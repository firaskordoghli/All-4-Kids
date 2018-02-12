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
public class Livre {
    private int id_livre;
    private String nom;
    private String categorie;
    private String description;
    private String type;
    private int good;
    private int bad;

    public Livre(int id_livre, String nom, String categorie, String description, String type, int good, int bad) {
        this.id_livre = id_livre;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.type = type;
        this.good = good;
        this.bad = bad;
    }

    public Livre( String nom, String categorie, String description, String type, int good, int bad) {
       
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.type = type;
        this.good = good;
        this.bad = bad;
    }

    public Livre() {
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash = 23 * hash + this.id_livre;
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
        final Livre other = (Livre) obj;
        if (this.id_livre != other.id_livre) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livre{" + "id_livre=" + id_livre + ", nom=" + nom + ", categorie=" + categorie + ", description=" + description + ", type=" + type + ", good=" + good + ", bad=" + bad + '}';
    }
    
    
    
    
}
