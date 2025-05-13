package ma.fssm.evaluationStage.api.controller;

import ma.fssm.evaluationStage.api.dto.EvaluationDTO;
import ma.fssm.evaluationStage.api.entity.Evaluation;
import ma.fssm.evaluationStage.api.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;


    @GetMapping
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @GetMapping("/{id}")
    public Evaluation getEvaluationById(@PathVariable int id) {
        return evaluationService.getEvaluationById(id);
    }

    @GetMapping("/categorie")
    public List<Evaluation> getEvaluationsByCategorie(@RequestParam String categorie) {
        return evaluationService.getEvaluationsByCategorie(categorie);
    }

    @GetMapping("/valeur")
    public List<Evaluation> getEvaluationsByValeur(@RequestParam String valeur) {
        return evaluationService.getEvaluationsByValeur(valeur);
    }

    @PostMapping
    public List<Evaluation> saveEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        // Mapper les données du DTO vers les entités
        List<Evaluation> evaluations = evaluationService.mapDTOToEvaluation(evaluationDTO);

        // Renvoyer les évaluations sauvegardées
        return evaluations;
    }


    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable int id) {
        evaluationService.deleteEvaluation(id);
    }
}