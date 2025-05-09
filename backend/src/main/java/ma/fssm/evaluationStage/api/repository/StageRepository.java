package ma.fssm.evaluationStage.api.repository;

import ma.fssm.evaluationStage.api.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Integer> {
    List<Stage> findByEntreprise(String entreprise);
    List<Stage> findByStagiaires_Id(Integer id);
    List<Stage> findByObjectifContaining(String objectif);
}