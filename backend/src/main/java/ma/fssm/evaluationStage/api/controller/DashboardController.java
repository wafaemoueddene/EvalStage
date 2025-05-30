package ma.fssm.evaluationStage.api.controller;

import lombok.RequiredArgsConstructor;
import ma.fssm.evaluationStage.api.dto.dashboard.*;
import ma.fssm.evaluationStage.api.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/dashboard")
public class DashboardController {

        private final DashboardService dashboardService;


    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    @GetMapping("/competences")
    public ResponseEntity<List<CompetenceStatsDTO>> getCompetencesStats() {
        return ResponseEntity.ok(dashboardService.getCompetencesStats());
    }

    @GetMapping("/entreprises")
    public ResponseEntity<List<EntrepriseStatsDTO>> getEntreprisesStats() {
        return ResponseEntity.ok(dashboardService.getEntreprisesStats());
    }

    @GetMapping("/evaluations")
    public ResponseEntity<List<EvaluationStatsDTO>> getEvaluationStats() {
        List<EvaluationStatsDTO> evaluationStats = dashboardService.getEvaluationStats();
        return ResponseEntity.ok(evaluationStats);
    }

    @GetMapping("/evaluation-categories")
    public ResponseEntity<Map<String, Integer>> getEvaluationCategoriesStats() {
        Map<String, Integer> evaluationCategoriesStats = dashboardService.getEvaluationCategoriesStats();
        return ResponseEntity.ok(evaluationCategoriesStats);
    }


    @GetMapping("/stages")
    public ResponseEntity<List<StageStatsDTO>> getStagesStats() {
        List<StageStatsDTO> stagesStats = dashboardService.getStagesStats();
        return ResponseEntity.ok(stagesStats);
    }

    @GetMapping("/evaluation-category-values")
    public ResponseEntity<List<EvaluationCategoryValueDTO>> getEvaluationCategoryValues() {
        List<EvaluationCategoryValueDTO> values = dashboardService.getEvaluationCategoryValues();
        return ResponseEntity.ok(values);
    }

    @GetMapping("/competence-category-values")
    public ResponseEntity<List<CompetenceCategoryValueDTO>> getCompetenceCategoryValues() {
        List<CompetenceCategoryValueDTO> values = dashboardService.getCompetenceCategoryValues();
        return ResponseEntity.ok(values);
    }

}