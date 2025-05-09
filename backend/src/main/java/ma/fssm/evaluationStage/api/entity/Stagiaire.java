package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Data
public class Stagiaire extends Personne {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private String institution;

    public Stagiaire(String nom, String email, String prenom, int id) {
        super(nom, email, prenom);
        this.id = id;
    }

    public Stagiaire(String nom, String email, String prenom, int id, String institution) {
        super(nom, email, prenom);
        this.id = id;
        this.institution = institution;
    }

    public Stagiaire(int id, String institution) {
        this.id = id;
        this.institution = institution;
    }

    @ManyToMany(mappedBy = "stagiaires")
    private Collection<Stage> stages;

    public Stagiaire() {

    }
}