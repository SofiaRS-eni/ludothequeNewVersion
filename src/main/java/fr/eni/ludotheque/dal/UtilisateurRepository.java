package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Utilisateur;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findUtilisateurByUsername(String username);
}
