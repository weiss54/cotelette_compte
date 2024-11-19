package fr.ul.miage.repertoire;

import fr.ul.miage.entite.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteRepertoire extends JpaRepository<Compte, Long> {

    public Optional<Compte> findCompteByEmail(String email);


}
