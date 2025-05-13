package ma.fssm.evaluationStage.api.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StageStatsDTO {
    @JsonProperty("entreprise")
    @Getter
    @Setter
    private String entreprise;

    @Getter
    @Setter
    @JsonProperty("objectif")
    private String objectif;

    @Getter
    @Setter
    @JsonProperty("description")
    private String description;

    // Informations du stagiaire
    @Getter
    @Setter
    @JsonProperty("stagiaireNom")
    private String stagiaireNom;

    @Getter
    @Setter
    @JsonProperty("stagiairePrenom")
    private String stagiairePrenom;

    @Getter
    @Setter
    @JsonProperty("stagiaireEmail")
    private String stagiaireEmail;

    // Informations du tuteur
    @Getter
    @Setter
    @JsonProperty("tuteurNom")
    private String tuteurNom;

    @Getter
    @Setter
    @JsonProperty("tuteurPrenom")
    private String tuteurPrenom;

    @Getter
    @Setter
    @JsonProperty("tuteurEmail")
    private String tuteurEmail;

    // Informations de la période
    @Getter
    @Setter
    @JsonProperty("dateDebut")
    private LocalDate dateDebut;


    @Getter
    @Setter
    @JsonProperty("dateFin")
    private LocalDate dateFin;


}