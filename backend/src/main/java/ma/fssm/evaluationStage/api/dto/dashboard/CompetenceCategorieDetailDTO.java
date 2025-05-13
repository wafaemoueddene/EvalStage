package ma.fssm.evaluationStage.api.dto.dashboard;

public class CompetenceCategorieDetailDTO {
    private String intituleCategorie;
    private double valeurMoyenne;

    public CompetenceCategorieDetailDTO(String intituleCategorie, double valeurMoyenne) {
        this.intituleCategorie = intituleCategorie;
        this.valeurMoyenne = valeurMoyenne;
    }

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
