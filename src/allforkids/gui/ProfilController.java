/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Session;
import allforkids.entites.User;
import allforkids.service.ServiceUser;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class ProfilController implements Initializable {

    @FXML
    private ImageView photo;
   // private Label NomPrenom;
    @FXML
    private JFXButton edit;
    @FXML
    private Label prenom;
   // private Label test;
    @FXML
    private JFXButton out;
    @FXML
    private Label nom;
    @FXML
    private Label CIN;
    @FXML
    private Label mail;
    @FXML
    private Label tel;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb ) {
        // 
        int id;
        id=Session.getIdThisUser();
         ServiceUser s = new ServiceUser();
         User u = s.GetUserById(id);
       nom.setText(u.getNom());
       prenom.setText(u.getMail());
       mail.setText(u.getMail());
       CIN.setText(u.getCin());
       date.setText(String.valueOf(u.getDate()));
       //test.setText(u.getPicture());
       Image img;
        try {
            img = new Image(new FileInputStream(u.getPicture()));
            
        photo.setImage(img);
        } catch (FileNotFoundException ex) {
            System.out.println(ex); // Logger.getLogger(DétailEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        ServiceUser su = new ServiceUser();
        User u = new User();
        Parent root =FXMLLoader.load(getClass().getResource("CreationCompte.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                        
                su.updateUser(u, Session.getIdThisUser());
                
                       
        
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException {
          Parent root =FXMLLoader.load(getClass().getResource("Login.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
}
}