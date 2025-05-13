package ma.fssm.evaluationStage.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private String objectif;
    private String entreprise;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "stage_stagiaire",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "stagiaire_id")
    )
    @JsonManagedReference
    private Collection<Stagiaire> stagiaires;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "stage_periode",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "periode_id")
    )
    private Collection<Periode> periodes;
}
