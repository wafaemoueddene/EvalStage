package ma.fssm.evaluationStage.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stagiaire extends Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String institution;

    @ManyToMany(mappedBy = "stagiaires")
    @JsonBackReference
    private Collection<Stage> stages;

    public Stagiaire(String nom, String email, String prenom, int id, String institution) {
        super(nom, email, prenom);
        this.id = id;
        this.institution = institution;
    }
}
