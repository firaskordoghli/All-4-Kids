/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Metier;
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
public class ServiceMetier {
       static Config ds = Config.getInstance();

    public void insrerMetier(Metier m) {
        try {
            String req = "INSERT INTO metier VALUES(?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, m.getId_user());
            ste.setInt(2, m.getId_etablismment());
            ste.setInt(3, m.getRole());
     
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateMetier(Metier m, int id,int id2) {
        try {
            String req = "UPDATE metier SET  ,role=? WHERE id_user = ? and id_etablismment=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
                ste.setInt(1, m.getRole());
             ste.setInt(2, id);
            ste.setInt(3,id2);
         
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteMetier(int id,int id2) {
        try {
            String req = "DELETE FROM metier WHERE id_user = ? and id_etablismment=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
             ste.setInt(2, id2);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Metier> selectMetier() {
        List<Metier> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM metier ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Metier(
                                result.getInt("id_user"),
                                result.getInt("id_etablismment"),
                                result.getInt("role")
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
