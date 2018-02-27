/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.all4kids.controllers;

import static edu.esprit.all4kids.controllers.ServiceAdminController.selectedService;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ourabi
 */
public class ConsulterServiceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView<Service> tvService;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrix;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colCategorie;

    @FXML
    private ImageView imvService;

    @FXML
    private Button btAjouterService;

    @FXML
    private Button btReclamerService;

    @FXML
    private Button btConfirmerReclamation;

    @FXML
    private TextField txReclamation;

    @FXML
    void ajouterService(ActionEvent event) throws IOException {
        
         Parent p1 = FXMLLoader.load(getClass().getResource("/allforkids/gui/AjouterService.fxml"));
              //   FXMLLoader.load(getClass().getClassLoader().getResource("edu/esprit/all4kids/gui/AjouterService.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();
               

    }

    @FXML
    void confirmer(ActionEvent event) {
        
        ReclamationService rs = new ReclamationService();
        Reclamation r = new Reclamation(0, txReclamation.getText(), Session.getIdThisUser(), selectedService.getId(), 0,selectedService.getNom());
        rs.addReclamation(r);
        txReclamation.setVisible(false);
             btConfirmerReclamation.setVisible(false);
             txReclamation.clear();

    }

    @FXML
    void reclamer(ActionEvent event) {
        txReclamation.setVisible(true);
             btConfirmerReclamation.setVisible(true);

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
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             // TODO

             txReclamation.setVisible(false);
             btConfirmerReclamation.setVisible(false);
             buildTableviewService();
             EventGetService();
         } catch (ParseException ex) {
             Logger.getLogger(ConsulterServiceController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ConsulterServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
    
}
