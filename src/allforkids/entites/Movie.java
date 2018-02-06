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
    private String categorie;
    private String movie;

    public Movie(int id_movie, String categorie, String movie) {
        this.id_movie = id_movie;
        this.categorie = categorie;
        this.movie = movie;
    }

    public Movie() {
    }

    public int getId_movie() {
        return id_movie;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
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
        return "Movie{" + "categorie=" + categorie + ", movie=" + movie + '}';
    }

}
