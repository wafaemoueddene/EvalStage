package ma.fssm.evaluationStage.api.service;

import ma.fssm.evaluationStage.api.entity.Tuteur;
import ma.fssm.evaluationStage.api.repository.TuteurRepository;
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