package ma.fssm.evaluationStage.api.dto.dashboard;

import lombok.Data;

@Data
public class DashboardStatsDTO {
    private int totalEvaluations;
    private int completedEvaluations;
    private int pendingEvaluations;
    private float averageRating;
    private int totalStagiaires;
    private int totalTuteurs;
    private int totalEntreprises;
}