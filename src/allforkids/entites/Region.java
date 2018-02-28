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
public class Region {
    private int id_region ;
    private String nom_region;

    public Region(int id_region, String nom_region) {
        this.id_region = id_region;
        this.nom_region = nom_region;
    }

    public Region() {
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getNom_region() {
        return nom_region;
    }

    public void setNom_region(String nom_region) {
        this.nom_region = nom_region;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Region other = (Region) obj;
        if (this.id_region != other.id_region) {
            return false;
        }
        return true;
    }
    
    
    
}
