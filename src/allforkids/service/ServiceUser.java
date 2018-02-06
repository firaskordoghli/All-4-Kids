/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.User;
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
public class ServiceUser {

    static Config ds = Config.getInstance();

    public void insrerUser(User u) {
        try {
            String req = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, u.getId());
            ste.setString(2, u.getCin());
            ste.setString(3, u.getNom());
            ste.setString(4, u.getPrenom());
            ste.setString(5, u.getMail());
            ste.setString(6, u.getDate());
            ste.setString(7, u.getPicture());
            ste.setInt(8, u.getRole());
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(User u, int id) {
        try {
            String req = "UPDATE user SET  cin= ? ,nom=?,prenom = ? ,mail=? , date=?,picture=?,role=?, WHERE id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, u.getCin());
            ste.setString(2, u.getNom());
            ste.setString(3, u.getPrenom());
            ste.setString(4, u.getMail());
            ste.setString(5, u.getDate());
            ste.setString(6, u.getPicture());
            ste.setInt(7, u.getRole());
            ste.setInt(8, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteUser(int id) {
        try {
            String req = "DELETE FROM user WHERE id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> selectUser() {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM user ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new User(
                                result.getInt("id_user"),
                                result.getString("cin"),
                                result.getString("nom"),
                                result.getString("prenom"),
                                result.getString("mail"),
                                result.getString("date"),
                                result.getString("picture"),
                                result.getInt("role")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
