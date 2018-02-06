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
public class Participevenement {

    private int id_evenement;
    private int id_user;
    private int type;

    public Participevenement(int id_evenement, int id_user, int type) {
        this.id_evenement = id_evenement;
        this.id_user = id_user;
        this.type = type;
    }

    public Participevenement() {
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.id_evenement;
        hash = 13 * hash + this.id_user;
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
        final Participevenement other = (Participevenement) obj;
        if (this.id_evenement != other.id_evenement) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

}
