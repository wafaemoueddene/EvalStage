package ma.fssm.evaluationStage.api.service;

import ma.fssm.evaluationStage.api.entity.Categorie;
import ma.fssm.evaluationStage.api.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(int id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public List<Categorie> getCategoriesByIntitule(String intitule) {
        return categorieRepository.findByIntituleCategorie(intitule);
    }

    public List<Categorie> getCategoriesByValeurGreaterThan(Float valeur) {
        return categorieRepository.findByValeurCategorieGreaterThan(valeur);
    }

    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteCategorie(int id) {
        categorieRepository.deleteById(id);
    }
}