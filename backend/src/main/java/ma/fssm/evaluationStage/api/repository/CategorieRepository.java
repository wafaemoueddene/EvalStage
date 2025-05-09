package ma.fssm.evaluationStage.api.repository;

import ma.fssm.evaluationStage.api.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    @Query("SELECT c FROM Categorie c WHERE c.intitule_categorie = :intitule_categorie")
    List<Categorie> findByIntituleCategorie(@Param("intitule_categorie") String intitule_categorie);
//    List<Categorie> findByIntituleCategorie(String intitule_categorie);

    @Query("SELECT c FROM Categorie c WHERE c.valeur_categorie > :valeur_categorie")
    List<Categorie> findByValeurCategorieGreaterThan(@Param("valeur_categorie") Float valeur_categorie);
//    List<Categorie> findByValeur_categorieGreaterThan(Float valeur_categorie);


}