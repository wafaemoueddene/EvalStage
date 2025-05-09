package com.example.EvaluationStage.service;

import com.example.EvaluationStage.entity.Tuteur;
import com.example.EvaluationStage.repository.TuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TuteurService {

    @Autowired
    private TuteurRepository tuteurRepository;

    public List<Tuteur> getAllTuteurs() {
        return tuteurRepository.findAll();
    }

    public Tuteur getTuteurById(int id) {
        return tuteurRepository.findById(id).orElse(null);
    }

    public List<Tuteur> getTuteursByEntreprise(String entreprise) {
        return tuteurRepository.findByEntreprise(entreprise);
    }

    public Optional<Tuteur> getTuteurByEmail(String email) {
        return tuteurRepository.findByEmail(email);
    }

    public Optional<Tuteur> getTuteurByNomAndPrenom(String nom, String prenom) {
        return tuteurRepository.findByNomAndPrenom(nom, prenom);
    }

    public Tuteur saveTuteur(Tuteur tuteur) {
        return tuteurRepository.save(tuteur);
    }

    public void deleteTuteur(int id) {
        tuteurRepository.deleteById(id);
    }
}