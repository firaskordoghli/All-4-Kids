/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;


import allforkids.entites.Service;
import allforkids.entites.Session;
import edu.esprit.all4kids.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import java.util.Properties;


/**
 *
 * @author Ourabi
 */
public class ServiceService {
    
    private Connection connection;
    private PreparedStatement ps;

    public ServiceService() {
        connection = DataSource.getInstance().getConnection();
    }

    public void addService(Service s){
        String req = "insert into service (nom,description,prix,date,categorie,image,user_id,enabled) values (?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, s.getNom());
            ps.setString(2, s.getDescription());
            ps.setDouble(3, s.getPrix());
            ps.setDate(4, s.getDate());
            ps.setString(5, s.getCategorie());  
            
            ps.setString(6, s.getImage());
            ps.setInt(7, s.getUser_id());              
            ps.setInt(8, s.getEnabled());
            
            
            
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     public void edit(Service s) {
        String req="UPDATE service SET nom='"+s.getNom()+"',description='"+s.getDescription()+"',prix='"+String.valueOf(s.getPrix())+"',categorie='"+s.getCategorie()+"',image='"+s.getImage()+"'WHERE id="+s.getId();
         
        
        try {
            ps = connection.prepareStatement(req);
           
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
     public void acceptService(int id) {
        String req="UPDATE service SET enabled = 1 WHERE id="+id;
         
        
        try {
            ps = connection.prepareStatement(req);
           
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteService(int id) {
        String req = "delete from service where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<Service> lister() throws ParseException, SQLException {

        ObservableList <Service> list = FXCollections.observableArrayList();
        ResultSet rs; 
        String req = "SELECT * FROM service where enabled = 1";
       
            ps = connection.prepareStatement(req);
            rs = ps.executeQuery(req);
            
            while (rs.next()) {
               Service s = 
new Service(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getDouble("prix"), rs.getDate("date"), rs.getString("categorie"), rs.getInt("user_id"), rs.getString("image"));
               
                list.add(s);
            }
            return list;
       

}
    
    public ObservableList<Service> listerAttente() throws ParseException, SQLException {

        ObservableList <Service> list = FXCollections.observableArrayList();
        ResultSet rs; 
        String req = "SELECT * FROM service where enabled = 0";
       
            ps = connection.prepareStatement(req);
            rs = ps.executeQuery(req);
            
            while (rs.next()) {
               Service s = 
new Service(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getDouble("prix"), rs.getDate("date"), rs.getString("categorie"), rs.getInt("user_id"), rs.getString("image"));
               
                list.add(s);
            }
            return list;
       

}
    
   
    
   
    
    
}
