package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Data
public class Stage {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private String objectif;

    @Setter
    @Getter
    private String entreprise;

    public Stage(int id, String description, String objectif, String entreprise) {
        this.id = id;
        this.description = description;
        this.objectif = objectif;
        this.entreprise = entreprise;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "stage_stagiaire",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "stagiaire_id")
    )
    private Collection<Stagiaire> stagiaires ;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "stage_periode",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "periode_id")
    )
    private Collection<Periode> periodes ;

    public Stage() {

    }
}