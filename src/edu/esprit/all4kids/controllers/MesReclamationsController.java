/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.all4kids.controllers;

import allforkids.entites.Reclamation;
import allforkids.entites.Session;
import allforkids.service.ReclamationService;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ourabi
 */
public class MesReclamationsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private TableView<Reclamation> tvReclamation;

    @FXML
    private TableColumn<Reclamation, String> colService;

    @FXML
    private TableColumn<Reclamation, String> colDescription;

    @FXML
    private TableColumn<Reclamation, String> colEtat;
    
     @FXML
    private Button reload;

    @FXML
    void Reload(ActionEvent event) throws ParseException, SQLException {
            buildTableviewReclamation();
    }
    
    
    private void buildTableviewReclamation() throws ParseException, SQLException {
       
    
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colService.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("traite"));
       
               

    
        
    
       ReclamationService ss = new ReclamationService();
        
        ObservableList<Reclamation> data = ss.myReclamation(Session.getIdThisUser());
        
        tvReclamation.setItems(data);
      
        
      }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              // TODO

              buildTableviewReclamation();
          } catch (ParseException ex) {
              Logger.getLogger(MesReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(MesReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
    
}
