package fr.ul.miage.entite;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table
public class Connection {

    @Id
    private Long idcompte;
    private String clef;
    private Timestamp datecreation = new Timestamp(System.currentTimeMillis());
    private Timestamp datefinvalidite;

    public Long getIdcompte() {
        return idcompte;
    }

    public String getClef() {
        return clef;
    }

    public Timestamp getDatecreation() {
        return datecreation;
    }

    public Timestamp getDatefinvalidite() {
        return datefinvalidite;
    }

    public void setIdCompte(Long idCompte) {
        this.idcompte = idCompte;
    }

    public void setClef(String clef) {
        this.clef = clef;
    }

    public void setDatefinvalidite() {
        this.datefinvalidite = new Timestamp(System.currentTimeMillis()+86400000);
    }

    public boolean isNotExpired() {
        return datefinvalidite.before(new Timestamp(System.currentTimeMillis()));
    }

    public void updateDateFin() {
        //TODO
    }
}
