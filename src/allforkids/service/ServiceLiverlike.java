/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Liverlike;
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
public class ServiceLiverlike {
    
       static Config ds = Config.getInstance();

    public void insrerLiverlike(Liverlike m) {
        try {
            String req = "INSERT INTO liverlike VALUES(?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, m.getId_liver());
            ste.setInt(2, m.getId_user());
            ste.setInt(3, m.getGood());
            ste.setInt(4, m.getBad());
     
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLiverlike(Liverlike m, int id ,int id2) {
        try {
            String req = "UPDATE liverlike SET  good= ? ,bad=? WHERE id_liver = ? and id_user=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
             
            ste.setInt(1, m.getGood());
            ste.setInt(2, m.getBad());
            ste.setInt(3, id);
            ste.setInt(4, id2);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteLiverlike(int id, int id2) {
        try {
            String req = "DELETE FROM liverlike WHERE id_liver = ? and id_user= ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.setInt(2, id2);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Liverlike> selectliverlike() {
        List<Liverlike> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM liverlike ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Liverlike(
                                result.getInt("id_liver"),
                                result.getInt("id_user"),
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
      public Liverlike selectliverlikeByIdlivreuser(int id ,int id2) {
       Liverlike l = new Liverlike();
        try {
            String req = "SELECT * FROM liverlike where id_liver = ? and id_user= ? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.setInt(2, id2);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
               
                   l =     new Liverlike(
                                result.getInt("id_liver"),
                                result.getInt("id_user"),
                                result.getInt("good"),
                                result.getInt("bad")
                     
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
}
