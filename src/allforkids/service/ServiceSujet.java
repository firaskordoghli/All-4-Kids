/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Commentaire;
import allforkids.entites.Sujet;
import static allforkids.service.ServiceCommentaire.ds;
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
public class ServiceSujet {
      static Config ds = Config.getInstance();

    public void insrerSujet(Sujet s) {
        try {
            String req = "INSERT INTO sujet VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, s.getId_sujet());
            ste.setString(2,s.getTitle());
            ste.setString(3, s.getDescription());
            ste.setString(4, s.getTag());
            ste.setInt(5, s.getGood());
            ste.setInt(6, s.getBad());
            ste.setBoolean(7, s.isVisibilite());
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSujet(Sujet s, int id) {
        try {
            String req = "UPDATE sujet SET  title= ? ,description=?,tag = ? ,good=? , bad=?,visibilite=? WHERE id_Sujet = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
           ste.setString(1,s.getTitle());
            ste.setString(2, s.getDescription());
            ste.setString(3, s.getTag());
            ste.setInt(4, s.getGood());
            ste.setInt(5, s.getBad());
            ste.setBoolean(6, s.isVisibilite());
            ste.setInt(7, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteSujet(int id) {
        try {
            String req = "DELETE FROM sujet WHERE id_sujet = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Sujet> selectSujet() {
        List<Sujet> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM sujet ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Sujet(
                                result.getInt("id_sujet"),
                                result.getString("title"),
                                result.getString("description"),
                                result.getString("tag"),
                                result.getInt("good"),
                                result.getInt("bad"),
                                result.getBoolean("visibilite")
                           
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<Sujet> GetSujetByIdd(int id_sujet) {
        List<Sujet> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM Sujet where id_sujet=?  ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id_sujet);
          

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                list.add(new Sujet(
                        result.getString("title"),
                        result.getString("description"),
                        result.getString("tag"),
                         result.getInt("good"),
                         result.getInt("bad"),
                         result.getBoolean("visibilite")
                       
                ));
                return list;
            }

            
        } catch (SQLException ex) {
            ex.printStackTrace();
            

        }
        return null ;

    }
}
    
    

