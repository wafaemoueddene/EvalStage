package ma.fssm.evaluationStage.api.service;

import lombok.RequiredArgsConstructor;
import ma.fssm.evaluationStage.api.dto.dashboard.*;
import ma.fssm.evaluationStage.api.entity.*;
import ma.fssm.evaluationStage.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.Objects;
import java.util.HashSet;


@RequiredArgsConstructor
@Service
public class DashboardService {


    private static final Logger logger = Logger.getLogger(DashboardService.class.getName());

    private  final  EvaluationRepository evaluationRepository;


    private  final StagiaireRepository stagiaireRepository;


    private final  TuteurRepository tuteurRepository;


    private final  CompetencesRepository competencesRepository;


    private final  StageRepository stageRepository;


    private  final AppreciationRepository appreciationRepository;


    private  final PeriodeRepository periodeRepository;


    @Transactional(readOnly = true)
    public Map<String, Integer> getEvaluationCategoriesStats() {
        List<Evaluation> evaluations = evaluationRepository.findAll();
        Map<String, Integer> evaluationCategoriesCount = new HashMap<>();

        for (Evaluation evaluation : evaluations) {
            String categorie = evaluation.getCategorie();
            if (categorie != null && !categorie.isEmpty()) {
                evaluationCategoriesCount.put(categorie, evaluationCategoriesCount.getOrDefault(categorie, 0) + 1);
            }
        }

        return evaluationCategoriesCount;
    }

    @Transactional(readOnly = true)
    public List<EvaluationStatsDTO> getEvaluationStats() {
        Map<String, Integer> evaluationCount = getEvaluationCategoriesStats();  // Réutilisation de la méthode existante

        List<EvaluationStatsDTO> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : evaluationCount.entrySet()) {
            EvaluationStatsDTO dto = new EvaluationStatsDTO();
            dto.setName(entry.getKey());
            dto.setValue(entry.getValue());
            result.add(dto);
        }

        return result;
    }


    @Transactional(readOnly = true)
    public DashboardStatsDTO getDashboardStats() {
        logger.info("Fetching dashboard stats...");
        long totalEvaluations = evaluationRepository.count();
        logger.info("Total Evaluations: " + totalEvaluations);

        // Compter le nombre d'évaluations avec des notes > 0 directement en base de données
        long completedEvaluations = appreciationRepository.findAll().stream()
                .filter(a -> a.getCompetences() != null && a.getCompetences().getNote() > 0)
                .map(a -> a.getEvaluation().getId())
                .distinct()
                .count();

        long pendingEvaluations = totalEvaluations - completedEvaluations;

        // Calculer la note moyenne directement à partir des compétences notées
        double averageRating = competencesRepository.findAll().stream()
                .filter(c -> c.getNote() > 0)
                .mapToDouble(Competences::getNote)
                .average()
                .orElse(0.0);

        // Compter le nombre total de stagiaires, tuteurs et entreprises
        long totalStagiaires = stagiaireRepository.count();
        long totalTuteurs = tuteurRepository.count();

        // Récupérer les entreprises uniques
        Set<String> entreprises = stageRepository.findAll().stream()
                .map(Stage::getEntreprise)
                .filter(Objects::nonNull)
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toSet());

        long totalEntreprises = entreprises.size();

        // Créer et retourner le DTO
        DashboardStatsDTO stats = new DashboardStatsDTO();
        stats.setTotalEvaluations((int) totalEvaluations);
        stats.setCompletedEvaluations((int) completedEvaluations);
        stats.setPendingEvaluations((int) pendingEvaluations);
        stats.setAverageRating((float) averageRating);
        stats.setTotalStagiaires((int) totalStagiaires);
        stats.setTotalTuteurs((int) totalTuteurs);
        stats.setTotalEntreprises((int) totalEntreprises);

        return stats;
    }

    /**
     * Récupère les statistiques des compétences optimisées pour le graphique en camembert
     */
    @Transactional(readOnly = true)
    public List<CompetenceStatsDTO> getCompetencesStats() {
        Map<String, List<Competences>> groupedCompetences = competencesRepository.findAll().stream()
                .filter(c -> c.getIntitule_competence() != null && !c.getIntitule_competence().isEmpty())
                .collect(Collectors.groupingBy(Competences::getIntitule_competence));

        List<CompetenceStatsDTO> result = new ArrayList<>();

        for (Map.Entry<String, List<Competences>> entry : groupedCompetences.entrySet()) {
            String competenceName = entry.getKey();
            List<Competences> competencesList = entry.getValue();

            // Calculer la moyenne des notes pour cette compétence en ignorant les notes à 0
            double averageNote = competencesList.stream()
                    .mapToDouble(Competences::getNote)
                    .filter(note -> note > 0)
                    .average()
                    .orElse(0.0);

            // Ne pas ajouter les compétences qui n'ont pas de notes
            if (averageNote > 0) {
                CompetenceStatsDTO dto = new CompetenceStatsDTO();
                dto.setName(competenceName);
                dto.setValue((float) averageNote);
                result.add(dto);
            }
        }

        return result;
    }

    /**
     * Récupère les statistiques des entreprises optimisées pour le graphique à barres
     */
    @Transactional(readOnly = true)
    public List<EntrepriseStatsDTO> getEntreprisesStats() {
        // Récupérer tous les stages avec leurs stagiaires
        List<Stage> stages = stageRepository.findAll();

        // Créer une map pour stocker les stagiaires uniques par entreprise
        Map<String, Set<Integer>> stagiairesByEntreprise = new HashMap<>();

        // Remplir la map avec les données
        for (Stage stage : stages) {
            String entreprise = stage.getEntreprise();
            if (entreprise != null && !entreprise.isEmpty() && stage.getStagiaires() != null) {
                // Initialiser le set s'il n'existe pas encore
                stagiairesByEntreprise.putIfAbsent(entreprise, new HashSet<>());

                // Ajouter les IDs des stagiaires
                for (Stagiaire stagiaire : stage.getStagiaires()) {
                    stagiairesByEntreprise.get(entreprise).add(stagiaire.getId());
                }
            }
        }

        // Convertir la map en liste de DTOs
        List<EntrepriseStatsDTO> result = new ArrayList<>();
        for (Map.Entry<String, Set<Integer>> entry : stagiairesByEntreprise.entrySet()) {
            EntrepriseStatsDTO dto = new EntrepriseStatsDTO();
            dto.setName(entry.getKey());
            dto.setStagiaires(entry.getValue().size());
            result.add(dto);
        }

        // Trier par nombre de stagiaires décroissant
        result.sort(Comparator.comparing(EntrepriseStatsDTO::getStagiaires).reversed());

        return result;
    }

    @Transactional(readOnly = true)
    public List<StageStatsDTO> getStagesStats() {
        List<Stage> stages = stageRepository.findAll();
        List<StageStatsDTO> result = new ArrayList<>();

        for (Stage stage : stages) {
            StageStatsDTO dto = new StageStatsDTO();

            // Informations de base du stage
            dto.setEntreprise(stage.getEntreprise());
            dto.setObjectif(stage.getObjectif());
            dto.setDescription(stage.getDescription());

            // Informations du stagiaire (on prend le premier stagiaire associé au stage)
            if (stage.getStagiaires() != null && !stage.getStagiaires().isEmpty()) {
                Stagiaire stagiaire = stage.getStagiaires().iterator().next();
                dto.setStagiaireNom(stagiaire.getNom());
                dto.setStagiairePrenom(stagiaire.getPrenom());
                dto.setStagiaireEmail(stagiaire.getEmail());
            }

            // Informations du tuteur
            if (stage.getPeriodes() != null && !stage.getPeriodes().isEmpty()) {
                Periode periode = stage.getPeriodes().iterator().next();

                // Si cette période a des tuteurs associés, on prend le premier
                if (periode.getTuteur() != null && !periode.getTuteur().isEmpty()) {
                    Tuteur tuteur = periode.getTuteur().iterator().next();
                    dto.setTuteurNom(tuteur.getNom());
                    dto.setTuteurPrenom(tuteur.getPrenom());
                    dto.setTuteurEmail(tuteur.getEmail());
                }

                // Informations de la période
                dto.setDateDebut(periode.getDateDebut());
                dto.setDateFin(periode.getDateFin());
            }

            result.add(dto);
        }

        return result;
    }

    /**
     * Récupère les données pour le graphique des catégories d'évaluation
     * avec leurs valeurs correspondantes
     */
    @Transactional(readOnly = true)
    public List<EvaluationCategoryValueDTO> getEvaluationCategoryValues() {
        List<Evaluation> evaluations = evaluationRepository.findAll();
        Map<String, List<Float>> categoriesValues = new HashMap<>();

        // Grouper les évaluations par catégorie
        for (Evaluation evaluation : evaluations) {
            String categorie = evaluation.getCategorie();

            // Vérifier que nous pouvons accéder à la valeur de l'évaluation
            Float valeurEvaluation = 0.0f;
            try {

                valeurEvaluation = 0.0f;
            } catch (Exception e) {
                valeurEvaluation = 0.0f;
            }

            if (categorie != null && !categorie.isEmpty()) {
                categoriesValues.putIfAbsent(categorie, new ArrayList<>());
                categoriesValues.get(categorie).add(valeurEvaluation);
            }
        }

        // Calculer la moyenne des valeurs pour chaque catégorie
        List<EvaluationCategoryValueDTO> result = new ArrayList<>();
        for (Map.Entry<String, List<Float>> entry : categoriesValues.entrySet()) {
            String category = entry.getKey();
            List<Float> values = entry.getValue();

            double averageValue = values.stream()
                    .mapToDouble(Float::doubleValue)
                    .average()
                    .orElse(0.0);

            result.add(new EvaluationCategoryValueDTO(category, averageValue));
        }

        return result;
    }

    /**
     * Récupère les données pour le graphique des catégories de compétences
     * avec leurs valeurs correspondantes
     */
    @Transactional(readOnly = true)
    public List<CompetenceCategoryValueDTO> getCompetenceCategoryValues() {
        // Récupérer toutes les compétences avec leurs catégories
        List<Competences> competences = competencesRepository.findAll();
        Map<String, List<Float>> categoriesValues = new HashMap<>();

        // Pour chaque compétence, parcourir ses catégories associées
        for (Competences competence : competences) {
            if (competence.getCategories() != null) {
                for (Categorie categorie : competence.getCategories()) {
                    String intituleCategorie = categorie.getIntitule_categorie();
                    String valeurCategorieStr = categorie.getValeur_categorie();

                    // Convertir la valeur de la catégorie de String en Float
                    Float valeurCategorie = 0.0f;
                    try {
                        valeurCategorie = Float.parseFloat(valeurCategorieStr);
                    } catch (NumberFormatException | NullPointerException e) {
                        // En cas d'erreur de conversion, utiliser 0.0f par défaut
                        valeurCategorie = 0.0f;
                    }

                    if (intituleCategorie != null && !intituleCategorie.isEmpty()) {
                        categoriesValues.putIfAbsent(intituleCategorie, new ArrayList<>());
                        categoriesValues.get(intituleCategorie).add(valeurCategorie);
                    }
                }
            }
        }

        // Calculer la moyenne des valeurs pour chaque catégorie
        List<CompetenceCategoryValueDTO> result = new ArrayList<>();
        for (Map.Entry<String, List<Float>> entry : categoriesValues.entrySet()) {
            String category = entry.getKey();
            List<Float> values = entry.getValue();

            double averageValue = values.stream()
                    .mapToDouble(Float::doubleValue)
                    .average()
                    .orElse(0.0);

            result.add(new CompetenceCategoryValueDTO(category, averageValue));
        }

        return result;
    }



}