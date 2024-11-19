package fr.ul.miage.entite;

import fr.ul.miage.utils.EncrypteurMdp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String mdp;
    private String nom;
    private String prenom;
    private Timestamp datecreation;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Timestamp getDatecreation() {
        return datecreation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = EncrypteurMdp.encryptPassword(mdp);
    }

    public void setClearMdp() {
        this.mdp = null;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}