package ma.fssm.evaluationStage.api.dto.dashboard;

import lombok.Data;

@Data
public class CompetenceCategoryValueDTO {
    private String category;
    private double value;

    public CompetenceCategoryValueDTO() {
    }

    public CompetenceCategoryValueDTO(String category, double value) {
        this.category = category;
        this.value = value;
    }
}