package fr.ul.miage.service;

import fr.ul.miage.entite.Compte;
import fr.ul.miage.repertoire.CompteRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    private CompteRepertoire compteRepository;

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

}
