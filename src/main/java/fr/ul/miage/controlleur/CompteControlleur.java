package fr.ul.miage.controlleur;

import fr.ul.miage.entite.Compte;
import fr.ul.miage.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compte")
public class CompteControlleur {

    @Autowired
    private CompteService compteService;

    @PostMapping
    public ResponseEntity<Compte> createUtilisateur(
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String email,
            @RequestParam String password) {
        return compteService.creerCompte(nom, prenom, email, password);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compte> getUtilisateur(@PathVariable Long id) {
        return compteService.getCompte(id);
    }

}
