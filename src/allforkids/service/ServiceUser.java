/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.User;
import allforkids.util.BCrypt;
import allforkids.util.Config;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*; 

/**
 *
 * @author slim
 */
public class ServiceUser {

    static Config ds = Config.getInstance();

    public void insrerUser(User u) {
        try {
            java.sql.Date sqldate = new Date(u.getDate().getTime());
            String req = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, u.getId());
            ste.setString(2, u.getCin());
            ste.setString(3, u.getNom());
            ste.setString(4, u.getPrenom());
            ste.setString(5, u.getPass());
            ste.setString(6, u.getMail());
            ste.setDate(7, sqldate);
            ste.setString(8, u.getPicture());
            ste.setInt(9, u.getRole());
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(User u, int id) {
        try {
            java.sql.Date sqldate = new Date(u.getDate().getTime());
            String req = "UPDATE user SET  cin= ? ,nom=?,prenom = ? ,email=? , date=?,picture=?,role=? WHERE id_user = ?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, u.getCin());
            ste.setString(2, u.getNom());
            ste.setString(3, u.getPrenom());
            ste.setString(4, u.getMail());
            ste.setDate(5, sqldate);
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
                                result.getString("email"),
                                result.getDate("date"),
                                result.getString("picture"),
                                result.getInt("role"),
                                result.getString("pass")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public User GetUserByMail(String e, Label l) {
        try {
            String req = "SELECT * FROM user where email=?  ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, e);
          

            ResultSet result = ste.executeQuery();
            while (result.next()) {

              User  u = new User(
                        result.getInt("id_user"),
                        result.getString("cin"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getDate("date"),
                        result.getString("picture"),
                        result.getInt("role"),
                
                        result.getString("pass")
                );
                return u;
            }

            
        } catch (SQLException ex) {
            ex.printStackTrace();
            l.setText("verifier votre information ");

        }
        return null ;

    }
    
     

      public User recevoirUser(String username) {
        return null;
       /* try {
          String req = "select username,email,password,nom,prenom,enabled,id,roles from utilisateur where username=?";
            PreparedStatement ps = Connection.prepareStatement(req);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
               // User user = new User( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getInt(7));
              //  user.setRoles(rs.getString(8));
                //return user;
            }
                    
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            l.setText("* verifier vos informations !");
        }
        return null;*/
      }
        
        
       public void changerMDP() {
       }

       
    

     public User GetUserById(int id) {
        try {
            String req = "SELECT * FROM user where id_user=?  ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            
          ste.setInt(1, id);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

              User  u = new User(
                        result.getInt("id_user"),
                        result.getString("cin"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getDate("date"),
                        result.getString("picture"),
                        result.getInt("role"),
                
                        result.getString("pass")
                );
                return u;
            }

            
        } catch (SQLException ex) {
            ex.printStackTrace();
           

        }
        return null ;

    }
    
}
