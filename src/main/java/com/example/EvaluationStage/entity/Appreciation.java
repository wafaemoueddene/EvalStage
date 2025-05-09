package com.example.EvaluationStage.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Collection;

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

    @ManyToMany(mappedBy = "appreciations")
    private Collection<Periode> periodes;

    public Appreciation() {

    }
}