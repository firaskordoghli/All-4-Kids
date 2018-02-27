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
public class Movie {

    private int id_movie;
    private String nom;
    private String url;
    private String img ;

    public Movie(int id_movie, String nom, String url, String img) {
        this.id_movie = id_movie;
        this.nom = nom;
        this.url = url;
        this.img = img ;
    }

    public Movie(String nom, String url,String img) {
        this.nom = nom;
        this.url = url;
        this.img = img ;
    }

    public Movie() {
    }

    public int getId_movie() {
        return id_movie;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

  

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id_movie;
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
        final Movie other = (Movie) obj;
        if (this.id_movie != other.id_movie) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Movie{" + "id_movie=" + id_movie + ", nom=" + nom + ", url=" + url + ", img=" + img + '}';
    }

   

   

}
