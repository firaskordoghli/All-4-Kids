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
public class Listeattente {
    private int num ;
    private int id_user ;
    private int id_evenmment ;

    public Listeattente(int num, int id_user, int id_evenmment) {
        this.num = num;
        this.id_user = id_user;
        this.id_evenmment = id_evenmment;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_evenmment() {
        return id_evenmment;
    }

    public void setId_evenmment(int id_evenmment) {
        this.id_evenmment = id_evenmment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_user;
        hash = 97 * hash + this.id_evenmment;
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
        final Listeattente other = (Listeattente) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_evenmment != other.id_evenmment) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Listeattente{" + "num=" + num + ", id_user=" + id_user + ", id_evenmment=" + id_evenmment + '}';
    }
    
}
