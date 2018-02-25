/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Evenement;
import allforkids.entites.Participevenement;
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
public class ServiceParticipevenement {
      static Config ds = Config.getInstance();

    public void insrerParticipevenement(Participevenement p) {
        try {
            String req = "INSERT INTO participevenement VALUES(?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, p.getId_evenement());
            ste.setInt(2, p.getId_user());
            ste.setInt(3, p.getType());
     
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateParticipevenement(Participevenement p, int id,int id2) {
        try {
            String req = "UPDATE participevenement SET  type= ? WHERE  id_evenement=? and id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
             
            ste.setInt(1, p.getType());
            ste.setInt(2, id);
            ste.setInt(3, id2);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteParticipevenement(int id , int id2) {
        try {
            String req = "DELETE FROM participevenement WHERE id_evenement = ? and id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.setInt(2, id2);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Participevenement> selectParticipevenement() {
        List<Participevenement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM participevenement ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Participevenement(
                                result.getInt("id_evenement"),
                                result.getInt("id_user"),
                                result.getInt("type")
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Participevenement> selectParticipevenementByid(int id) {
          List<Participevenement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM participevenement where id_user =? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Participevenement(
                                result.getInt("id_evenement"),
                                result.getInt("id_user"),
                                result.getInt("type")
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Evenement> selectEvenementByid(int id) {
          List<Evenement> list = new ArrayList<>();
        try {
            String req = "SELECT * From evenement e , participevenement p where p.id_user=? and e.id_evenement=p.id_evenement ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Evenement(
                                result.getInt("id_evenement"),
                                result.getString("nom"),
                                result.getString("lieu"),
                                result.getDate("date"),
                                result.getString("type"),
                                result.getInt("nbr_participation"),
                                result.getBoolean("etat"),
                                result.getInt("id_user"),
                                result.getString("photo"),
                                result.getDouble("latitude"),
                                result.getDouble("longitude")
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public Participevenement selectParticipevenementByid2(int id,int id2) {
          Participevenement e = null ;
        try {
            String req = "SELECT * FROM participevenement where id_user =? and id_evenement=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.setInt(2, id2);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
              
                       e = new Participevenement(
                                result.getInt("id_evenement"),
                                result.getInt("id_user"),
                                result.getInt("type")
                        );
                        
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
       public int nbparticipent(int id) {
          int nb = 0 ;
        try {
            String req = "SELECT  COUNT(*) as nb FROM participevenement where  id_evenement=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            
            ResultSet result = ste.executeQuery();
            while (result.next()) {
              
                    
                         nb = result.getInt("nb");
                              
                        
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb;
    }
}
