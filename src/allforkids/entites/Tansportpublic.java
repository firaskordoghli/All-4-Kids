/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.entites;

import java.util.Objects;

/**
 *
 * @author slim
 */
public class Tansportpublic {

    private String nom_transp;
    private String time;
    private String satation;
    private String trajet;
    private String type;
    private float fraix;

    public Tansportpublic(String nom_transp, String time, String satation, String trajet, String type, float fraix) {
        this.nom_transp = nom_transp;
        this.time = time;
        this.satation = satation;
        this.trajet = trajet;
        this.type = type;
        this.fraix = fraix;
    }

    public Tansportpublic() {
    }

    public String getNom_transp() {
        return nom_transp;
    }

    public void setNom_transp(String nom_transp) {
        this.nom_transp = nom_transp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSatation() {
        return satation;
    }

    public void setSatation(String satation) {
        this.satation = satation;
    }

    public String getTrajet() {
        return trajet;
    }

    public void setTrajet(String trajet) {
        this.trajet = trajet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getFraix() {
        return fraix;
    }

    public void setFraix(float fraix) {
        this.fraix = fraix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nom_transp);
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
        final Tansportpublic other = (Tansportpublic) obj;
        if (!Objects.equals(this.nom_transp, other.nom_transp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tansportpublic{" + "nom_transp=" + nom_transp + ", time=" + time + ", satation=" + satation + ", trajet=" + trajet + ", type=" + type + ", fraix=" + fraix + '}';
    }

}
