package ma.fssm.evaluationStage.api.repository;

import ma.fssm.evaluationStage.api.entity.Appreciation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppreciationRepository extends JpaRepository<Appreciation, Integer> {
    List<Appreciation> findByEvaluation_Id(Long evaluationId);
    List<Appreciation> findByCompetences_Id(Long competencesId);
}