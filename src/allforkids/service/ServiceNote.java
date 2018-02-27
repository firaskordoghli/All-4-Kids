/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Note;
import allforkids.util.Config;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author FATNASSI
 */
public class ServiceNote {
    static Config ds = Config.getInstance();
    
    public void insrerMoyenne(Note n) {
        try {

            String req = "INSERT INTO `note` ( `id_user`,`moyenne` ) VALUES (?,?) ";
                   
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            
            ste.setInt(1, n.getId_user());
            ste.setFloat(2, n.getMoyenne());

            ste.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void deleteMoyenne (int id) {
        try {
            String req = "DELETE FROM note WHERE id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);;
        }
    }
    
    public Note SelectMoyenneById(int id) {
        try {
            String req = "SELECT * FROM note where id_user=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, id);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                Note u = new Note(
                        result.getInt("id_user"),
                        result.getFloat("moyenne")
                );
                return u;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }
    
    public int statistiquesupp(int id) {
        try {
            String req = "SELECT COUNT(*) AS nb FROM note WHERE moyenne AND id_etablissement=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            
            ste.setInt(1, id);
            
            ResultSet result=ste.executeQuery();
            while(result.next())
            {
                return result.getInt("nb");    
            }
            
        } catch (Exception e) {
            System.out.println(e);    
        }
        return -1;
    }
    
    
}
