package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Data
public class Evaluation {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private String categorie;

    public Evaluation(int id, String categorie, String valeur_evaluation) {
        this.id = id;
        this.categorie = categorie;
        this.valeur_evaluation = valeur_evaluation;
    }

    @Setter
    @Getter
    private String valeur_evaluation;

    @OneToMany(mappedBy = "evaluation")
    private Collection<Appreciation> appreciations;

    public Evaluation() {

    }
}