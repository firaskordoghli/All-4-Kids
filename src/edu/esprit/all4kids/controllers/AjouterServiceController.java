/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.all4kids.controllers;

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
import java.util.Calendar;
import java.util.Properties;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.MessagingException;


/**
 * FXML Controller class
 *
 * @author Ourabi
 */
public class AjouterServiceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private AnchorPane holderPane;
    
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
    private Button btAjouter;
    
    private String fileName;
    
    java.sql.Date date ;

    @FXML
    void ajouter(ActionEvent event) throws IOException, MessagingException {
        
        ServiceService ss = new ServiceService();
        Service service = new Service(txNom.getText(), txDescription.getText(), Double.parseDouble(txPrix.getText()), date, cbCategorie.getValue(),Session.getIdThisUser() , fileName, 0);
        ss.addService(service);
        
       ReclamationService rs = new ReclamationService();
       rs.sendMail("ali.nemri@esprit.tn","0054005456023378","Nouveau Service","Service: "+txNom.getText()+", "+txDescription.getText() );

            
            Parent p1 = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/gui/Home.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();
    }
    
    

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
    
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }

    @FXML
    void retour(ActionEvent event) {

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
          // TODO
          date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
          txDate.setText(String.valueOf(date));
          txDate.setDisable(true);
          txDate.setEditable(true);
          CategorieService cs = new CategorieService();
          ObservableList<String> options = cs.listerCombo() ;
          cbCategorie.setItems(options);
      } catch (ParseException ex) {
          Logger.getLogger(AjouterServiceController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(AjouterServiceController.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        
    }    
    
}
