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

    public Commentaire(int id_commentaire, String description, int good, int bad) {
        this.id_commentaire = id_commentaire;
        this.description = description;
        this.good = good;
        this.bad = bad;
    }

    public Commentaire(String description, int good, int bad) {
        this.description = description;
        this.good = good;
        this.bad = bad;
    }

    public Commentaire() {
    }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id_commentaire;
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
        final Commentaire other = (Commentaire) obj;
        if (this.id_commentaire != other.id_commentaire) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "description=" + description + ", good=" + good + ", bad=" + bad + '}';
    }
    
}
