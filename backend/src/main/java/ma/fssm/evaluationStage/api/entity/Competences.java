package ma.fssm.evaluationStage.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Competences {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private String intitule_competence;

    @Setter
    @Getter
    private Float note;

    public Competences(String intitule_competence, int id, Float note) {
        this.intitule_competence = intitule_competence;
        this.id = id;
        this.note = note;
    }

    @OneToMany(mappedBy = "competences")
    private Collection<Appreciation> appreciations;

    // Relation One-to-Many avec Categorie
    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categorie> categories = new ArrayList<>();

    public Competences() {
    }



    // Méthode pour ajouter une catégorie en établissant la relation bidirectionnelle
    public void addCategorie(Categorie categorie) {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
        }
        this.categories.add(categorie);
        categorie.setCompetence(this);
    }


}