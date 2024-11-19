package fr.ul.miage.entite;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table
public class Connection {

    @Id
    private String idCompte;
    private String clef;
    private Timestamp datecreation = new Timestamp(System.currentTimeMillis());
    private Timestamp datefinvalidite;

    public String getIdCompte() {
        return idCompte;
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

    public void setDatefinvalidite(Timestamp datefinvalidite) {
        this.datefinvalidite = datefinvalidite;
    }

    public boolean isNotExpired() {
        return datefinvalidite.before(new Timestamp(System.currentTimeMillis()));
    }

    public void updateDateFin() {
        //TODO
    }
}
