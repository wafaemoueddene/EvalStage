package ma.fssm.evaluationStage.api.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EvaluationCategoryValueDTO {
    private String category;
    private double value;


}