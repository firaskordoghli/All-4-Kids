/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Evenement;
import allforkids.util.Config;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceEvenement {

    static Config ds = Config.getInstance();
    private final static Logger log = Logger.getLogger(ServiceEvenement.class.getName());

    public void insrerEvenement(Evenement e) {
        try {

            java.sql.Date sqldate = new Date(e.getDate().getTime());
            String req = "INSERT INTO evenement VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, e.getId_evenement());
            ste.setString(2, e.getNom());
            ste.setString(3, e.getLieu());
            ste.setDate(4, sqldate);
            ste.setInt(5, e.getType());
            ste.setInt(6, e.getNbr_participation());
            ste.setBoolean(7, e.isEtat());
            ste.setInt(8, e.getId_user());
            ste.setString(9, e.getPhoto());
            ste.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.INFO, "{0}Erorr ServiceEvenement.insrerEvenement : ", ex.getMessage());
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEvenement(Evenement e, int id_evenement) {
        try {
            java.sql.Date sqldate = new Date(e.getDate().getTime());
            String req = "UPDATE evenement SET  nom= ? ,lieu=?,date = ? ,type=? , nbr_participation=?,etat=? WHERE id_evenement = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, e.getNom());
            ste.setString(2, e.getLieu());
            ste.setDate(3, sqldate);
            ste.setInt(4, e.getType());
            ste.setInt(5, e.getNbr_participation());
            ste.setBoolean(6, e.isEtat());
            ste.setInt(7, id_evenement);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteEvenement(int id) {
        try {
            String req = "DELETE FROM evenement WHERE id = ?";
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
                                result.getDate("date"),
                                result.getInt("type"),
                                result.getInt("nbr_participation"),
                                result.getBoolean("etat"),
                                result.getInt("id_user"),
                                result.getString("photo")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public String getImgByid(int id)
    { 
      String s = "" ;  
           try {
            String req = "SELECT * FROM evenement where id_evenement=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
        
            ste.setInt(1,id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                s = result.getString("photo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return s ; 
    }

}
