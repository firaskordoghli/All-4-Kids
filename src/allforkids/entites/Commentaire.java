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
public class Commentaire {
    private int id_commentaire ;
    private String description ;
    private  int good ;
    private int bad ;
      private  int id_user ;
    private int id_sujet ;

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }

    public Commentaire(int id_commentaire, String description, int good, int bad, int id_user, int id_sujet) {
        this.id_commentaire = id_commentaire;
        this.description = description;
        this.good = good;
        this.bad = bad;
        this.id_user = id_user;
        this.id_sujet = id_sujet;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_commentaire=" + id_commentaire + ", description=" + description + ", good=" + good + ", bad=" + bad + ", id_user=" + id_user + ", id_sujet=" + id_sujet + '}';
    }

    public Commentaire(String description, int id_user, int id_sujet) {
        this.description = description;
        this.id_user = id_user;
        this.id_sujet = id_sujet;
    }

    public Commentaire(String description, int good, int bad, int id_user, int id_sujet) {
        this.description = description;
        this.good = good;
        this.bad = bad;
        this.id_user = id_user;
        this.id_sujet = id_sujet;
    }
    

    
    
}
