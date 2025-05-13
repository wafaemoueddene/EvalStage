package ma.fssm.evaluationStage.api.dto.dashboard;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompetenceCategorieDetailDTO {
    private String intituleCategorie;
    private double valeurMoyenne;


    public String getIntituleCategorie() {
        return intituleCategorie;
    }

    public void setIntituleCategorie(String intituleCategorie) {
        this.intituleCategorie = intituleCategorie;
    }

    public double getValeurMoyenne() {
        return valeurMoyenne;
    }

    public void setValeurMoyenne(double valeurMoyenne) {
        this.valeurMoyenne = valeurMoyenne;
    }
}
