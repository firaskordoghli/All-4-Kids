/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.entites;

import java.sql.Date;

/**
 *
 * @author Ourabi
 */
public class Service {
    
        private int id;
        private String nom;
        private String description;
        private double prix;
        private java.sql.Date date;
        private String categorie;
        private int user_id;
        private String image;
        private int enabled;

    public Service(int id, String nom, String description, double prix, String categorie, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.image = image;
    }
        
        

    public Service(int id, String nom, String description, double prix, Date date, String categorie, int user_id, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.date = date;
        this.categorie = categorie;
        this.user_id = user_id;
        this.image = image;
    }

    public Service(String nom, String description, double prix, Date date, String categorie, int user_id, String image, int enabled) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.date = date;
        this.categorie = categorie;
        this.user_id = user_id;
        this.image = image;
        this.enabled = enabled;
    }
    
    
    

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
        
    
    
    
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
        
        
        
}


