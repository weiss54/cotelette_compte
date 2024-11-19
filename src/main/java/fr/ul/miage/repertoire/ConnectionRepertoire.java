package fr.ul.miage.repertoire;

import fr.ul.miage.entite.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConnectionRepertoire extends JpaRepository<Connection, Long> {

    public Optional<Connection> findByClef(String clef);

}
