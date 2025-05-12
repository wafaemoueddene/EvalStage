package ma.fssm.evaluationStage.api.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Collection;
import java.util.ArrayList;

@Entity
@Data
public class Appreciation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Appreciation(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "competences_id")
    private Competences competences;

    @ManyToMany
    @JoinTable(
            name = "appreciation_periode",
            joinColumns = @JoinColumn(name = "appreciation_id"),
            inverseJoinColumns = @JoinColumn(name = "periode_id")
    )
    private Collection<Periode> periodes = new ArrayList<>();

    public Appreciation() {
    }

    public void setValeur(String valeurCompetence) {
        // Implémentez cette méthode si nécessaire
    }

    // Méthode d'aide pour ajouter une période à cette appréciation
    public void addPeriode(Periode periode) {
        if (this.periodes == null) {
            this.periodes = new ArrayList<>();
        }
        this.periodes.add(periode);

        // Si la période a également une collection d'appréciations, ajoutez-y cette appréciation
        if (periode.getAppreciations() != null && !periode.getAppreciations().contains(this)) {
            periode.getAppreciations().add(this);
        }
    }
}