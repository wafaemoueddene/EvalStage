package ma.fssm.evaluationStage.api.controller;

import ma.fssm.evaluationStage.api.entity.Categorie;
import ma.fssm.evaluationStage.api.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getCategorieById(@PathVariable int id) {
        return categorieService.getCategorieById(id);
    }

    @GetMapping("/search")
    public List<Categorie> getCategoriesByIntitule(@RequestParam String intitule) {
        return categorieService.getCategoriesByIntitule(intitule);
    }

    @GetMapping("/filter")
    public List<Categorie> getCategoriesByValeurGreaterThan(@RequestParam Float valeur) {
        return categorieService.getCategoriesByValeurGreaterThan(valeur);
    }

    @PostMapping
    public Categorie saveCategorie(@RequestBody Categorie categorie) {
        return categorieService.saveCategorie(categorie);
    }

    @DeleteMapping("/{id}")
    public void deleteCategorie(@PathVariable int id) {
        categorieService.deleteCategorie(id);
    }
}