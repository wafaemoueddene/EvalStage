package com.example.EvaluationStage.controller;

import com.example.EvaluationStage.entity.Tuteur;
import com.example.EvaluationStage.service.TuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tuteurs")
public class TuteurController {

    @Autowired
    private TuteurService tuteurService;

    @GetMapping
    public List<Tuteur> getAllTuteurs() {
        return tuteurService.getAllTuteurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tuteur> getTuteurById(@PathVariable int id) {
        Tuteur tuteur = tuteurService.getTuteurById(id);
        if (tuteur != null) {
            return ResponseEntity.ok(tuteur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/entreprise")
    public List<Tuteur> getTuteursByEntreprise(@RequestParam String entreprise) {
        return tuteurService.getTuteursByEntreprise(entreprise);
    }

    @GetMapping("/email")
    public ResponseEntity<Tuteur> getTuteurByEmail(@RequestParam String email) {
        Optional<Tuteur> tuteur = tuteurService.getTuteurByEmail(email);
        return tuteur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Tuteur> getTuteurByNomAndPrenom(
            @RequestParam String nom, @RequestParam String prenom) {
        Optional<Tuteur> tuteur = tuteurService.getTuteurByNomAndPrenom(nom, prenom);
        return tuteur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tuteur saveTuteur(@RequestBody Tuteur tuteur) {
        return tuteurService.saveTuteur(tuteur);
    }

    @DeleteMapping("/{id}")
    public void deleteTuteur(@PathVariable int id) {
        tuteurService.deleteTuteur(id);
    }
}