package ma.fssm.evaluationStage.api.controller;

import ma.fssm.evaluationStage.api.entity.Competences;
import ma.fssm.evaluationStage.api.service.CompetencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competences")
public class CompetencesController {

    @Autowired
    private CompetencesService competencesService;

    @GetMapping
    public List<Competences> getAllCompetences() {
        return competencesService.getAllCompetences();
    }

    @GetMapping("/{id}")
    public Competences getCompetenceById(@PathVariable int id) {
        return competencesService.getCompetenceById(id);
    }

    @PostMapping
    public Competences saveCompetence(@RequestBody Competences competence) {
        return competencesService.saveCompetence(competence);
    }

    @DeleteMapping("/{id}")
    public void deleteCompetence(@PathVariable int id) {
        competencesService.deleteCompetence(id);
    }
}