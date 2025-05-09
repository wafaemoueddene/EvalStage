package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
public class Periode {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private LocalDate dateDebut;

    @Setter
    @Getter
    private LocalDate dateFin;

    public Periode(int id, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    @ManyToMany
    @JoinTable(
            name = "periode_tuteur",
            joinColumns = @JoinColumn(name = "periode_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tuteur_id", referencedColumnName = "id")
    )
    private Collection<Tuteur> tuteur;

    @ManyToMany
    @JoinTable(
            name = "periode_appreciation",
            joinColumns = @JoinColumn(name = "periode_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "appreciation_id", referencedColumnName = "id")
    )
    private Collection<Appreciation> appreciations;

    @ManyToMany(mappedBy = "periodes")
    private Collection<Stage> stages;

    public Periode() {

    }
}