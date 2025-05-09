package ma.fssm.evaluationStage.api.service;

import ma.fssm.evaluationStage.api.entity.Evaluation;
import ma.fssm.evaluationStage.api.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Evaluation getEvaluationById(int id) {
        return evaluationRepository.findById(id).orElse(null);
    }

    public List<Evaluation> getEvaluationsByCategorie(String categorie) {
        return evaluationRepository.findByCategorie(categorie);
    }

    public List<Evaluation> getEvaluationsByValeur(String valeur) {
        return evaluationRepository.findByValeurEvaluation(valeur);
    }

    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(int id) {
        evaluationRepository.deleteById(id);
    }
}