/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Sujet;
import allforkids.entites.User;
import allforkids.service.ServiceSujet;
import allforkids.service.ServiceUser;
import allforkids.util.BCrypt;
import allforkids.util.Validation;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class ForumSujetCreationController implements Initializable {

    @FXML
    private JFXTextArea Description;
    @FXML
    private JFXTextField Titre;
    
    
    @FXML
    private ChoiceBox<String> tag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> list = new ArrayList();
        list.add("Enfant");
        list.add("Bébé");
        list.add("Lycée");
        list.add("Cours");
        list.add("Cours particulier");
        list.add("Autres");

        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        tag.setItems(ob);
    }    

    @FXML
    private void validerSujet(ActionEvent event) throws IOException {
     

        ServiceSujet a = new ServiceSujet();
      
        
        
        Sujet s = new Sujet(Titre.getText(),Description.getText(),tag.getValue(),0,0,true);
         a.insrerSujet(s);
            

            /*   Notifications notificiationBuilder = Notifications.create()
                        .title("créer")
                        .text("votre compte a été créer !")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.BOTTOM_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Missing!");
                            }
                        });
                notificiationBuilder.darkStyle();
                notificiationBuilder.showConfirm();
               //Login(event);*/
         
            Parent root = FXMLLoader.load(getClass().getResource("ListeSujet.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();

        }

    }

    

