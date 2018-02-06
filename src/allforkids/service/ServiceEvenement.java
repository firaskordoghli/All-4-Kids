/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Evenement;
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
 * @author FATNASSI
 */
public class ServiceEvenement {
    static Config ds = Config.getInstance();

    public void insrerUser(Evenement e) {
        try {
            String req = "INSERT INTO evenement VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, e.getId_evenement());
            ste.setString(2, e.getNom());
            ste.setString(3, e.getLieu());
            ste.setString(4, e.getDate());
            ste.setInt(5, e.getType());
            ste.setInt(6, e.getNbr_participation());
            ste.setBoolean(7, e.isEtat());
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(Evenement e, int id_evenement) {
        try {
            String req = "UPDATE evenement SET  nom= ? ,lieu=?,date = ? ,type=? , nbr_participation=?,etat=?, WHERE id_evenement = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, e.getNom());
            ste.setString(2, e.getLieu());
            ste.setString(3, e.getDate());
            ste.setInt(4, e.getType());
            ste.setInt(5, e.getNbr_participation());
            ste.setBoolean(6, e.isEtat());
            ste.setInt(7, id_evenement);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteUser(int id) {
        try {
            String req = "DELETE FROM user WHERE id = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Evenement> selectEvenement() {
        List<Evenement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM evenement ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Evenement(
                                result.getInt("id_evenement"),
                                result.getString("nom"),
                                result.getString("lieu"),
                                result.getString("prenom"),
                                result.getInt("type"),
                                result.getInt("nbr_participation"),
                                result.getBoolean("etat")
                                
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
