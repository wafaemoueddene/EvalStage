package ma.fssm.evaluationStage.api.controller;

import ma.fssm.evaluationStage.api.entity.Periode;
import ma.fssm.evaluationStage.api.service.PeriodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/periodes")
public class PeriodeController {

    @Autowired
    private PeriodeService periodeService;

    @GetMapping
    public List<Periode> getAllPeriodes() {
        return periodeService.getAllPeriodes();
    }

    @GetMapping("/{id}")
    public Periode getPeriodeById(@PathVariable int id) {
        return periodeService.getPeriodeById(id);
    }

    @GetMapping("/between")
    public List<Periode> getPeriodesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return periodeService.getPeriodesByDateRange(startDate, endDate);
    }

    @GetMapping("/before")
    public List<Periode> getPeriodesByDateFinBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return periodeService.getPeriodesByDateFinBefore(date);
    }

    @PostMapping
    public Periode savePeriode(@RequestBody Periode periode) {
        return periodeService.savePeriode(periode);
    }

    @DeleteMapping("/{id}")
    public void deletePeriode(@PathVariable int id) {
        periodeService.deletePeriode(id);
    }
}