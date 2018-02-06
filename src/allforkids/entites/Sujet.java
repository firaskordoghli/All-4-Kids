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
public class Sujet {

    private int id_sujet;
    private String title;
    private String description;
    private String tag;
    private int good;
    private int bad;
    private boolean visibilite;

    public Sujet(int id_sujet, String title, String description, String tag, int good, int bad, boolean visibilite) {
        this.id_sujet = id_sujet;
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.good = good;
        this.bad = bad;
        this.visibilite = visibilite;
    }

    public Sujet() {
    }

    public int getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public boolean isVisibilite() {
        return visibilite;
    }

    public void setVisibilite(boolean visibilite) {
        this.visibilite = visibilite;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id_sujet;
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
        final Sujet other = (Sujet) obj;
        if (this.id_sujet != other.id_sujet) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sujet{" + "id_sujet=" + id_sujet + ", title=" + title + ", description=" + description + ", tag=" + tag + ", good=" + good + ", bad=" + bad + ", visibilite=" + visibilite + '}';
    }
    
    

}
