/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.entites;

/**
 *
 * @author slim
 */
public class Covoiturage {

    private int id_covoiturage;
    private String type;
    private String time;
    private String depart;
    private String arrive;
    private int nbr_place;
    private float prix;
    private int etat;
    private int id_user;

    public Covoiturage(int id_covoiturage, String type, String time, String depart, String arrive, int nbr_place, float prix, int etat,int id_user) {
        this.id_covoiturage = id_covoiturage;
        this.type = type;
        this.time = time;
        this.depart = depart;
        this.arrive = arrive;
        this.nbr_place = nbr_place;
        this.prix = prix;
        this.etat = etat;
        this.id_user = id_user;
    }

    public Covoiturage() {
    }

    public int getId_covoiturage() {
        return id_covoiturage;
    }

    public void setId_covoiturage(int id_covoiturage) {
        this.id_covoiturage = id_covoiturage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
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
        hash = 89 * hash + this.id_covoiturage;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Covoiturage other = (Covoiturage) obj;
        if (this.id_covoiturage != other.id_covoiturage) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Covoiturage{" + "type=" + type + ", time=" + time + ", depart=" + depart + ", arrive=" + arrive + ", nbr_place=" + nbr_place + ", prix=" + prix + ", etat=" + etat + ", id_user=" + id_user + '}';
    }

}
