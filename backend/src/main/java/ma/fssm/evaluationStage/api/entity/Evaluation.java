package ma.fssm.evaluationStage.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String categorie;

    private String valeur_evaluation;

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Appreciation> appreciations;
}
