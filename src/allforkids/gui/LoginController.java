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
import allforkids.util.Validation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class LoginController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private Button cx;
    @FXML
    private PasswordField pass;
    @FXML
    private Label ErrMail;
    @FXML
    private Label ErrPass;
    @FXML
    private Label lb;
    @FXML
    private Hyperlink MdpOublir;
    @FXML
    private Label MailC;
    @FXML
    private Label PassC;
    @FXML
    private Label test;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void cnx(ActionEvent event) throws SQLException, IOException {
        String email = mail.getText();
        String mdp = pass.getText();
 
        ServiceUser su = new ServiceUser();
        User u = su.GetUserByMail(email,lb);
       // test.setText(u.getMail());
        test.setText(u.getPass());
        if (su.GetUserByMail(email,lb) != null) {
           if (BCrypt.checkpw(pass.getText(),u.getPass())) {
                
                //ajouter une variable ou id 
                Session.setIdThisUser(u.getId());
                if (u.getRole() == 1) {
                    // envoi vers page admin 
                 Parent root =FXMLLoader.load(getClass().getResource("Profil.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                } else if (u.getRole() == 2) {
                       // envoi vers page admin 
                 Parent root =FXMLLoader.load(getClass().getResource("Profil.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                    //envoi vers page parents
                }
             
            }else {ErrPass.setText("verrifer votre passe");}

        }else{ErrMail.setText("verrifier votre mail");}
    ErrMail.setText(u.toString());
    }
        
    

    @FXML
    private void valider(ActionEvent event) throws IOException {
         Parent root =FXMLLoader.load(getClass().getResource("CreationCompte.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
    }

    @FXML
    private void MdpOublir(ActionEvent event) throws IOException {
         Parent root =FXMLLoader.load(getClass().getResource("MDPOublier.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
    }

}

 /*   @FXML
    private void MdpOublir(ActionEvent event) {
    }

    @FXML
    private void valider(ActionEvent event) {
    }*/
