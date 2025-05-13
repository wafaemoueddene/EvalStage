package ma.fssm.evaluationStage.api.dto.dashboard;

import lombok.Data;

@Data
public class EvaluationCategoryValueDTO {
    private String category;
    private double value;

    public EvaluationCategoryValueDTO() {
    }

    public EvaluationCategoryValueDTO(String category, double value) {
        this.category = category;
        this.value = value;
    }
}