/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.all4kids.controllers;

import allforkids.entites.Categorie;
import allforkids.entites.Reclamation;
import allforkids.entites.Service;
import allforkids.entites.Session;
import allforkids.service.ReclamationService;
import allforkids.service.ServiceService;
import allforkids.service.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ourabi
 */
public class ServiceAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane holderPane;
    
    private Categorie selectedCategorie = null;
    
      @FXML
    private TableView<Service> tvService;

    @FXML
    private TableColumn<Service, String> colNom;

    @FXML
    private TableColumn<Service, String> colDescription;

    @FXML
    private TableColumn<Service, Double> colPrix;

    @FXML
    private TableColumn<Service, java.sql.Date> colDate;

    @FXML
    private TableColumn<Service, String> colCategorie;

    @FXML
    private TableView<Categorie> tvCategorie;

    @FXML
    private TableColumn<Categorie, String> colCategories;

    
     @FXML
    private TableView<Service> tvService1;

    @FXML
    private TableColumn<Service, String> colNom1;

    @FXML
    private TableColumn<Service, String> colDescription1;

    @FXML
    private TableColumn<Service, Double> colPrix1;

    @FXML
    private TableColumn<Service, java.sql.Date> colDate1;

    @FXML
    private TableColumn<Service, String> colCategorie1;
    
    @FXML
    private TextField txCategorie;

    @FXML
    private Button btAjouCat;

    @FXML
    private Button btModifierCategorie;

    @FXML
    private Button btSupprimerCategorie;

    @FXML
    private ImageView imvService;

    @FXML
    private Button btAjouterService;

    @FXML
    private Button btModifierService;

    @FXML
    private Button supprimerService;
    static Service selectedService;

    
    @FXML
    private Button btAccepterService;

    @FXML
    void accepterService(ActionEvent event) throws ParseException, SQLException {
        
        ServiceService ss = new ServiceService();
        ss.acceptService(selectedService.getId());
         buildTableviewService();
         buildTableviewServiceAttente();

    }
    
    @FXML
    void ajouterCategorie(ActionEvent event) throws ParseException, SQLException {
        
        CategorieService cs = new CategorieService();
        Categorie cat = new Categorie(txCategorie.getText());
        cs.addCategorie(cat);
         buildTableviewCategorie();
         txCategorie.clear();

    }

    @FXML
    void ajouterService(ActionEvent event) throws IOException {
        
         AnchorPane ajouter = FXMLLoader.load(getClass().getResource("/allforkids/gui/AjouterService.fxml"));
            setNode(ajouter);
    }

    @FXML
    void modifierCategorie(ActionEvent event) throws ParseException, SQLException, IOException {

         CategorieService cs = new CategorieService(); 
         selectedCategorie.setNom(txCategorie.getText());
        cs.editCategorie(selectedCategorie);
        System.out.println(selectedCategorie.getId());
         buildTableviewCategorie();
         txCategorie.clear();
         
         
    }

    @FXML
    void modifierService(ActionEvent event) throws IOException {
        
       AnchorPane ajouter = FXMLLoader.load(getClass().getResource("/allforkids/gui/ModifierService.fxml"));
            setNode(ajouter);
    }

    @FXML
    void supprimerCategorie(ActionEvent event) throws ParseException, SQLException {

        CategorieService cs = new CategorieService();          
        cs.deleteCategorie(selectedCategorie.getId());
       
         buildTableviewCategorie();
         txCategorie.clear();
         
    }

    @FXML
    void supprimerService(ActionEvent event) throws ParseException, SQLException {
        
        ServiceService ss = new ServiceService();
        ss.deleteService(selectedService.getId());
        buildTableviewService();

    }
    
     private void buildTableviewCategorie() throws ParseException, SQLException {
       
    
        colCategories.setCellValueFactory(new PropertyValueFactory<>("nom"));
            
    CategorieService cs = new CategorieService();   
    
        ObservableList<Categorie> data = cs.lister();
        
        tvCategorie.setItems(data);
      
        
      }
     
      private void EventGetCategorie() {
        tvCategorie.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Categorie> observable,
                Categorie oldValue,
                Categorie newValue) -> {
            if (newValue == null) {
                return;
            }
            
            selectedCategorie = newValue;
            txCategorie.setText(newValue.getNom());
            
            
        });
    }
      
      private void EventGetService() {
        tvService.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Service> observable,
                Service oldValue,
                Service newValue) -> {
            if (newValue == null) {
                return;
            }
            
            selectedService = newValue;
            Image img = new Image(Session.addImage+newValue.getImage()) ;
            imvService.setImage(img); 
            
            
        });
    }
      
      private void EventGetServiceAttente() {
        tvService1.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Service> observable,
                Service oldValue,
                Service newValue) -> {
            if (newValue == null) {
                return;
            }
            
            selectedService = newValue;
            Image img = new Image(Session.addImage+newValue.getImage()) ;
            imvService.setImage(img); 
            
            
        });
    }
      
      private void buildTableviewService() throws ParseException, SQLException {
       
    
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
               

    
        
    
       ServiceService ss = new ServiceService();
        
        ObservableList<Service> data = ss.lister();
        
        tvService.setItems(data);
      
        
      }
      
       private void buildTableviewServiceAttente() throws ParseException, SQLException {
       
    
        colCategorie1.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        colDate1.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDescription1.setCellValueFactory(new PropertyValueFactory<>("description"));
        colNom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
               

    
        
    
       ServiceService ss = new ServiceService();
        
        ObservableList<Service> data = ss.listerAttente();
        
        tvService1.setItems(data);
      
        
      }
     
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              // TODO
              buildTableviewCategorie();
              EventGetCategorie();
              
              buildTableviewService();
              EventGetService();
             
              buildTableviewServiceAttente();
              EventGetServiceAttente();
          } catch (ParseException ex) {
              Logger.getLogger(ServiceAdminController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(ServiceAdminController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
//    
      private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }
}
