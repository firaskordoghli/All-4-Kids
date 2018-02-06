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
public class Metier {
    private int id_user;
    private int id_etablismment;
    private int role;

    public Metier(int id_user, int id_etablismment, int role) {
        this.id_user = id_user;
        this.id_etablismment = id_etablismment;
        this.role = role;
    }

    public Metier() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_etablismment() {
        return id_etablismment;
    }

    public void setId_etablismment(int id_etablismment) {
        this.id_etablismment = id_etablismment;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_user;
        hash = 79 * hash + this.id_etablismment;
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
        final Metier other = (Metier) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_etablismment != other.id_etablismment) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Metier{" + "id_user=" + id_user + ", id_etablismment=" + id_etablismment + ", role=" + role + '}';
    }
    
    
}
