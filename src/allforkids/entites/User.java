/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.entites;

import java.util.Date;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author slim
 */
public class User {

    private int id;
    private String cin;
    private String nom;
    private String prenom;
    private String mail;
    private Date date;
    private String picture;
    private int role;
    private String pass;

    public User() {
    }

    public User(int id, String cin, String nom, String prenom, String mail, Date date, String picture, int role, String pass) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.date = date;
        this.picture = picture;
        this.role = role;
        this.pass = pass;
    }

    public User(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(String cin, String nom, String prenom, String mail, Date date, String picture, int role, String pass) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.date = date;
        this.picture = picture;
        this.role = role;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.mail);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "cin=" + cin + ", nom=" + ", prenom=" + prenom + ", mail=" + mail + ", date=" + date.toString() + ", picture=" + picture + ", role=" + role + '}';
    }

}
