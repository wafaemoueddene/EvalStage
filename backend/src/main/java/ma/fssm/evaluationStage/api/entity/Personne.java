package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Data
public abstract class Personne {
    @Setter
    @Getter
    private String nom;

    @Setter
    @Getter
    private String prenom;

    @Setter
    @Getter
    private String email;

    public Personne(String nom, String email, String prenom) {
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
    }

    public Personne() {
    }
}