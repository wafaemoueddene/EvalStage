package com.example.EvaluationStage.controller;

import com.example.EvaluationStage.entity.Evaluation;
import com.example.EvaluationStage.service.EvaluationService;
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
    public Evaluation saveEvaluation(@RequestBody Evaluation evaluation) {
        return evaluationService.saveEvaluation(evaluation);
    }

    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable int id) {
        evaluationService.deleteEvaluation(id);
    }
}