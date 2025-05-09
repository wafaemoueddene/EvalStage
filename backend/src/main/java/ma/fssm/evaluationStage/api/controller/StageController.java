package ma.fssm.evaluationStage.api.controller;

import ma.fssm.evaluationStage.api.entity.Stage;
import ma.fssm.evaluationStage.api.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping
    public List<Stage> getAllStages() {
        return stageService.getAllStages();
    }

    @GetMapping("/{id}")
    public Stage getStageById(@PathVariable int id) {
        return stageService.getStageById(id);
    }

    @GetMapping("/entreprise")
    public List<Stage> getStagesByEntreprise(@RequestParam String entreprise) {
        return stageService.getStagesByEntreprise(entreprise);
    }

    @GetMapping("/stagiaire/{stagiaireId}")
    public List<Stage> getStagesByStagiaireId(@PathVariable Integer stagiaireId) {
        return stageService.getStagesByStagiaireId(stagiaireId);
    }

    @GetMapping("/objectif")
    public List<Stage> getStagesByObjectifContaining(@RequestParam String objectif) {
        return stageService.getStagesByObjectifContaining(objectif);
    }

    @PostMapping
    public Stage saveStage(@RequestBody Stage stage) {
        return stageService.saveStage(stage);
    }

    @DeleteMapping("/{id}")
    public void deleteStage(@PathVariable int id) {
        stageService.deleteStage(id);
    }
}