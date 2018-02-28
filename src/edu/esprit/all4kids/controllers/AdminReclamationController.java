/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.all4kids.controllers;

import static edu.esprit.all4kids.controllers.ServiceAdminController.selectedService;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import allforkids.entites.Reclamation;
import allforkids.entites.Service;
import allforkids.service.ReclamationService;
import allforkids.service.ServiceService;

/**
 * FXML Controller class
 *
 * @author Ourabi
 */
public class AdminReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TableView<Reclamation> tvReclamation;

    @FXML
    private TableColumn<Reclamation, String> colService;

    @FXML
    private TableColumn<Reclamation, Integer> colUser;

    @FXML
    private TableColumn<Reclamation, String> colDescription;

    @FXML
    private TableView<Reclamation> tvReclamation1;

    @FXML
    private TableColumn<Reclamation, String> colService1;

    @FXML
    private TableColumn<Reclamation, Integer> colUser1;

    @FXML
    private TableColumn<Reclamation, String> colDescription1;

    @FXML
    private Button btAccepter;

    @FXML
    private Button btRefuser;
    private Reclamation selectedReclamation;

    @FXML
    void accepter(ActionEvent event) throws ParseException, SQLException {
        
        ReclamationService sr = new ReclamationService();
        sr.acceptService(selectedReclamation.getId());
        buildTableviewReclamation();
        buildTableviewReclamation1();

    }

    @FXML
    void refuser(ActionEvent event) throws ParseException, SQLException {
        
        ReclamationService sr = new ReclamationService();
        sr.deleteReclamation(selectedReclamation.getId());
        buildTableviewReclamation();
        buildTableviewReclamation1();

    }
    
     private void EventGetReclamation() {
        tvReclamation.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Reclamation> observable,
                Reclamation oldValue,
                Reclamation newValue) -> {
            if (newValue == null) {
                return;
            }
            
            selectedReclamation = newValue;
           
            
            
        });
    }
    
    private void buildTableviewReclamation() throws ParseException, SQLException {
       
    
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colService.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("user_id"));
       
               

    
        
    
       ReclamationService ss = new ReclamationService();
        
        ObservableList<Reclamation> data = ss.lister();
        
        tvReclamation.setItems(data);
      
        
      }
    
    private void EventGetReclamation1() {
        tvReclamation1.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Reclamation> observable,
                Reclamation oldValue,
                Reclamation newValue) -> {
            if (newValue == null) {
                return;
            }
            
            selectedReclamation = newValue;
           
            
            
        });
    }
    
    private void buildTableviewReclamation1() throws ParseException, SQLException {
       
    
        colDescription1.setCellValueFactory(new PropertyValueFactory<>("description"));
        colService1.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        colUser1.setCellValueFactory(new PropertyValueFactory<>("user_id"));
       
               

    
        
    
       ReclamationService ss = new ReclamationService();
        
        ObservableList<Reclamation> data = ss.lister2();
        
        tvReclamation1.setItems(data);
      
        
      }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              // TODO

              buildTableviewReclamation();
              EventGetReclamation();
              
              buildTableviewReclamation1();
              EventGetReclamation1();
          } catch (ParseException ex) {
              Logger.getLogger(AdminReclamationController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(AdminReclamationController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
    
}
