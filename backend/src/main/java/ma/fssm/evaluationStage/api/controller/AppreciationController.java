package ma.fssm.evaluationStage.api.controller;

import ma.fssm.evaluationStage.api.entity.Appreciation;
import ma.fssm.evaluationStage.api.service.AppreciationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appreciations")
public class AppreciationController {

    @Autowired
    private AppreciationService appreciationService;

    @GetMapping
    public List<Appreciation> getAllAppreciations() {
        return appreciationService.getAllAppreciations();
    }

    @GetMapping("/{id}")
    public Appreciation getAppreciationById(@PathVariable int id) {
        return appreciationService.getAppreciationById(id);
    }

    @GetMapping("/evaluation/{evaluationId}")
    public List<Appreciation> getAppreciationsByEvaluationId(@PathVariable Long evaluationId) {
        return appreciationService.getAppreciationsByEvaluationId(evaluationId);
    }

    @GetMapping("/competence/{competenceId}")
    public List<Appreciation> getAppreciationsByCompetencesId(@PathVariable Long competenceId) {
        return appreciationService.getAppreciationsByCompetencesId(competenceId);
    }

    @PostMapping
    public Appreciation saveAppreciation(@RequestBody Appreciation appreciation) {
        return appreciationService.saveAppreciation(appreciation);
    }

    @DeleteMapping("/{id}")
    public void deleteAppreciation(@PathVariable int id) {
        appreciationService.deleteAppreciation(id);
    }
}