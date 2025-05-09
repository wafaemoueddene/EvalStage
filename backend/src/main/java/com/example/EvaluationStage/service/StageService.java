package com.example.EvaluationStage.service;

import com.example.EvaluationStage.entity.Stage;
import com.example.EvaluationStage.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Stage getStageById(int id) {
        return stageRepository.findById(id).orElse(null);
    }

    public List<Stage> getStagesByEntreprise(String entreprise) {
        return stageRepository.findByEntreprise(entreprise);
    }

    public List<Stage> getStagesByStagiaireId(Integer stagiaireId) {
        return stageRepository.findByStagiaires_Id(stagiaireId);
    }

    public List<Stage> getStagesByObjectifContaining(String objectif) {
        return stageRepository.findByObjectifContaining(objectif);
    }

    public Stage saveStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public void deleteStage(int id) {
        stageRepository.deleteById(id);
    }
}