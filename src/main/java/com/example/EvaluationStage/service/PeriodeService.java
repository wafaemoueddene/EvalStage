package com.example.EvaluationStage.service;

import com.example.EvaluationStage.entity.Periode;
import com.example.EvaluationStage.repository.PeriodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PeriodeService {

    @Autowired
    private PeriodeRepository periodeRepository;

    public List<Periode> getAllPeriodes() {
        return periodeRepository.findAll();
    }

    public Periode getPeriodeById(int id) {
        return periodeRepository.findById(id).orElse(null);
    }

    public List<Periode> getPeriodesByDateRange(LocalDate startDate, LocalDate endDate) {
        return periodeRepository.findByDateDebutBetween(startDate, endDate);
    }

    public List<Periode> getPeriodesByDateFinBefore(LocalDate date) {
        return periodeRepository.findByDateFinBefore(date);
    }

    public Periode savePeriode(Periode periode) {
        return periodeRepository.save(periode);
    }

    public void deletePeriode(int id) {
        periodeRepository.deleteById(id);
    }
}