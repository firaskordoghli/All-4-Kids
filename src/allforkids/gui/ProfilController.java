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
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class ProfilController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Label NomPrenom;
    @FXML
    private JFXButton edit;
    @FXML
    private Label prenom;
    @FXML
    private Label test;

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
       NomPrenom.setText(u.getNom());
       prenom.setText(u.getMail());
        
    }    

    @FXML
    private void Modifier(ActionEvent event) {
    }
}