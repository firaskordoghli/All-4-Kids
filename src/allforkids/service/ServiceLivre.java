/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Livre;
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
public class ServiceLivre {
       static Config ds = Config.getInstance();

    public void insrerLivre(Livre l) {
        try {
            String req = "INSERT INTO livre VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, l.getId_livre());
            ste.setString(2,l.getNom());
            ste.setString(3, l.getCategorie());
            ste.setString(4, l.getDescription());
            ste.setString(5, l.getType());
            ste.setInt(6, l.getGood());
            ste.setInt(7, l.getBad());
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLivre(Livre l, int id) {
        try {
            String req = "UPDATE livre SET  nom= ?,categorie=? ,description=?,type = ? ,good=? , bad=?  WHERE id_livre = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
           ste.setString(1,l.getNom());
            ste.setString(2, l.getCategorie());
            ste.setString(3, l.getDescription());
            ste.setString(4, l.getType());
            ste.setInt(5, l.getGood());
            ste.setInt(6, l.getBad());
            ste.setInt(7, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteLivre(int id) {
        try {
            String req = "DELETE FROM livre WHERE id_livre = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Livre> selectLivre() {
        List<Livre> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM livre ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Livre(
                                result.getInt("id_livre"),
                                result.getString("nom"),
                                result.getString("categorie"),
                                result.getString("description"),
                                result.getString("type"),
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
