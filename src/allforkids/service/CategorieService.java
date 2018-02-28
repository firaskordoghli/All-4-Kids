/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;


import allforkids.entites.Categorie;
import edu.esprit.all4kids.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ourabi
 */
public class CategorieService {
    
    private Connection connection;
    private PreparedStatement ps;

    public CategorieService() {
        connection = DataSource.getInstance().getConnection();
    }
    
    
    public void addCategorie(Categorie c){
        
        String req = "insert into categorie_service (nom) values (?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, c.getNom());                      
            
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteCategorie(int id) {
        String req = "delete from categorie_service where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editCategorie(Categorie c) {
        String req="UPDATE categorie_service SET nom='"+c.getNom()+"'WHERE id="+c.getId();
         
        
        try {
            ps = connection.prepareStatement(req);
           
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     public ObservableList<Categorie> lister() throws ParseException, SQLException {

        ObservableList <Categorie> list = FXCollections.observableArrayList();
        ResultSet rs; 
        String req = "SELECT * FROM categorie_service";
      
            ps = connection.prepareStatement(req);
            rs = ps.executeQuery(req);
            
            while (rs.next()) {
               Categorie e = 
                        new Categorie(rs.getInt("id"),rs.getString("nom"));

               
                list.add(e);
            }
            return list;
     }
     
     public ObservableList<String> listerCombo() throws ParseException, SQLException {

        ObservableList <String> list = FXCollections.observableArrayList();
        ResultSet rs; 
        String req = "SELECT * FROM categorie_service";
      
            ps = connection.prepareStatement(req);
            rs = ps.executeQuery(req);
            
            while (rs.next()) {           
                                       
                list.add(rs.getString("nom"));
            }
            return list;
     }
     
        } 
    
    

