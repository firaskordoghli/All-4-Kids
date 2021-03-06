/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Evenement;
import allforkids.entites.User;
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
            String req = "INSERT INTO evenement VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, e.getId_evenement());
            ste.setInt(2, e.getId_user());
            ste.setString(3, e.getNom());
            ste.setString(4, e.getDescriptionn());
            ste.setDate(5, sqldate);
            ste.setString(6, e.getType());
            ste.setInt(7, e.getNbr_participation());
            ste.setBoolean(8, e.isEtat());
           
            ste.setString(9, e.getPhoto());
            ste.setDouble(10, e.getLatitude());
            ste.setDouble(11, e.getLongitude());
            ste.setTime(12,e.getTemp());
            ste.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.INFO, "{0}Erorr ServiceEvenement.insrerEvenement : ", ex.getMessage());
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEvenement(Evenement e, int id_evenement) {
        try {
            java.sql.Date sqldate = new Date(e.getDate().getTime());
            String req = "UPDATE evenement SET  nom= ? ,descriptionn=?,date = ? ,type=? , nbr_participation=?,etat=?,photo=?,latitude=?,longitude=?, temp = ? WHERE id_evenement = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, e.getNom());
            ste.setString(2, e.getDescriptionn());
            ste.setDate(3, sqldate);
            ste.setString(4, e.getType());
            ste.setInt(5, e.getNbr_participation());
            ste.setBoolean(6, e.isEtat());
            ste.setString(7, e.getPhoto());
            ste.setDouble(8, e.getLatitude());
            ste.setDouble(9, e.getLongitude());
            ste.setTime(10, e.getTemp());
            ste.setInt(11, id_evenement);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public  void deleteEvenement(int id) {
        try {
            String req = "DELETE FROM evenement WHERE id_evenement = ?";
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
                                result.getString("descriptionn"),
                                result.getDate("date"),
                                result.getString("type"),
                                result.getInt("nbr_participation"),
                                result.getBoolean("etat"),
                                result.getInt("id_user"),
                                result.getString("photo"),
                                result.getDouble("latitude"),
                                result.getDouble("longitude"),
                                result.getTime("temp")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String getImgByid(int id) {
        String s = "";
        try {
            String req = "SELECT * FROM evenement where id_evenement=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                s = result.getString("photo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }

        return s;
    }

    public Evenement getIdByName(String nom) {
        Evenement e = new Evenement();
        try {
            String req = "SELECT * FROM evenement where nom=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setString(1, nom);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                e.setId_evenement(result.getInt("id_evenement"));
                e.setNom(result.getString("nom"));
                e.setDescriptionn(result.getString("descriptionn"));
                e.setDate(result.getDate("date"));
                e.setType(result.getString("type"));
                e.setNbr_participation(result.getInt("nbr_participation"));
                e.setEtat(result.getBoolean("etat"));
                e.setId_user(result.getInt("id_user"));
                e.setPhoto(result.getString("photo"));
                e.setLatitude( result.getDouble("latitude"));   
                e.setLongitude(result.getDouble("longitude"));
                e.setTemp(result.getTime("temp"));

            }
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }

        return e;
    }
      public List<Evenement> selectMesEvenement( int id) {
        List<Evenement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM evenement Where id_user = ? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Evenement(
                                result.getInt("id_evenement"),
                                result.getString("nom"),
                                result.getString("descriptionn"),
                                result.getDate("date"),
                                result.getString("type"),
                                result.getInt("nbr_participation"),
                                result.getBoolean("etat"),
                                result.getInt("id_user"),
                                result.getString("photo"),
                                result.getDouble("latitude"),
                                result.getDouble("longitude"),
                                result.getTime("temp")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public String selectUsers( int id) {
        String a = "";
        try {
            String req = "SELECT concat(u.nom,' ', u.prenom) as ut From user u , evenement e WHERE e.id_user=? and u.id =e.id_user";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
              
                     
                      a = result.getString("ut");
                        
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
       public  void deleteEvenementdepas() {
        try {
            String req = "DELETE FROM evenement WHERE Date <  SYSDATE() ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
           
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
