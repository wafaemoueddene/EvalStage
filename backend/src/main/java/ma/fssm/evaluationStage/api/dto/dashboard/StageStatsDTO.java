package ma.fssm.evaluationStage.api.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class StageStatsDTO {
    @JsonProperty("entreprise")
    private String entreprise;

    @JsonProperty("objectif")
    private String objectif;

    @JsonProperty("description")
    private String description;

    // Informations du stagiaire
    @JsonProperty("stagiaireNom")
    private String stagiaireNom;

    @JsonProperty("stagiairePrenom")
    private String stagiairePrenom;

    @JsonProperty("stagiaireEmail")
    private String stagiaireEmail;

    // Informations du tuteur
    @JsonProperty("tuteurNom")
    private String tuteurNom;

    @JsonProperty("tuteurPrenom")
    private String tuteurPrenom;

    @JsonProperty("tuteurEmail")
    private String tuteurEmail;

    // Informations de la période
    @JsonProperty("dateDebut")
    private LocalDate dateDebut;

    @JsonProperty("dateFin")
    private LocalDate dateFin;

    // Getters
    public String getEntreprise() {
        return entreprise;
    }

    public String getObjectif() {
        return objectif;
    }

    public String getDescription() {
        return description;
    }

    public String getStagiaireNom() {
        return stagiaireNom;
    }

    public String getStagiairePrenom() {
        return stagiairePrenom;
    }

    public String getStagiaireEmail() {
        return stagiaireEmail;
    }

    public String getTuteurNom() {
        return tuteurNom;
    }

    public String getTuteurPrenom() {
        return tuteurPrenom;
    }

    public String getTuteurEmail() {
        return tuteurEmail;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    // Setters
    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStagiaireNom(String stagiaireNom) {
        this.stagiaireNom = stagiaireNom;
    }

    public void setStagiairePrenom(String stagiairePrenom) {
        this.stagiairePrenom = stagiairePrenom;
    }

    public void setStagiaireEmail(String stagiaireEmail) {
        this.stagiaireEmail = stagiaireEmail;
    }

    public void setTuteurNom(String tuteurNom) {
        this.tuteurNom = tuteurNom;
    }

    public void setTuteurPrenom(String tuteurPrenom) {
        this.tuteurPrenom = tuteurPrenom;
    }

    public void setTuteurEmail(String tuteurEmail) {
        this.tuteurEmail = tuteurEmail;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}