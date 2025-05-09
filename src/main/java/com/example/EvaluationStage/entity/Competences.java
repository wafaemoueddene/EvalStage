package com.example.EvaluationStage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

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

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @OneToMany(mappedBy = "competences")
    private Collection<Appreciation> appreciations;

    public Competences() {
        
    }
}