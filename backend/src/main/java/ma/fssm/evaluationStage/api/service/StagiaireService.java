package ma.fssm.evaluationStage.api.service;

import ma.fssm.evaluationStage.api.entity.Stagiaire;
import ma.fssm.evaluationStage.api.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;

    public List<Stagiaire> getAllStagiaires() {
        return stagiaireRepository.findAll();
    }

    public Stagiaire getStagiaireById(int id) {
        return stagiaireRepository.findById(id).orElse(null);
    }

    public List<Stagiaire> getStagiairesByInstitution(String institution) {
        return stagiaireRepository.findByInstitution(institution);
    }

    public Optional<Stagiaire> getStagiaireByEmail(String email) {
        return stagiaireRepository.findByEmail(email);
    }

    public Optional<Stagiaire> getStagiaireByNomAndPrenom(String nom, String prenom) {
        return stagiaireRepository.findByNomAndPrenom(nom, prenom);
    }

    public Stagiaire saveStagiaire(Stagiaire stagiaire) {
        return stagiaireRepository.save(stagiaire);
    }

    public void deleteStagiaire(int id) {
        stagiaireRepository.deleteById(id);
    }
}