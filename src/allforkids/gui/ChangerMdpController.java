/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Session;
import allforkids.entites.User;
import allforkids.service.ServiceUser;
import allforkids.util.BCrypt;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class ChangerMdpController implements Initializable {

    @FXML
    private JFXPasswordField newPassword;
    @FXML
    private JFXPasswordField confirmNewPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changeMdp(ActionEvent event)throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        
         ServiceUser u=new ServiceUser();
     // User user=new User();
      
     
          if (newPassword.getText().equals(confirmNewPassword.getText()))
          {
              u.changerMDP(newPassword.getText(),Session.getIdThisUser());
              Alert2.afficherSuccses("", "votre mot de passe a été changée avec success");
          } else {Alert2.afficherSuccses("", "verifier vos champs ");}
      }
     
    }
    

