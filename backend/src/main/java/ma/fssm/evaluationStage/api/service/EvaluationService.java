package ma.fssm.evaluationStage.api.service;

import ma.fssm.evaluationStage.api.dto.EvaluationDTO;
import ma.fssm.evaluationStage.api.entity.*;
import ma.fssm.evaluationStage.api.repository.EvaluationRepository;
import ma.fssm.evaluationStage.api.repository.StagiaireRepository;
import ma.fssm.evaluationStage.api.repository.TuteurRepository;
import ma.fssm.evaluationStage.api.repository.CategorieRepository;
import ma.fssm.evaluationStage.api.repository.CompetencesRepository;
import ma.fssm.evaluationStage.api.repository.StageRepository;
import ma.fssm.evaluationStage.api.repository.PeriodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private StagiaireRepository stagiaireRepository;

    @Autowired
    private TuteurRepository tuteurRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private CompetencesRepository competencesRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private PeriodeRepository periodeRepository;

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Evaluation getEvaluationById(int id) {
        return evaluationRepository.findById(id).orElse(null);
    }

    public List<Evaluation> getEvaluationsByCategorie(String categorie) {
        return evaluationRepository.findByCategorie(categorie);
    }

    public List<Evaluation> getEvaluationsByValeur(String valeur) {
        return evaluationRepository.findByValeurEvaluation(valeur);
    }

    @Transactional
    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(int id) {
        evaluationRepository.deleteById(id);
    }

    @Transactional
    public Evaluation mapDTOToEvaluation(EvaluationDTO dto) {
        // Création de l'évaluation
        Evaluation evaluation = new Evaluation();
        evaluation.setCategorie(dto.getThemeProjet());
        evaluation.setValeur_evaluation(dto.getAvisGeneral());

        // Préparation de la liste des appréciations
        List<Appreciation> appreciations = new ArrayList<>();
        evaluation.setAppreciations(appreciations);

        // Traiter le stagiaire
        Stagiaire stagiaire = getOrCreateStagiaire(dto);

        // Traiter le tuteur
        Tuteur tuteur = getOrCreateTuteur(dto);

        // Créer la période
        Periode periode = createPeriode(dto, tuteur);

        // Créer le stage
        Stage stage = createStage(dto, stagiaire, periode);

        // Traiter les compétences par catégorie
        processCompetences(dto, evaluation, "INDIVIDU", dto.getCompetenceIndividu(), periode, Float.parseFloat(dto.getNoteIndividu()));
        processCompetences(dto, evaluation, "ENTREPRISE", dto.getCompetenceEntreprise(), periode, Float.parseFloat(dto.getNoteEntreprise()));
        processCompetences(dto, evaluation, "SCIENTIFIQUE", dto.getCompetencesScientifiques(), periode, Float.parseFloat(dto.getNoteScientifique()));

        return evaluation;
    }

    private Stagiaire getOrCreateStagiaire(EvaluationDTO dto) {
        // Vérifier si le stagiaire existe déjà par email
        Optional<Stagiaire> existingStagiaire = stagiaireRepository.findByEmail(dto.getEmailStagiaire());

        if (existingStagiaire.isPresent()) {
            return existingStagiaire.get();
        } else {
            // Créer un nouveau stagiaire
            String[] nomPrenom = dto.getStagiaire().split(" ", 2);
            String nom = nomPrenom.length > 0 ? nomPrenom[0] : "";
            String prenom = nomPrenom.length > 1 ? nomPrenom[1] : "";

            Stagiaire stagiaire = new Stagiaire();
            stagiaire.setNom(nom);
            stagiaire.setPrenom(prenom);  // Cette ligne manquait
            stagiaire.setEmail(dto.getEmailStagiaire());
            stagiaire.setInstitution("FSSM");

            return stagiaireRepository.save(stagiaire);
        }
    }

    private Tuteur getOrCreateTuteur(EvaluationDTO dto) {
        // Vérifier si le tuteur existe déjà par email
        Optional<Tuteur> existingTuteur = tuteurRepository.findByEmail(dto.getEmailTuteur());

        if (existingTuteur.isPresent()) {
            return existingTuteur.get();
        } else {
            // Créer un nouveau tuteur
            String[] nomPrenom = dto.getTuteur().split(" ", 2);
            String nom = nomPrenom.length > 0 ? nomPrenom[0] : "";
            String prenom = nomPrenom.length > 1 ? nomPrenom[1] : "";

            Tuteur tuteur = new Tuteur();
            tuteur.setNom(nom);
            tuteur.setPrenom(prenom);
            tuteur.setEmail(dto.getEmailTuteur());
            tuteur.setEntreprise(dto.getEntreprise());

            return tuteurRepository.save(tuteur);
        }
    }

    private Periode createPeriode(EvaluationDTO dto, Tuteur tuteur) {
        Periode periode = new Periode();

        // Parser les dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateDebut = LocalDate.parse(dto.getDateDebut(), formatter);
        LocalDate dateFin = LocalDate.parse(dto.getDateFin(), formatter);

        periode.setDateDebut(dateDebut);
        periode.setDateFin(dateFin);

        // Associer le tuteur
        List<Tuteur> tuteurs = new ArrayList<>();
        tuteurs.add(tuteur);
        periode.setTuteur(tuteurs);

        return periodeRepository.save(periode);
    }

    private Stage createStage(EvaluationDTO dto, Stagiaire stagiaire, Periode periode) {
        Stage stage = new Stage();
        stage.setDescription(dto.getThemeProjet());
        stage.setObjectif(dto.getObjectifs());
        stage.setEntreprise(dto.getEntreprise());

        // Associer le stagiaire
        List<Stagiaire> stagiaires = new ArrayList<>();
        stagiaires.add(stagiaire);
        stage.setStagiaires(stagiaires);

        // Associer la période
        List<Periode> periodes = new ArrayList<>();
        periodes.add(periode);
        stage.setPeriodes(periodes);

        return stageRepository.save(stage);
    }

    private void processCompetences(EvaluationDTO dto, Evaluation evaluation, String categorieType,
                                    Map<String, String> competencesMap, Periode periode, Float noteGlobale) {

        if (competencesMap == null || competencesMap.isEmpty()) {
            return;
        }


        // Traiter chaque compétence
        for (Map.Entry<String, String> entry : competencesMap.entrySet()) {
            String intituleCompetence = entry.getKey();
            String valeurCompetence = entry.getValue();

            // Sauter les compétences sans valeur
            if (valeurCompetence == null || valeurCompetence.isEmpty()) {
                continue;
            }


            // Créer la compétence
            Competences competence = new Competences();
            competence.setIntitule_competence(intituleCompetence);
            competence = competencesRepository.save(competence);

            // Créer l'appréciation
            Appreciation appreciation = new Appreciation();
            appreciation.setEvaluation(evaluation);
            appreciation.setCompetences(competence);

            // Associer à la période
            List<Periode> periodes = new ArrayList<>();
            periodes.add(periode);
            appreciation.setPeriodes(periodes);

            // Ajouter à la liste des appréciations de l'évaluation
            evaluation.getAppreciations().add(appreciation);
        }
    }



}