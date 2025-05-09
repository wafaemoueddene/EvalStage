package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Data
public class Tuteur extends Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private String entreprise;

    public Tuteur(String nom, String email, String prenom, int id, String entreprise) {
        super(nom, email, prenom);
        this.id = id;
        this.entreprise = entreprise;
    }

    public Tuteur(int id, String entreprise) {
        this.id = id;
        this.entreprise = entreprise;
    }

    @ManyToMany
    private Collection<Periode> periodes;

    public Tuteur() {

    }
}