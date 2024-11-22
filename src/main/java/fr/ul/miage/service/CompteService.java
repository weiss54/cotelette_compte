package fr.ul.miage.service;

import fr.ul.miage.entite.Compte;
import fr.ul.miage.entite.Connection;
import fr.ul.miage.repertoire.CompteRepertoire;
import fr.ul.miage.repertoire.ConnectionRepertoire;
import fr.ul.miage.utils.EncrypteurMdp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    private CompteRepertoire compteRepository;

    @Autowired
    private ConnectionService connectionService;
    @Autowired
    private ConnectionRepertoire connectionRepertoire;

    public ResponseEntity<Compte> creerCompte(String nom, String prenom, String email, String mdp) {
        Compte c = new Compte();
        Optional<Compte> compte = compteRepository.findCompteByEmail(email);
        if (compte.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        c.setEmail(email);
        c.setNom(nom);
        c.setPrenom(prenom);
        if (mdp == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        c.setMdp(mdp);
        Compte res = compteRepository.save(c);
        res.setClearMdp();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    public ResponseEntity<Compte> getCompte(Long id) {
        Optional<Compte> compte = compteRepository.findById(id);
        if (compte.isPresent()) {
            Compte c = compte.get();
            c.setClearMdp();
            return new ResponseEntity<>(compte.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Connection> connexion(String email, String password) {
        Optional<Compte> compte = compteRepository.findCompteByEmail(email);
        if (compte.isPresent()) {
            Compte c = compte.get();
            String passwordCrypter = EncrypteurMdp.encryptPassword(password);
            if (c.getMdp().equals(passwordCrypter)) {
                c.setClearMdp();
                //TODO verifier si une connexion existe deja
                Optional<Connection> connection = connectionRepertoire.findById(c.getId());
                if (connection.isPresent()) {
                    Connection c1 = connection.get();
                    connectionRepertoire.delete(c1);
                }
                Connection connection1 = creerClefConnection(c);
                return new ResponseEntity<>(connection1, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Connection creerClefConnection(Compte c) {
        Connection connection = new Connection();
        connection.setIdCompte(c.getId());
        connection.setClef("1234");
        connection.setDatefinvalidite();
        connectionRepertoire.save(connection);
        return connection;
    }

}
