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
import com.google.code.facebookapi.FacebookJaxbRestClient;
//import com.google.code.facebookapi.FacebookJaxbRestClient;
import com.jfoenix.controls.JFXButton;
 
import com.jfoenix.controls.JFXTimePicker;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;

//import com.restfb.DefaultFacebookClient;
//import com.restfb.FacebookClient;
//import com.restfb.exception.FacebookException;

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
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.PostMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.apache.commons.httpclient.URIException;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.http.Header;
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
    public static String maill ;
    public static String passl ;
    @FXML
    private Hyperlink facebook;

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
                 Parent root =FXMLLoader.load(getClass().getResource("Home.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                } else if (u.getRole() == 2) {
                       // envoi vers page admin 
                 Parent root =FXMLLoader.load(getClass().getResource("Home.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                    //envoi vers page parents
                }
                else if (u.getRole() == 3) {
                       // envoi vers page admin 
                 Parent root =FXMLLoader.load(getClass().getResource("Home.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                    //envoi vers page parents
                }
                else if (u.getRole() == 0) {
                       // envoi vers page admin 
                 Parent root =FXMLLoader.load(getClass().getResource("Home.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                    //envoi vers page parents
                }
             
            }else {Alert2.afficherSuccses("Erreur login", "mot de passe  ou mail incorrect");}

        }else{//ErrMail.setText("verrifier votre mail");}
   // ErrMail.setText(u.toString());
            Alert2.afficherSuccses("Erreur login", "les champs sont vide!!");
    }
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

    @FXML
    private void facebook(ActionEvent event) {
        
        String domain = "http://google.com";
        String appId = "1957709750923073";
       
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";
       
        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");
       
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        while(true){
       
            if(!driver.getCurrentUrl().contains("facebook.com")){
            String url = driver.getCurrentUrl();
            accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
           
            driver.quit();
           
                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                User user = fbClient.fetchObject("me",User.class);
               
                lb.setText(user.getMail());
           
            }
       
        }
       
    }

  
}


    
    
    
    

    
    
    
        
    

   
    




/*
   @FXML
    private void MdpOublir(ActionEvent event) {
    }

    @FXML
    private void valider(ActionEvent event) {
    }
*/