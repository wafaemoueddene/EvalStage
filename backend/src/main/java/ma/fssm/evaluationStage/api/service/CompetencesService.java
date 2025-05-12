package ma.fssm.evaluationStage.api.service;

import ma.fssm.evaluationStage.api.entity.Competences;
import ma.fssm.evaluationStage.api.repository.CompetencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetencesService {

    @Autowired
    private CompetencesRepository competencesRepository;

    public List<Competences> getAllCompetences() {
        return competencesRepository.findAll();
    }

    public Competences getCompetenceById(int id) {
        return competencesRepository.findById(id).orElse(null);
    }

    public Competences saveCompetence(Competences competence) {
        return competencesRepository.save(competence);
    }

    public void deleteCompetence(int id) {
        competencesRepository.deleteById(id);
    }

    public List<Competences> findByCategorieIntitule(String intitule) {
        return competencesRepository.findByCategorieIntitule(intitule);
    }

    public List<Competences> findByNoteGreaterThan(Float note) {
        return competencesRepository.findByNoteGreaterThan(note);
    }
}