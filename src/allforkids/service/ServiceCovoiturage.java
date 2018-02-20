/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Covoiturage;
import allforkids.entites.Transport;
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
 * @author firas
 */
public class ServiceCovoiturage {
       static Config ds = Config.getInstance();
       
    
    public void insrerCov(Transport t) {
        try {
            String req = "INSERT INTO trasnsport (region,ville,depart,arrivé,description,telephone,place,frais)"
                    + " VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, t.getRegion());
            ste.setString(2, t.getVille());
            ste.setString(3, t.getDepart());
            ste.setString(4, t.getArrivé());
            ste.setString(5, t.getDescription());
            ste.setString(6, t.getTelephone());
            ste.setString(7, t.getPlace());
            ste.setString(8, t.getFrais());
            
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
            String req = "DELETE FROM trasnsport WHERE id_transport = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Transport> selectCov() {
        List<Transport> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM trasnsport ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Transport(
                                result.getInt("id_transport"),
                                result.getString("region"),
                                result.getString("ville"),
                                result.getString("depart"),
                                result.getString("arrivé"),
                                result.getString("description"),
                                result.getString("telephone"),
                                result.getString("place"),
                                result.getString("frais"),
                                result.getInt("type")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Transport> selectCovById(int id) {
        List<Transport> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM trasnsport where id_transport = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1,id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Transport(
                                result.getInt("id_transport"),
                                result.getString("region"),
                                result.getString("ville"),
                                result.getString("depart"),
                                result.getString("arrivé"),
                                result.getString("description"),
                                result.getString("telephone"),
                                result.getString("place"),
                                result.getString("frais"),
                                result.getInt("type")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    

}
