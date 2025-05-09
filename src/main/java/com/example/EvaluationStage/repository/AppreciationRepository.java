package com.example.EvaluationStage.repository;

import com.example.EvaluationStage.entity.Appreciation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppreciationRepository extends JpaRepository<Appreciation, Integer> {
    List<Appreciation> findByEvaluation_Id(Long evaluationId);
    List<Appreciation> findByCompetences_Id(Long competencesId);
}