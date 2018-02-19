/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.User;
import allforkids.service.ServiceUser;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class MDPOublierController implements Initializable {

    @FXML
    private JFXTextField MailC;
    @FXML
    private Label Mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Changer(ActionEvent event) throws IOException {
           ServiceUser us = new ServiceUser();
        
       /* 
        User u=us.recevoirUser(MailC.getText());
        if (MailC.getText().equals(u.getEmail()))
        {
            //generer mdp automatiquement
            
            String s=""+u.getId();
            System.out.println(s);
            for (int i=0;i<20;i++)
            {
           Random rand = new Random();
           int a=rand.nextInt(10);
           s=s+a;
            }
            
            System.out.println(s);
            
            us.changerMDP(s,u.getId());
            
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
         
        Scene scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        
        app_stage.setScene(scene);
        
        app_stage.show();
        
        
        }
        else
            loginL.setText("* verifier vos informations !");*/
        
                
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
         
        Scene scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        
        app_stage.setScene(scene);
        
        app_stage.show();
    }
    
}
