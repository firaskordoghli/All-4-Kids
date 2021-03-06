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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author slim
 */
public class ServiceEtablissement {

    static Config ds = Config.getInstance();

    public void insrerEtablissement(Etablissement e,int u) {
        try {
            System.out.println(e);
            String req = "INSERT INTO `etablissement` ( `nom`, `type`, `region`, `ville`, `description`, `image`, `verification`, `id_user`) VALUES ( ?, ?, ?, ?, ?, ?, 'Non valide',?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

                
            
            ste.setString(1, e.getNom());
            ste.setString(2, e.getType());
            ste.setString(3, e.getRegion());
            ste.setString(4, e.getVille());
            ste.setString(5, e.getDescription());
            ste.setString(6, e.getImage());
            ste.setInt(7, u);
            

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEtablissement(Etablissement e, int id) {
        try {
            String req = "UPDATE etablissement SET nom=?,type=?,region=?,ville=?,description=?,image=? WHERE id_etablissement = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setString(1, e.getNom());
            ste.setString(2, e.getType());
            ste.setString(3, e.getRegion());
            ste.setString(4, e.getVille());
            ste.setString(5, e.getDescription());
            ste.setString(6, e.getImage());
            ste.setInt(7, id);
            ste.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);  
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
                                result.getString("nom"),
                                result.getString("type"),
                                result.getString("region"),
                                result.getString("ville"),
                                result.getString("description"),
                                result.getString("image"),
                                result.getString("verification")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Etablissement> selectEtablissement1() throws SQLException {
        ObservableList<Etablissement> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM etablissement where verification='Valide'";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Etablissement(
                                result.getInt("id_etablissement"),
                                result.getString("nom"),
                                result.getString("type")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ObservableList<Etablissement> selectEtablissement2() throws SQLException {
        ObservableList<Etablissement> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM etablissement";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Etablissement(
                                result.getInt("id_etablissement"),
                                result.getString("nom"),
                                result.getString("type")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Etablissement GetEtablissemebtById(int id) {
        try {
            String req = "SELECT * FROM etablissement where id_etablissement=? ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, id);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                Etablissement u = new Etablissement(
                        result.getInt("id_etablissement"),
                        result.getString("nom"),
                        result.getString("description"),
                        result.getString("type"),
                        result.getString("region"),
                        result.getString("ville"),
                        result.getString("image"),
                        result.getString("verification")
                );
                return u;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }

    public static int GetLastId() {
        try {
            String req = "SELECT  Max(id_etablissement) as id FROM etablissement  ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            //ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                int a = result.getInt("id");

                return a + 1;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return 0;

    }

    public ObservableList<Etablissement> selectEtablissementById(int id) throws SQLException {
        ObservableList<Etablissement> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM etablissement where  id_user=?";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Etablissement(
                                result.getInt("id_etablissement"),
                                result.getString("nom"),
                                result.getString("type"),
                                result.getString("region"),
                                result.getString("ville"),
                                result.getString("description"),
                                result.getString("image"),
                                result.getString("verification")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ObservableList<Etablissement> selectEtablissementById2(int id) throws SQLException {
        ObservableList<Etablissement> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM etablissement WHERE id_user=? AND verification='Valide'";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Etablissement(
                                result.getInt("id_etablissement"),
                                result.getString("nom"),
                                result.getString("type")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
        public void ValiderEtablissement(Etablissement e, int id){
        try {
            String req = "UPDATE `etablissement` SET `verification` = 'Valide' WHERE `etablissement`.`id_etablissement` =?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);  
        }
        
        }
}
