package ma.fssm.evaluationStage.api.repository;

import ma.fssm.evaluationStage.api.entity.Tuteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TuteurRepository extends JpaRepository<Tuteur, Integer> {
    List<Tuteur> findByEntreprise(String entreprise);
    Optional<Tuteur> findByEmail(String email);
    Optional<Tuteur> findByNomAndPrenom(String nom, String prenom);

}