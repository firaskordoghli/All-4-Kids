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
public class Transportrejoindr {
      private int id_transport ;
      private int id_user ;

    public Transportrejoindr(int id_transport, int id_user) {
        this.id_transport = id_transport;
        this.id_user = id_user;
    }

    public Transportrejoindr() {
    }

    public int getId_transport() {
        return id_transport;
    }

    public void setId_transport(int id_transport) {
        this.id_transport = id_transport;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_transport;
        hash = 29 * hash + this.id_user;
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
        final Transportrejoindr other = (Transportrejoindr) obj;
        if (this.id_transport != other.id_transport) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transportrejoindr{" + "id_transport=" + id_transport + ", id_user=" + id_user + '}';
    }
      
}
