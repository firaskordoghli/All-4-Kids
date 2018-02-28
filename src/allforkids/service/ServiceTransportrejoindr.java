/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Participevenement;
import allforkids.entites.Transport;
import allforkids.entites.Transportrejoindr;
import allforkids.entites.User;
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
public class ServiceTransportrejoindr {
     static Config ds = Config.getInstance();

    public void insrerTransportrejoindr(Transportrejoindr p) {
        try {
            String req = "INSERT INTO transportrejoindr VALUES(?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, p.getId_transport());
            ste.setInt(2, p.getId_user());
            ste.executeUpdate();
            
            String req1 = "UPDATE trasnsport SET place =  place -1 WHERE id_transport = ?";
            PreparedStatement ste1 = ds.getConnection().prepareStatement(req1);
            ste1.setInt(1, p.getId_transport());
            ste1.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Transportrejoindr> getCovUser(Transportrejoindr p) {
        List<Transportrejoindr> list = new ArrayList<>();
        try {
            String req = "select * FROM transportrejoindr WHERE id_transport = ? and id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, p.getId_transport());
            ste.setInt(2, p.getId_user());
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Transportrejoindr(
                                result.getInt("id_transport"),
                                result.getInt("id_user")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
    }

  

    public  void deleteTransportrejoindr(int id , int id2) {
        try {
            String req = "DELETE FROM transportrejoindr WHERE 	id_transport = ? and id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.setInt(2, id2);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Transportrejoindr> selectTransportrejoindr() {
        List<Transportrejoindr> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM transportrejoindr ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Transportrejoindr(
                                result.getInt("id_transport"),
                                result.getInt("id_user")
                            
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<Transport> selectTransportrejoindhist( int id ) {
        List<Transport> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM transportrejoindr tr ,trasnsport t "
                    + "where t.id_transport = tr.id_transport AND tr.id_user =? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
             ste.setInt(1, id);
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
                                result.getString("type"),
                                result.getDate("date"),
                                result.getString("arriveName"),
                                result.getString("departName")
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
}
