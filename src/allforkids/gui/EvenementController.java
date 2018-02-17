/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Participevenement;
import allforkids.service.ServiceEvenement;
import allforkids.service.ServiceParticipevenement;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class EvenementController implements Initializable {

    @FXML
    private Tab consultt;
    @FXML
    private ListView<Label> listevent;
    @FXML
    private AnchorPane detail;
    @FXML
    private Label titre;
    @FXML
    private ImageView img;
    @FXML
    private Label description;
    @FXML
    private JFXButton inscript;
    @FXML
    private Tab mesconsult;
    @FXML
    private Tab inscrit;
    private Evenement event;
    private ServiceEvenement s = new ServiceEvenement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Label> a = FXCollections.observableArrayList() ;
          List<Evenement> e = new ArrayList<>() ;
         
          e= s.selectEvenement();
          
          
          for (Evenement ev : e) {
                  try {
                      
             Label l = new Label( ev.getNom());
             l.setFont(Font.font("DK Cool Crayon", 30));
           ImageView i = new ImageView( new Image( new FileInputStream(ev.getPhoto())));
           i.setFitHeight(150);
           i.setFitWidth(250);
           
                 l.setGraphic(i);
                 listevent.getItems().add(l);
                 listevent.setStyle("-fx-background-color: #B7D7F2");
             }catch (FileNotFoundException ex) {
                 Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex); 
             }
         
        }
         detail.setVisible(false);
    }    

    @FXML
    private void afficherImage(MouseEvent me) throws FileNotFoundException {
           detail.setVisible(true);
            String l = listevent.getSelectionModel().getSelectedItem().getText();
            event = s.getIdByName(l);
            titre.setText(l);
            description.setText("Lieu: "+event.getLieu()+"\n"+"Date: "+event.getDate().toString()+"\n"+"Type: "+event.getType());
            Image image =new Image(new FileInputStream(event.getPhoto()));
            img.setImage(image);
            
    }

    @FXML
    private void inscription(ActionEvent ev) {
         String l = listevent.getSelectionModel().getSelectedItem().getText();
            event = s.getIdByName(l);
          Participevenement p = new Participevenement(event.getId_evenement(),6,6);
          ServiceParticipevenement sp = new ServiceParticipevenement();
          sp.insrerParticipevenement(p);
         
    }
    
}
