/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Livre;
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
public class ServiceLivre {

    static Config ds = Config.getInstance();

    public void insrerLivre(Livre l) {
        try {
            String req = "INSERT INTO livre VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, l.getId_livre());
            ste.setString(2, l.getNom());
            ste.setString(3, l.getCategorie());
            ste.setString(4, l.getDescription());
            ste.setString(5, l.getType());
            ste.setInt(6, l.getGood());
            ste.setInt(7, l.getBad());
            ste.setString(8, l.getPhoto());
            ste.setString(9, l.getUrl());
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLivre(Livre l, int id) {
        try {
            String req = "UPDATE livre SET  nom= ?,categorie=? ,description=?,type = ? ,good=? , bad=? ,photo=?, url= ? WHERE id_livre = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, l.getNom());
            ste.setString(2, l.getCategorie());
            ste.setString(3, l.getDescription());
            ste.setString(4, l.getType());
            ste.setInt(5, l.getGood());
            ste.setInt(6, l.getBad());
            ste.setString(7, l.getPhoto());
            ste.setString(8, l.getUrl());
            ste.setInt(9, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteLivre(int id) {
        try {
            String req = "DELETE FROM livre WHERE id_livre = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Livre> selectLivre() {
        List<Livre> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM livre ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Livre(
                                result.getInt("id_livre"),
                                result.getString("nom"),
                                result.getString("categorie"),
                                result.getString("description"),
                                result.getString("type"),
                                result.getInt("good"),
                                result.getInt("bad"),
                                result.getString("photo"),
                                result.getString("url")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Livre> selectLivreByCatg(String categ) {
        List<Livre> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM livre where categorie= ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, categ);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new Livre(
                                result.getInt("id_livre"),
                                result.getString("nom"),
                                result.getString("categorie"),
                                result.getString("description"),
                                result.getString("type"),
                                result.getInt("good"),
                                result.getInt("bad"),
                                result.getString("photo"),
                                result.getString("url")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Livre selectLivreByName(String nom) {
        Livre l = null;
        try {
            String req = "SELECT * FROM livre where nom= ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, nom);
            ResultSet result = ste.executeQuery();
            while (result.next()) {

                l = new Livre(
                        result.getInt("id_livre"),
                        result.getString("nom"),
                        result.getString("categorie"),
                        result.getString("description"),
                        result.getString("type"),
                        result.getInt("good"),
                        result.getInt("bad"),
                        result.getString("photo"),
                        result.getString("url")
                );

            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public Livre getIdByName(String nom) {
        Livre l = new Livre();
        try {
            String req = "SELECT * FROM livre where nom=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setString(1, nom);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                l.setId_livre(result.getInt("id_livre"));
                l.setNom(result.getString("nom"));
                l.setCategorie(result.getString("categorie"));
                l.setDescription(result.getString("description"));
                l.setType(result.getString("type"));
                l.setGood(result.getInt("good"));
                l.setBad(result.getInt("bad"));
                l.setPhoto(result.getString("photo"));
                l.setUrl(result.getString("url"));

            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }

        return l;
    }
}
