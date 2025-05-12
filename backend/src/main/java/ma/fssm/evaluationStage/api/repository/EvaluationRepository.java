package ma.fssm.evaluationStage.api.repository;

import ma.fssm.evaluationStage.api.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    List<Evaluation> findByCategorie(String categorie);

    @Query("SELECT e FROM Evaluation e WHERE e.valeur_evaluation LIKE %:valeur%")
    List<Evaluation> findByValeurEvaluation(@Param("valeur_evaluation") String valeur_evaluation);


}