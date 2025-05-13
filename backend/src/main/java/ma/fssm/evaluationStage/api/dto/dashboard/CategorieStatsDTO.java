package ma.fssm.evaluationStage.api.dto.dashboard;



public class CategorieStatsDTO {
    private String categorie;
    private String valeur;
    private int count;

    public CategorieStatsDTO() {
    }

    public CategorieStatsDTO(String categorie, String valeur, int count) {
        this.categorie = categorie;
        this.valeur = valeur;
        this.count = count;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}