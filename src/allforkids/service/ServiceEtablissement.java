/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Etablissement;
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
public class ServiceEtablissement {

    static Config ds = Config.getInstance();

    public void insrerEtablissement(Etablissement e) {
        try {
            String req = "INSERT INTO `etablissement`(`nom`, `type`, `region`, `ville`, `description`,`verification`,`note`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            // ste.setInt(1, e.getId());
         
            //ste.setFloat(3, e.getNote());*/
            ste.setString(1, e.getNom());
            ste.setString(2, e.getType());
            ste.setString(3, e.getRegion());
            ste.setString(4, e.getVille());
            ste.setString(5, e.getDescription());
            ste.setBoolean(6, e.isVerification());
            ste.setFloat(7, e.getNote());

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public void updateEtablissement(Etablissement e, int id) {
        try {
            String req = "UPDATE etablissement SET  verification= ? ,note=?,nom = ? ,description=? , type=?,region=?,ville=? WHERE id_etablissement = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
             
            ste.setBoolean(1, e.isVerification());
            ste.setFloat(2, e.getNote());
            ste.setString(3, e.getNom());
            ste.setString(4, e.getDescription());
            ste.setString(5, e.getType());
            ste.setString(6, e.getRegion());
            ste.setString(7, e.getVille());
            ste.setInt(8, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteEtablissement(int id) {
        try {
            String req = "DELETE FROM etablissement WHERE id_etablissement = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Etablissement> selectEtablissement() {
        List<Etablissement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM etablissement ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Etablissement(
                                result.getInt("id_etablissement"),
                                result.getBoolean("verification"),
                                result.getFloat("not"),
                                result.getString("nom"),
                                result.getString("description"),
                                result.getString("type"),
                                result.getString("region"),
                                result.getString("ville")
 
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }*/
}
