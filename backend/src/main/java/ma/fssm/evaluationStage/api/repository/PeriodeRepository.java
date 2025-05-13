package ma.fssm.evaluationStage.api.repository;
import ma.fssm.evaluationStage.api.entity.Periode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PeriodeRepository extends JpaRepository<Periode, Integer> {
    List<Periode> findByDateDebutBetween(LocalDate dateDebut, LocalDate dateFin);
    List<Periode> findByDateFinBefore(LocalDate date);

}