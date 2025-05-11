package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Periode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToMany
    @JoinTable(
            name = "periode_tuteur",
            joinColumns = @JoinColumn(name = "periode_id"),
            inverseJoinColumns = @JoinColumn(name = "tuteur_id")
    )
    private Collection<Tuteur> tuteur;

    @ManyToMany
    @JoinTable(
            name = "periode_appreciation",
            joinColumns = @JoinColumn(name = "periode_id"),
            inverseJoinColumns = @JoinColumn(name = "appreciation_id")
    )
    private Collection<Appreciation> appreciations;

    @ManyToMany(mappedBy = "periodes")
    private Collection<Stage> stages;
}
