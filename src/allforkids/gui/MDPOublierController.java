/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import allforkids.entites.User;
import allforkids.service.ServiceMail;
import allforkids.service.ServiceUser;
import com.google.code.facebookapi.schema.NotificationData.Notifications;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
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
    private JFXTextField mail;
    @FXML
    private JFXButton sendMail;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendMail(ActionEvent event) throws MessagingException, IOException {
     /*  ServiceMail Ma = new ServiceMail();
        Ma.generateAndSendEmail("recuperation de mdp", "", "3","r4.cherif@gmail.com");*/
       
       
       ServiceMail Ma = new ServiceMail();
        ServiceUser us = new ServiceUser();
        
        User u=us.recevoirUser(mail.getText());
        
            //generer mdp automatiquement
            
            
            
           Random rand = new Random(10000);
           int a=rand.nextInt(10000);
          //int a=h+1000;
           
          
            
            
           // System.out.println(s);
           Ma.generateAndSendEmail("recuperation de mdp", "test", a,"r4.cherif@gmail.com");
            }
            /*us.changerMDP(s,u.getId());
            
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
         
        Scene scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        
        app_stage.setScene(scene);
        
        app_stage.show();*/
        
        
                
        }
        
        
    
        
        
    


    
  
