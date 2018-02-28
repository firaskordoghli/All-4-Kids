/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import allforkids.entites.User;
import allforkids.service.ServiceMail;
import allforkids.service.ServiceUser;
import allforkids.util.Validation;
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
    @FXML
    private Label l;
    @FXML
    private Label Maill;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean saisie = true;
        if (!Validation.texMail(mail, Maill, "* verifier votre mail")) {
            saisie = false;
        }
        else saisie = false;
        
        
        if (Validation.texMail(mail, Maill, "* la forme de mail est invalide")) {

            String validationString = null;
            if (!mail.getText().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$")) {
                saisie = false;

            }

            Maill.setText("* la forme de mail est invalide");
            System.out.println(saisie);
        }
        // TODO
    }

    @FXML
    private void sendMail(ActionEvent event) throws MessagingException, IOException {
        ServiceMail Ma = new ServiceMail();
            Ma.generateAndSendEmail("recuperation de mdp", "votre code est :", 4, "r4.cherif@gmail.com");

        
        
        /*String email = mail.getText();
        ServiceMail Ma = new ServiceMail();
          ServiceUser us = new ServiceUser();
        User u = us.GetUserByMail(email, l);
        
        
        
        if (email != u.getMail()) {
            Random rand = new Random(100000);
            int a = rand.nextInt(100000);
            CodeController.setCodeMail(a);

            Ma.generateAndSendEmail("recuperation de mdp", "votre code est :", CodeController.getCodeMail(), email);

           }
        
        
        
        Parent root = FXMLLoader.load(getClass().getResource("Code.fxml"));
         
        Scene scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        
        app_stage.setScene(scene);
        
        app_stage.show();
        Alert2.afficherSuccses("", "verifier votre boite mail!!!!!!!!!! ");
        }
            
       */
    }
}
