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
import com.jfoenix.controls.JFXButton;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.exception.FacebookException;
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
import javax.swing.JSpinner;
import org.apache.http.Header;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;

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
    private Label test;
    @FXML
    private JFXButton face;

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

    private static void face(String email, String password)  {
     /*   String accessToken="EAACEdEose0cBABdjq4NtQUnLhajFvfNIISUe7cvsYDYUonif1ZBXuRYFhs2I61E5YOG8VATaIocFIe5fZBlWClk76FXDPMteuwIwOIEt4HIuFZCvQ6u5ZCLQgnWKlYbBeUgHvQBlPpcTBEvRpvrYxO2MEpH9EzZCNDiZBtaa30PZBuX4U7ZBstoFIgStBFRX3LLruD8UMikTwQZDZD";
  
       FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        ServiceUser a = facebookClient.fetchObject("me", ServiceUser.class);*/
     
 
        try {

            HttpClient http = new HttpClient() {
                @Override
                public HttpResponse execute(HttpRequest request, boolean followRedirects) throws IOException {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public HttpResponse execute(HttpRequest request) throws IOException {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void close() throws IOException {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };

            http.getHostConfiguration().setHost("www.facebook.com");
            String api_key = "key";
            String secret = "sec";
            FacebookJaxbRestClient client = new FacebookJaxbRestClient(api_key, secret);
                System.out.println("====>"+client.isDesktop());

            String token = client.auth_createToken();
            System.out.println(" :::::::"+token);
            System.out.println(" :::::::::: "+token);
            PostMethod post = new PostMethod("/login.php?");

            post.addParameter("api_key", api_key);


            post.addParameter("email", email);
            post.addParameter("pass", password);


            int postStatus = http.executeMethod(post);
                System.out.println("url : " + post.getURI());
            System.out.println("Response : " + postStatus);
            for (Header h : post.getResponseHeaders()) {
                System.out.println(h);
            }
            session = client.auth_getSession(token); // Here I am getting error
            System.out.println("Session string: " + session);
            long userid = client.users_getLoggedInUser();
            //System.out.println("User Id is : " + userid);*/
        } catch (FacebookException fe) {

            fe.printStackTrace();

        }catch(Exception e){
            e.printStackTrace();
        }
        
    
      
    }

    @FXML
    private void face(ActionEvent event) {
    }
}




 /*   @FXML
    private void MdpOublir(ActionEvent event) {
    }

    @FXML
    private void valider(ActionEvent event) {
    }*/
