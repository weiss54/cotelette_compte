package fr.ul.miage.service;

import fr.ul.miage.entite.Connection;
import fr.ul.miage.repertoire.ConnectionRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConnectionService {

    @Autowired
    public ConnectionRepertoire connectionRepertoire;

    public ResponseEntity<Boolean> verifierClef(String clef) {
        Optional<Connection> connection = connectionRepertoire.findByClef(clef);
        if (connection.isPresent()) {
            Connection c = connection.get();
            if (c.isNotExpired()) {
                //TODO changer la date de fin
                c.updateDateFin();
                connectionRepertoire.save(c);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                connectionRepertoire.delete(c);
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
