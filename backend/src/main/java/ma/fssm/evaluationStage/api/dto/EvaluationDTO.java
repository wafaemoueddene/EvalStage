package ma.fssm.evaluationStage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
    private String stagiaire;
    private String emailStagiaire;
    private String emailTuteur;
    private String entreprise;
    private String tuteur;
    private String dateDebut;
    private String dateFin;
    private String themeProjet;
    private String objectifs;
    private String application;
    private String ouverture;
    private String qualite;
    private Map<String, String> competenceIndividu;
    private Map<String, String> competenceEntreprise;
    private Map<String, String> competencesScientifiques;
    private Map<String, String> competenceMetier;
    private String noteIndividu;
    private String noteEntreprise;
    private String noteScientifique;
    private String avisGeneral;

}