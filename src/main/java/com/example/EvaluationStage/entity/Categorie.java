package com.example.EvaluationStage.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Collection;

@Entity
@Data
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String intitule_categorie;
    private Float valeur_categorie;

    public Categorie() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule_categorie() {
        return intitule_categorie;
    }

    public void setIntitule_categorie(String intitule_categorie) {
        this.intitule_categorie = intitule_categorie;
    }

    public Float getValeur_categorie() {
        return valeur_categorie;
    }

    public void setValeur_categorie(Float valeur_categorie) {
        this.valeur_categorie = valeur_categorie;
    }

    public Categorie(int id, String intitule_categorie, Float valeur_categorie, Collection<Competences> competences) {
        this.id = id;
        this.intitule_categorie = intitule_categorie;
        this.valeur_categorie = valeur_categorie;
        this.competences = competences;
    }

    public Collection<Competences> getCompetences() {
        return competences;
    }

    public void setCompetences(Collection<Competences> competences) {
        this.competences = competences;
    }

    @OneToMany(mappedBy = "categorie")
    private Collection<Competences> competences;
}