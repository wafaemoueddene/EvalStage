package ma.fssm.evaluationStage.api.dto.dashboard;


public class EvaluationDetailStatsDTO {
    private String name;
    private String value;
    private int count;

    public EvaluationDetailStatsDTO() {
    }

    public EvaluationDetailStatsDTO(String name, String value, int count) {
        this.name = name;
        this.value = value;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}