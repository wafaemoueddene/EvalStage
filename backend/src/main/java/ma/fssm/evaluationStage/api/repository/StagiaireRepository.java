package ma.fssm.evaluationStage.api.repository;

import ma.fssm.evaluationStage.api.entity.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Integer> {
    List<Stagiaire> findByInstitution(String institution);
    Optional<Stagiaire> findByEmail(String email);
    Optional<Stagiaire> findByNomAndPrenom(String nom, String prenom);

}