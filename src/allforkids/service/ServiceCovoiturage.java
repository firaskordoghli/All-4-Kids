/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Covoiturage;
import allforkids.util.Config;
import java.sql.Date;
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
public class ServiceCovoiturage {
       static Config ds = Config.getInstance();

    public void insrerCovoiturage(Covoiturage c) {
        try {
            java.sql.Date sqldate = new Date(c.getTime().getTime())   ;
            String req = "INSERT INTO covoiturage VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, c.getId_covoiturage());
            ste.setString(2, c.getType());
            ste.setDate(3, sqldate);
            ste.setString(4, c.getDepart());
            ste.setString(5, c.getArrive());
            ste.setInt(6, c.getNbr_place());
            ste.setFloat(7, c.getPrix());
            ste.setInt(8, c.getEtat());
            ste.setInt(9, c.getId_user());
     
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCovoiturage(Covoiturage c, int id) {
        try {
            java.sql.Date sqldate = new Date(c.getTime().getTime())   ;
            String req = "UPDATE covoiturage SET  type= ? ,time=? , depart=? , "
                    + "arrive=? ,nbr_place=? prix=? etat=? ,id_user=? WHERE id_covoiturage = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, c.getType());
            ste.setDate(2, sqldate);
            ste.setString(3, c.getDepart());
            ste.setString(4, c.getArrive());
            ste.setInt(5, c.getNbr_place());
            ste.setFloat(6, c.getPrix());
            ste.setInt(7, c.getEtat());
            ste.setInt(8, c.getId_user());
            ste.setInt(9,id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteCovoiturage(int id) {
        try {
            String req = "DELETE FROM covoiturage WHERE id_covoiturage = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Covoiturage> selectCovoiturage() {
        List<Covoiturage> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM covoiturage ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Covoiturage(
                                result.getInt("id_covoiturage"),
                                result.getString("type"),
                                result.getDate("time"),
                               result.getString("depart"),
                                 result.getString("arrive"),
                                 result.getInt("nbr_place"),
                                 result.getFloat("prix"),
                                 result.getInt("etat"),
                                 result.getInt("id_user")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
