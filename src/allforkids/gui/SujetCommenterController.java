/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Commentaire;
import allforkids.entites.Liverlike;
import allforkids.entites.Session;
import allforkids.entites.Sujet;
import allforkids.entites.User;
import allforkids.service.ServiceCommentaire;
import allforkids.service.ServiceLiverlike;
import allforkids.service.ServiceSujet;
import allforkids.service.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class SujetCommenterController implements Initializable {

    @FXML
    private JFXListView<Label> list;
    @FXML
    private JFXTextArea commenter;
    @FXML
    private JFXButton valide;
    @FXML
    private Label Nom;
    @FXML
    private ImageView img;
    private ServiceCommentaire sc = new ServiceCommentaire();
    @FXML
    private JFXButton good;
    @FXML
    private JFXButton bad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Commentaire> c = new ArrayList<>();
        c.addAll(sc.GetSujetById(ListeSujetController.id_sujet));
        ServiceUser su = new ServiceUser();
        ServiceCommentaire c1 = new ServiceCommentaire();
        if (c.size() == 0) {
            Session.getIdThisUser();
            User u = su.GetUserById(32);
            Nom.setText(u.getNom());
            Image image;
            try {
                image = new Image(new FileInputStream(u.getPicture()));
                
                img.setImage(image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SujetCommenterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            for (Commentaire commentaire : c) {
               try {
                    Label l = new Label(commentaire.getDescription());
                    ImageView i;

                    i = new ImageView(new Image(new FileInputStream(su.GetUserById(32).getPicture())));

                    i.setFitHeight(150);
                    i.setFitWidth(250);
                    l.setGraphic(i);
                    list.getItems().add(l);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SujetCommenterController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            Session.getIdThisUser();
            User u = su.GetUserById(32);
            Nom.setText(u.getNom());
            Image image;
            try {
                image = new Image(new FileInputStream(u.getPicture()));
                img.setImage(image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SujetCommenterController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void Enregistre(ActionEvent event) throws IOException {
        Commentaire c = new Commentaire(commenter.getText(), 0, 0, 32, 1);
        sc.insrerCommentaire(c);

        Parent root = FXMLLoader.load(getClass().getResource("SujetCommenter.fxml"));
        Scene scene = new Scene(root);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);

        app_stage.show();
    }

    @FXML
    private void good(ActionEvent event) {
        ServiceSujet s = new ServiceSujet();
        s.GetSujetByIdd(0);
        
        
       bad.setDisable(true);
       good.setDisable(true);
       
       
       
       
       
       
        

    }

    @FXML
    private void bad(ActionEvent event) {
        
    }

}
