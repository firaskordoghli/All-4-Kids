/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Movie;
import allforkids.util.Config;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slim
 */
public class ServiceMovie {
      static Config ds = Config.getInstance();

    public void insrerMovie(Movie m) {
        try {
            String req = "INSERT INTO movie VALUES(?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, m.getId_movie());
            ste.setString(2, m.getCategorie());
            ste.setString(3, m.getMovie());
     
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateMovie(Movie m, int id) {
        try {
            String req = "UPDATE movie SET  categorie= ? ,movie=? WHERE id_movie = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
             
            ste.setString(1, m.getCategorie());
            ste.setString(2, m.getMovie());
            ste.setInt(3, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteMovie(int id) {
        try {
            String req = "DELETE FROM movie WHERE id_movie = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Movie> selectMovie() {
        List<Movie> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM movie ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Movie(
                                result.getInt("id_movie"),
                                result.getString("categorie"),
                                result.getString("movie")
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
