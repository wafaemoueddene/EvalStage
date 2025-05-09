package ma.fssm.evaluationStage.api.service;

import ma.fssm.evaluationStage.api.entity.Appreciation;
import ma.fssm.evaluationStage.api.repository.AppreciationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppreciationService {

    @Autowired
    private AppreciationRepository appreciationRepository;

    public List<Appreciation> getAllAppreciations() {
        return appreciationRepository.findAll();
    }

    public Appreciation getAppreciationById(int id) {
        return appreciationRepository.findById(id).orElse(null);
    }

    public List<Appreciation> getAppreciationsByEvaluationId(Long evaluationId) {
        return appreciationRepository.findByEvaluation_Id(evaluationId);
    }

    public List<Appreciation> getAppreciationsByCompetencesId(Long competencesId) {
        return appreciationRepository.findByCompetences_Id(competencesId);
    }

    public Appreciation saveAppreciation(Appreciation appreciation) {
        return appreciationRepository.save(appreciation);
    }

    public void deleteAppreciation(int id) {
        appreciationRepository.deleteById(id);
    }
}