/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Commentaire;
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
public class ServiceCommentaire {
    static Config ds = Config.getInstance();

    public void insrerCommentaire(Commentaire c) {
        try {
            String req = "INSERT INTO commentaire VALUES(?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, c.getId_commentaire());
            ste.setString(2, c.getDescription());
            ste.setInt(3, c.getGood());
            ste.setInt(4, c.getBad());
     
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCommentaire(Commentaire c, int id) {
        try {
            String req = "UPDATE commentaire SET  description=?, good=? ,bad=? WHERE id_commentaire = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
             
            ste.setString(1, c.getDescription());
            ste.setInt(2, c.getGood());
            ste.setInt(3, c.getBad());
            ste.setInt(4, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteCommentaire(int id) {
        try {
            String req = "DELETE FROM commentaire WHERE id_commentaire = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Commentaire> selectCommentaire() {
        List<Commentaire> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM commentaire ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Commentaire(
                                result.getInt("id_commentaire"),
                                result.getString("description"),
                                result.getInt("good"),
                                result.getInt("bad")
                                
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
