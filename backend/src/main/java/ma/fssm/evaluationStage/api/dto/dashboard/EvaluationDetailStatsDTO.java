package ma.fssm.evaluationStage.api.dto.dashboard;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EvaluationDetailStatsDTO {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String value;

    @Getter
    @Setter
    private int count;




}