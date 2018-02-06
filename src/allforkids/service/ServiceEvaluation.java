/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Evaluation;
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
public class ServiceEvaluation {
       static Config ds = Config.getInstance();

    public void insrerEvaluation(Evaluation e) {
        try {
            String req = "INSERT INTO evaluation VALUES(?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, e.getId_eleve());
            ste.setInt(2, e.getId_etablissment());
            ste.setString(3, e.getAbsence());
            ste.setString(4, e.getMatiere());
            ste.setFloat(4, e.getNote());
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEvaluation(Evaluation e, int id , int id2) {
        try {
            String req = "UPDATE evaluation SET  absence= ? ,matiere=?, note=? WHERE id_etablissment=?  and id_eleve = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
             
            
            ste.setString(1, e.getAbsence());
            ste.setString(2, e.getMatiere());
            ste.setFloat(3, e.getNote());
             ste.setInt(4, id);
            ste.setInt(5, id2);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteEvaluation(int id , int id2) {
        try {
            String req = "DELETE FROM evaluation WHERE id_etablissment=?  and id_eleve = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Evaluation> selectEvaluation() {
        List<Evaluation> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM evaluation ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Evaluation(
                                result.getInt("id_eleve"),
                                result.getInt("id_etablissment"),
                                 result.getString("absence"),
                                result.getString("matiere"),
                                 result.getFloat("note")
                              
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
