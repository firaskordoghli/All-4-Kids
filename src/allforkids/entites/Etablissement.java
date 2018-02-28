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
public class Etablissement {

    private int id;
    private String nom;
    private String description;
    private String type;
    private String region;
    private String ville;
    private String image;
    private String verification;
    private int id_user;

    public Etablissement(int id,String nom, String description, String type, String region, String ville,String image,String verification) {
       
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.image=image;
        this.verification=verification;
    }

    public Etablissement(String nom, String type, String region, String ville, String description,String image,String verification) {
     
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.image=image;
        this.verification=verification;
    }
    public Etablissement(String nom, String type, String region, String ville, String description,String image) {
     
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.image=image;
    }

    public Etablissement(int id, String nom, String description, String type, String region, String ville, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.image = image;
    }
    
    public Etablissement(int id,String nom, String type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }

    public Etablissement(int id, String nom, String description, String type, String region, String ville, String image, String verification, int id_user) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.image = image;
        this.verification = verification;
        this.id_user = id_user;
    }
    public Etablissement(int id, String nom, String description, String type, String region, String ville, String image, int id_user) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.image = image;
        this.id_user = id_user;
    }

   

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    


    public Etablissement() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    @Override
    public String toString() {
        return "etablissement{" + /*"verification=" + verification + ", note=" + note +*/ ", nom=" + nom + ", description=" + description + ", type=" + type + ", region=" + region + ", ville=" + ville + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
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
        final Etablissement other = (Etablissement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
