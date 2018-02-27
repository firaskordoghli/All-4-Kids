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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ourabi
 */
public class ModifierServiceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private TextField txNom;

    @FXML
    private TextField txPrix;

    @FXML
    private TextField txDate;

    @FXML
    private TextArea txDescription;

    @FXML
    private ComboBox<String> cbCategorie;

    @FXML
    private Button btImage;

    @FXML
    private ImageView imv;

    @FXML
    private Button btRetour;

    @FXML
    private Button btModifier;
    private String fileName;

    @FXML
    void image(ActionEvent event) {

          FileChooser fileChooser = new FileChooser();
                     FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                     FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
                     FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                     fileChooser.getExtensionFilters().addAll(exjpg,exjpg2, expng);
                     fileChooser.setTitle("Choose an image File");

                     File file = fileChooser.showOpenDialog(null);
                     
                             if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                
                                
                               fileName= file.getName();
                                Image img = new Image(Session.addImage+fileName) ;
            imv.setImage(img); 
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
            }


            }
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {
        
        ServiceService ss = new ServiceService();
        Service service = new Service(selectedService.getId(),txNom.getText(), txDescription.getText(), Double.parseDouble(txPrix.getText()), cbCategorie.getValue() , fileName);
        
        ss.edit(service);
        
          Parent p1 = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/gui/Home.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();

    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        
        Parent p1 = FXMLLoader.load(getClass().getClassLoader().getResource("edu/esprit/all4kids/gui/ServiceAdmin.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              // TODO
              
              txDescription.setText(selectedService.getDescription());
              txDate.setText(String.valueOf(selectedService.getDate()));
              txNom.setText(selectedService.getNom());
              txPrix.setText(String.valueOf(selectedService.getPrix()));
              
              fileName = selectedService.getImage();
              Image img = new Image(Session.addImage+fileName) ;
              imv.setImage(img);
              
              txDate.setDisable(true);
              txDate.setEditable(true);
              CategorieService cs = new CategorieService();
              ObservableList<String> options = cs.listerCombo() ;
              cbCategorie.setItems(options);
              cbCategorie.setValue(selectedService.getCategorie());
          } catch (ParseException ex) {
              Logger.getLogger(ModifierServiceController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(ModifierServiceController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }    
    
}
