package ma.fssm.evaluationStage.api.repository;

import ma.fssm.evaluationStage.api.entity.Competences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetencesRepository extends JpaRepository<Competences, Integer> {

    @Query("SELECT c FROM Competences c WHERE c.categorie.intitule_categorie = :intitule_categorie")
    List<Competences> findByCategorieIntitule(@Param("intitule_categorie") String intitule);
    @Query("SELECT c FROM Competences c WHERE c.note > :note")
    List<Competences> findByNoteGreaterThan(@Param("note") Float note);
//    List<Competences> findByIntituleCompetenceCategorie(String categorie);
//    List<Competences> findByNoteGreaterThan(Float note);

}