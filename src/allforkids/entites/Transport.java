/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.entites;

/**
 *
 * @author DELL
 */
public class Transport {
    private int id_transport;
    private String region;
    private String ville;
    private String depart;
    private String arrivé;
    private String description;
    private String telephone;
    private String place;
    private String frais;
    private int type;

    public Transport(int id_transport, String region, String ville, String depart, String arrivé, String description, String telephone, String place, String frais, int type) {
        this.id_transport = id_transport;
        this.region = region;
        this.ville = ville;
        this.depart = depart;
        this.arrivé = arrivé;
        this.description = description;
        this.telephone = telephone;
        this.place = place;
        this.frais = frais;
        this.type = type;
    }

    public Transport(String region, String ville, String depart, String arrivé, String description, String telephone,
            String place, String frais) {
        this.region = region;
        this.ville = ville;
        this.depart = depart;
        this.arrivé = arrivé;
        this.description = description;
        this.telephone = telephone;
        this.place = place;
        this.frais = frais;
    }


    public int getId_transport() {
        return id_transport;
    }
    
    public String getRegion() {
        return region;
    }

    public String getVille() {
        return ville;
    }

    public String getDepart() {
        return depart;
    }

    public String getArrivé() {
        return arrivé;
    }

    public String getDescription() {
        return description;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPlace() {
        return place;
    }

    public String getFrais() {
        return frais;
    }

    public int getType() {
        return type;
    }

    public void setId_transport(int id_transport) {
        this.id_transport = id_transport;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setArrivé(String arrivé) {
        this.arrivé = arrivé;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setFrais(String frais) {
        this.frais = frais;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id_transport;
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
        final Transport other = (Transport) obj;
        if (this.id_transport != other.id_transport) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transport{" + "id_transport=" + id_transport + ", region=" + region + ", ville=" + ville + ", depart=" + depart + ", arriv\u00e9=" + arrivé + ", description=" + description + ", telephone=" + telephone + ", place=" + place + ", frais=" + frais + ", type=" + type + '}';
    }
    
    
}
