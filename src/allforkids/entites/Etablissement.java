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
    private boolean verification;
    private float note;
    private String nom;
    private String description;
    private String type;
    private String region;
    private String ville;

    public Etablissement(int id, boolean verification, float note, String nom, String description, String type, String region, String ville) {
        this.id = id;
        this.verification = verification;
        this.note = note;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.region = region;
        this.ville = ville;
    }

    public Etablissement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
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

    @Override
    public String toString() {
        return "etablissement{" + "verification=" + verification + ", note=" + note + ", nom=" + nom + ", description=" + description + ", type=" + type + ", region=" + region + ", ville=" + ville + '}';
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
