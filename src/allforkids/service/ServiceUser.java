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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.event.ActionEvent;
import javax.mail.*;  
import javax.mail.internet.*; 

/**
 *
 * @author slim
 */
public class ServiceUser {

    static Config ds = Config.getInstance();

    public void insrerUser(User u,String ro) {
        try {
            java.sql.Date sqldate = new Date(u.getDate().getTime());
            String req = "INSERT INTO user (`id`, `cin`, `nom`, `prenom`,`password`,`email`, `date`, `picture`, `roles`, `username`,`username_canonical`,`email_canonical`,`enabled`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, u.getId());
            ste.setString(2, u.getCin());
            ste.setString(3, u.getNom());
            ste.setString(4, u.getPrenom());
            ste.setString(5, u.getPass());
            ste.setString(6, u.getMail());
            ste.setDate(7, sqldate);
            ste.setString(8, u.getPicture());
            ste.setString(9,ro);
            ste.setString(10, u.getUsername());
              ste.setString(11, u.getUsername());
               ste.setString(12, u.getMail());
                ste.setInt(13, 1);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(User u, int id) {
        try {
            java.sql.Date sqldate = new Date(u.getDate().getTime());
            String req = "UPDATE user SET  cin= ? ,nom=?,prenom = ? ,email=? , date=?,picture=?,role=? WHERE id = ?";
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
            String req = "DELETE FROM user WHERE id = ?";
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
                                result.getInt("id"),
                                result.getString("cin"),
                                result.getString("nom"),
                                result.getString("prenom"),
                                result.getString("email"),
                                result.getDate("date"),
                                result.getString("picture"),
                                result.getInt("role"),
                                result.getString("password"),
                                result.getString("username")
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

                User u = new User(
                        result.getInt("id"),
                        result.getString("cin"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getDate("date"),
                        result.getString("picture"),
                        result.getInt("role"),
                        result.getString("password"),
                        result.getString("username"),
                        result.getString("roles")
                       
                );
                System.out.println(u.getRoles());
                return u;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            l.setText("verifier votre information ");

        }
        return null;

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

    public static void sendMail2( String to, String cc, String from, String subject, String text, String smtpHost) {
		
            try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", smtpHost);
			Session emailSession = Session.getDefaultInstance(properties);

			Message emailMessage = new MimeMessage(emailSession);
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			emailMessage.setFrom(new InternetAddress(from));
			emailMessage.setSubject(subject);
			emailMessage.setText(text);

			emailSession.setDebug(true);

			Transport.send(emailMessage);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}


    public User GetUserById(int id) {
        try {
            String req = "SELECT * FROM user where id=?  ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, id);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                User u = new User(
                        result.getInt("id"),
                        result.getString("cin"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getDate("date"),
                        result.getString("picture"),
                        result.getInt("role"),
                        result.getString("password"),
                         result.getString("username")
                );
                return u;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }
    
    
     public User GetUserByRole(int role) {
        try {
            String req = "SELECT * FROM user where role=?  ";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, role);

            ResultSet result = ste.executeQuery();
            while (result.next()) {

                User u = new User(
                        result.getInt("id"),
                        result.getString("cin"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getDate("date"),
                        result.getString("picture"),
                        result.getInt("role"),
                        result.getString("password"),
                        result.getString("username")
                );
                return u;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;

    }
    

    public ObservableList<User> GetUserById2(int id) throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM user INNER JOIN rejoindre ON user.id  = rejoindre.id_user WHERE id_etablissement=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new User(
                                result.getInt("id"),
                                result.getString("nom"),
                                result.getString("prenom")
                        )
                );
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;

    }
      public List<User> GetUserById3(int id) throws SQLException {
      List<User> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM user WHERE id=?";
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(
                        new User(
                                result.getInt("id"),
                                result.getString("nom"),
                                result.getString("prenom")
                        )
                );
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;

    }

      
      
        public void changerMDP(String newMdp,int id)
        {
            
            
             try {
            String reqUpdate = "UPDATE user set password=? where id=?";
            PreparedStatement ps = ds.getConnection().prepareStatement(reqUpdate);
          
            ps.setString(1, BCrypt.hashpw(newMdp,BCrypt.gensalt()));   
          
            ps.setInt(2,id);
            ps.executeUpdate();
            
            System.out.println("envoyé");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
        
        
         public String recevoirMDPavecId(int id)
        {
            String MDP ="vide";
            
          try{
              String reqRec = "select pass from user where id=?";
             PreparedStatement ps = ds.getConnection().prepareStatement(reqRec);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                MDP=rs.getString(1);
            }
          }
            
          catch (SQLException ex) {
          ex.printStackTrace();
        }
         return MDP;}
          
}
