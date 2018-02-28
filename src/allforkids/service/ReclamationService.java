/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;


import allforkids.entites.Reclamation;
import allforkids.entites.User;
import edu.esprit.all4kids.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ourabi
 */
public class ReclamationService {
    
    private Connection connection;
    private PreparedStatement ps;

    public ReclamationService() {
        
        connection = DataSource.getInstance().getConnection();
    }
    
     public void addReclamation(Reclamation r){
        String req = "insert into reclamation (description,user_id,service_id,action,service_name) values (?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, r.getDescription());
            ps.setInt(2, r.getUser_id()); 
            ps.setInt(3, r.getService_id());              
            ps.setInt(4, r.getAction());
            ps.setString(5, r.getService_name());
            
            
            
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
      public void sendMail(String userMail,String pass,String sujet,String contenu) throws MessagingException {
    
        
        String to = "ali.nemri@esprit.tn";
        String host = "smtp.gmail.com";
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put( "mail.smtp.host", host );
        prop.put("mail.smtp.user", userMail);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userMail));
         
        InternetAddress toAdresse = new InternetAddress(to);
                
        msg.setRecipient(Message.RecipientType.TO, toAdresse);
        msg.setSubject(sujet);
        msg.setContent(contenu,"text/html; charset=utf-8");
        Transport transport = session.getTransport("smtp");
        transport.connect(host, userMail, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    
          
        
        
    }
    
     public void edit(Reclamation r) {
       // String req="UPDATE reclamation SET description='"+r.getDescription()+"',description='"+s.getDescription()+"',prix='"+String.valueOf(s.getPrix())+"',categorie='"+s.getCategorie()+"',image='"+s.getImage()+"'WHERE id="+s.getId();
         
        
        try {
        //    ps = connection.prepareStatement(req);
           
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteReclamation(int id) {
        String req = "delete from reclamation where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<Reclamation> lister() throws ParseException, SQLException {

        ObservableList <Reclamation> list = FXCollections.observableArrayList();
        ResultSet rs; 
        String req = "SELECT * FROM reclamation where action = 1";
       
            ps = connection.prepareStatement(req);
            rs = ps.executeQuery(req);
            User u ;
            ServiceUser us = new ServiceUser();
            
            while (rs.next()) {
               Reclamation r = new Reclamation(rs.getInt("id"), rs.getString("description"), rs.getInt("user_id"), rs.getInt("service_id"), rs.getInt("action"), rs.getString("service_name"));
               u = us.GetUserById( rs.getInt("user_id"));
              // r.setUserName(u.getNom());
               list.add(r);
            }
            return list;
            
            
            
       

}
    
    
    
    public ObservableList<Reclamation> myReclamation(int id) throws ParseException, SQLException {

        ObservableList <Reclamation> list = FXCollections.observableArrayList();
        ResultSet rs; 
        String req = "SELECT * FROM reclamation where user_id ="+id;
       
            ps = connection.prepareStatement(req);
            rs = ps.executeQuery(req);
            
            while (rs.next()) {
               Reclamation r = new Reclamation(rs.getInt("id"), rs.getString("description"), rs.getInt("user_id"), rs.getInt("service_id"), rs.getInt("action"), rs.getString("service_name"));
                if(rs.getInt("action")==1){ r.setTraite("traite");}
                else{r.setTraite("en attente");              
                }
               list.add(r);
            }
            return list;
    }
    
    public ObservableList<Reclamation> lister2() throws ParseException, SQLException {

        ObservableList <Reclamation> list = FXCollections.observableArrayList();
        ResultSet rs; 
        String req = "SELECT * FROM reclamation where action = 0";
       
            ps = connection.prepareStatement(req);
            rs = ps.executeQuery(req);
            
            while (rs.next()) {
               Reclamation r = new Reclamation(rs.getInt("id"), rs.getString("description"), rs.getInt("user_id"), rs.getInt("service_id"), rs.getInt("action"), rs.getString("service_name"));
                list.add(r);
            }
            return list;
       

}
    
    public void acceptService(int id) {
        String req="UPDATE reclamation SET action = 1 WHERE id="+id;
         
        
        try {
            ps = connection.prepareStatement(req);
           
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
