package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String intitule_categorie;

    @Getter
    @Setter
    private String valeur_categorie;

    // Relation Many-to-One avec Competences - cette relation est maintenue
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competences_id")
    private Competences competence;
}