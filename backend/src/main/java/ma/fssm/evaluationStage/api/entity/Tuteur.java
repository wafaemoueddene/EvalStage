package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tuteur extends Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String entreprise;

    @ManyToMany(mappedBy = "tuteur")
    private Collection<Periode> periodes;
}
