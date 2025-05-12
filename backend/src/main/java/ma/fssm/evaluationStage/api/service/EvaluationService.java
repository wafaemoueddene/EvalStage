package ma.fssm.evaluationStage.api.service;

import ma.fssm.evaluationStage.api.dto.EvaluationDTO;
import ma.fssm.evaluationStage.api.entity.*;
import ma.fssm.evaluationStage.api.repository.*;
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

    @Autowired
    private AppreciationRepository appreciationRepository;

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
    public void saveCompetenceCategories(Map<String, String> competences, String categorieType) {
        if (competences == null || competences.isEmpty()) {
            return;
        }

        for (Map.Entry<String, String> entry : competences.entrySet()) {
            Categorie categorie = new Categorie();
            categorie.setIntitule_categorie(entry.getKey());
            categorie.setValeur_categorie(entry.getValue());
            categorieRepository.save(categorie);
        }
    }

    @Transactional
    public List<Evaluation> mapDTOToEvaluation(EvaluationDTO dto) {
        List<Evaluation> evaluations = new ArrayList<>();

        // 1. Récupérer ou créer le stagiaire
        Stagiaire stagiaire = getOrCreateStagiaire(dto);

        // 2. Récupérer ou créer le tuteur
        Tuteur tuteur = getOrCreateTuteur(dto);

        // 3. Créer la période de stage
        Periode periode = createPeriode(dto, tuteur);

        // 4. Créer le stage
        Stage stage = createStage(dto, stagiaire, periode);

        // Liste pour stocker toutes les compétences créées
        List<Competences> allCompetences = new ArrayList<>();

        // Créer une évaluation pour "Application dans ses activités"
        Evaluation evalApplication = null;
        if (dto.getApplication() != null && !dto.getApplication().isEmpty()) {
            evalApplication = new Evaluation();
            evalApplication.setCategorie("Application dans ses activités");
            evalApplication.setValeur_evaluation(mapApplicationValue(dto.getApplication()));
            evalApplication.setAppreciations(new ArrayList<>());
            evalApplication = saveEvaluation(evalApplication);
            evaluations.add(evalApplication);
        }

        // Créer une évaluation pour "Ouverture aux autres"
        Evaluation evalOuverture = null;
        if (dto.getOuverture() != null && !dto.getOuverture().isEmpty()) {
            evalOuverture = new Evaluation();
            evalOuverture.setCategorie("Ouverture aux autres");
            evalOuverture.setValeur_evaluation(mapOuvertureValue(dto.getOuverture()));
            evalOuverture.setAppreciations(new ArrayList<>());
            evalOuverture = saveEvaluation(evalOuverture);
            evaluations.add(evalOuverture);
        }

        // Créer une évaluation pour "Qualité de ses Productions"
        Evaluation evalQualite = null;
        if (dto.getQualite() != null && !dto.getQualite().isEmpty()) {
            evalQualite = new Evaluation();
            evalQualite.setCategorie("Qualité de ses \"Productions\"");
            evalQualite.setValeur_evaluation(mapQualiteValue(dto.getQualite()));
            evalQualite.setAppreciations(new ArrayList<>());
            evalQualite = saveEvaluation(evalQualite);
            evaluations.add(evalQualite);
        }

        // Traiter les compétences par catégorie
        Competences competenceIndividu = processCompetenceCategory(
                "Compétences liées à l'individu",
                dto.getCompetenceIndividu(),
                dto.getNoteIndividu(),
                allCompetences
        );

        Competences competenceEntreprise = processCompetenceCategory(
                "Compétences liées à l'entreprise",
                dto.getCompetenceEntreprise(),
                dto.getNoteEntreprise(),
                allCompetences
        );

        Competences competenceScientifique = processCompetenceCategory(
                "Compétences scientifiques",
                dto.getCompetencesScientifiques(),
                dto.getNoteScientifique(),
                allCompetences
        );

        // Traitement des compétences métier de manière similaire aux autres compétences
        // Même si la map est vide, on crée l'objet pour la note qui est obligatoire
        Competences competenceMetier = processCompetenceCategory(
                "Compétences métier",
                dto.getCompetenceMetier(),
                dto.getNoteMetier(),
                allCompetences
        );

        // Créer les appréciations qui lient les évaluations, compétences et périodes
        for (Evaluation evaluation : evaluations) {
            for (Competences competence : allCompetences) {
                createAppreciation(competence, evaluation, periode);
            }
        }

        return evaluations;
    }

    /**
     * Méthode générique pour traiter une catégorie de compétence
     */
    @Transactional
    public Competences processCompetenceCategory(String intitule, Map<String, String> competencesMap,
                                                  String noteStr, List<Competences> allCompetences) {
        Competences competence = new Competences();
        competence.setIntitule_competence(intitule);

        // Prendre en charge les cas où la note pourrait être null ou vide
        float note = 0F;
        if (noteStr != null && !noteStr.isEmpty()) {
            try {
                note = Float.parseFloat(noteStr);
            } catch (NumberFormatException e) {
                // Log de l'erreur ou gestion spécifique
            }
        }
        competence.setNote(note);


        // Sauvegarder la compétence principale avec sa note
        competence = competencesRepository.save(competence);
        allCompetences.add(competence);

        // Traiter les compétences détaillées et les catégories si elles existent
        if (competencesMap != null && !competencesMap.isEmpty()) {
            processDetailedCompetences(competence, competencesMap);
        }

        return competence;
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
            stagiaire.setPrenom(prenom);
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

        // Initialiser la collection des appréciations
        periode.setAppreciations(new ArrayList<>());

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

    @Transactional
    public void processDetailedCompetences(Competences mainCompetence, Map<String, String> competencesMap) {
        // Pour chaque entrée de compétence
        for (Map.Entry<String, String> entry : competencesMap.entrySet()) {
            String valeurCategorie = entry.getValue().isEmpty() ? "NA" : entry.getValue();

            // Créer une nouvelle catégorie
            Categorie categorie = new Categorie();
            categorie.setIntitule_categorie(entry.getKey());
            categorie.setValeur_categorie(valeurCategorie);

            // Établir la relation entre la catégorie et la compétence (sans référence circulaire)
            categorie.setCompetence(mainCompetence);

            // Sauvegarder la catégorie
            categorieRepository.save(categorie);

            // Ajouter la catégorie à la liste des catégories de la compétence
            if (mainCompetence.getCategories() == null) {
                mainCompetence.setCategories(new ArrayList<>());
            }
            mainCompetence.getCategories().add(categorie);
        }

        // Sauvegarder à nouveau la compétence principale après avoir ajouté toutes les catégories
        competencesRepository.save(mainCompetence);
    }

    // Méthodes de mappage des valeurs numériques aux textes qualitatifs
    private String mapApplicationValue(String value) {
        switch(value) {
            case "1": return "Paresseux";
            case "2": return "Le juste nécessaire";
            case "3": return "Bonne";
            case "4": return "Très forte";
            case "5": return "Dépasse ses objectifs";
            default: return value; // Retourne la valeur d'origine en cas de non-correspondance
        }
    }

    private String mapOuvertureValue(String value) {
        switch(value) {
            case "1": return "Isolé(e) ou en opposition";
            case "2": return "Renfermé(e) ou obtus";
            case "3": return "Bonne";
            case "4": return "Très Bonne";
            case "5": return "Excellente";
            default: return value;
        }
    }

    private String mapQualiteValue(String value) {
        switch(value) {
            case "1": return "Médiocre";
            case "2": return "Acceptable";
            case "3": return "Bonne";
            case "4": return "Très Bonne";
            case "5": return "Très professionnelle";
            default: return value;
        }
    }

    @Transactional
    public void createAppreciation(Competences competence, Evaluation evaluation, Periode periode) {
        if (competence != null && evaluation != null && periode != null) {
            // Créer une nouvelle appréciation
            Appreciation appreciation = new Appreciation();
            appreciation.setCompetences(competence);
            appreciation.setEvaluation(evaluation);

            // Sauvegarder l'appréciation
            appreciation = appreciationRepository.save(appreciation);

            // Mettre à jour les relations
            // 1. Ajouter l'appréciation à l'évaluation
            if (evaluation.getAppreciations() == null) {
                evaluation.setAppreciations(new ArrayList<>());
            }
            evaluation.getAppreciations().add(appreciation);
            evaluationRepository.save(evaluation);

            // 2. Ajouter l'appréciation à la période
            if (periode.getAppreciations() == null) {
                periode.setAppreciations(new ArrayList<>());
            }
            periode.getAppreciations().add(appreciation);
            periodeRepository.save(periode);
        }
    }
}