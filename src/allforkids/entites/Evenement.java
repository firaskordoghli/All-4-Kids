/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.entites;

/**
 *
 * @author FATNASSI
 */
public class Evenement {
    
    private int id_evenement;
    private String nom;
    private String lieu;
    private String date;
    private int type;
    private int nbr_participation;
    private boolean etat;
    private int id_user;

    public Evenement(int id_evenement, String nom, String lieu, String date, int type, int nbr_participation, boolean etat) {
        this.id_evenement = id_evenement;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.type = type;
        this.nbr_participation = nbr_participation;
        this.etat = etat;
    }

    public Evenement() {
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNbr_participation() {
        return nbr_participation;
    }

    public void setNbr_participation(int nbr_participation) {
        this.nbr_participation = nbr_participation;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id_evenement;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id_evenement != other.id_evenement) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_evenement=" + id_evenement + ", nom=" + nom + ", lieu=" + lieu + ", date=" + date + ", type=" + type + ", nbr_participation=" + nbr_participation + ", etat=" + etat + ", id_user=" + id_user + '}';
    }

    
    
    
    
    
            
    
}
