package ma.fssm.evaluationStage.api.controller;

import ma.fssm.evaluationStage.api.entity.Stagiaire;
import ma.fssm.evaluationStage.api.service.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stagiaires")
public class StagiaireController {

    @Autowired
    private StagiaireService stagiaireService;

    @GetMapping
    public List<Stagiaire> getAllStagiaires() {
        return stagiaireService.getAllStagiaires();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stagiaire> getStagiaireById(@PathVariable int id) {
        Stagiaire stagiaire = stagiaireService.getStagiaireById(id);
        if (stagiaire != null) {
            return ResponseEntity.ok(stagiaire);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/institution")
    public List<Stagiaire> getStagiairesByInstitution(@RequestParam String institution) {
        return stagiaireService.getStagiairesByInstitution(institution);
    }

    @GetMapping("/email")
    public ResponseEntity<Stagiaire> getStagiaireByEmail(@RequestParam String email) {
        Optional<Stagiaire> stagiaire = stagiaireService.getStagiaireByEmail(email);
        return stagiaire.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Stagiaire> getStagiaireByNomAndPrenom(
            @RequestParam String nom, @RequestParam String prenom) {
        Optional<Stagiaire> stagiaire = stagiaireService.getStagiaireByNomAndPrenom(nom, prenom);
        return stagiaire.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Stagiaire saveStagiaire(@RequestBody Stagiaire stagiaire) {
        return stagiaireService.saveStagiaire(stagiaire);
    }

    @DeleteMapping("/{id}")
    public void deleteStagiaire(@PathVariable int id) {
        stagiaireService.deleteStagiaire(id);
    }
}