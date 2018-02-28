/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Rejoindre;
import allforkids.util.Config;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author FATNASSI
 */
public class ServiceRejoindre {

    static Config ds = Config.getInstance();

    public void insrerRejoindre(int a, int b) {
        try {

            String req = "INSERT INTO `rejoindre` (`id_etablissement`, `id_user`, `verification` )"
                    + " VALUES (?,?,'Non valide');";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, a);
            ste.setInt(2, b);

            ste.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public Rejoindre SelectIfDejaExiste(int a,int b) {
        
        try {
            String req = "SELECT * FROM rejoindre where id_etablissement=? AND id_user=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, a);
            ste.setInt(2, b);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                Rejoindre r = new Rejoindre(
                        result.getInt("id_etablissement"),
                        result.getInt("id_user"),
                        result.getString("verification")
                );return r ;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }
    public Rejoindre GetIdUserById(int id) {
        try {
            String req = "SELECT * FROM rejoindre where id_etablissement=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, id);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                Rejoindre r = new Rejoindre(
                        result.getInt("id_etablissement"),
                        result.getInt("id_user"),
                        result.getString("verification")
                );
                return r;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }
    public List<Rejoindre> selectIdUserById(int id) throws SQLException {
        List<Rejoindre> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM rejoindre where  id_etablissement=?";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Rejoindre(
                                result.getInt("id_etablissement"),
                                result.getInt("id_user"),
                                result.getString("verification")
                        )
                );
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
    public  void deleteEleve(int id,int id2) {
        try {
            String req = "DELETE FROM rejoindre WHERE id_etablissement = ? AND id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.setInt(2, id2);
            ste.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);       
        }
    }
    
    public void accepterEleve(int id, int id2){
        try {
            String req = "UPDATE `rejoindre` SET `verification` = 'Valide' WHERE `rejoindre`.`id_etablissement` =? AND `rejoindre`.`id_user` =?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            
            ste.setInt(1, id);
            ste.setInt(2, id2);
            ste.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);  
        }
        
        }
    
    /*public Rejoindre getmoyennebyiduser(int id) {
        try {
            String req = "select moyenne from note n join rejoindre r where r.id_user=n.id_user and r.user=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, id);

            ResultSet result = ste.executeQuery();
            
            while (result.next()) {

                Rejoindre r = new Rejoindre(
                        result.getInt("id_etablissement"),
                        result.getInt("id_user"),
                        result.getString("verification")
                );
                return r;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }*/
}
