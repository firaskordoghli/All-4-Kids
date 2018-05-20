/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Covoiturage;
import allforkids.entites.Session;
import allforkids.entites.Transport;
import allforkids.service.ServiceCovoiturage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CovFormulaireController implements Initializable {

    ObservableList<String> typeList = FXCollections.observableArrayList("auccasionellement", "regulierement");
    @FXML
    private JFXTextField Region;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXTextField placeDispo;
    @FXML
    private JFXTextField fraix;
    @FXML
    private Button addButton;
    @FXML
    private Button delButton;
    private ComboBox type;
    @FXML
    private WebView addwebview;
    @FXML
    private JFXDatePicker date;

    private WebEngine webEngine;
    @FXML
    private JFXComboBox<String> typeCov;
    
  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeCov.setValue("");
        typeCov.setItems(typeList);
        
        webEngine = addwebview.getEngine();
        webEngine.load(getClass().getResource("addlocation.html").toString());
        // webEngine.load("https://www.google.com");
        
    }

    @FXML
    public void addCov(ActionEvent event) throws IOException {
        
        int id_user = Session.getIdThisUser();
        
        System.out.println(" arrive name : " + webEngine.executeScript("getArriveName();"));
        System.out.println("depart name : " + webEngine.executeScript("getDepartName();"));
        
        String arriveName = String.valueOf(webEngine.executeScript("getArriveName();"));
        String departName = String.valueOf(webEngine.executeScript("getDepartName();"));
        
        System.out.println(" arrive : " + webEngine.executeScript("getArrive();"));
        System.out.println("depart : " + webEngine.executeScript("getDepart();"));
        
        String dep = String.valueOf(webEngine.executeScript("getDepart();"));
        String arr = String.valueOf(webEngine.executeScript("getArrive();"));
        
        LocalDate d = date.getValue();
         if (date.getValue() != null) {
           
            Date date0 = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date date2 = new Date();
            if (date0.before(date2)) {
               Alert2.afficherWARNING("Erreur", "la date sasier invalid ");
                
            } 
         }
        Date date1 = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        
        ServiceCovoiturage cService = new ServiceCovoiturage();
        Transport t = new Transport( dep, arr,
                description.getText(), telephone.getText(), placeDispo.getText(), fraix.getText(), typeCov.getValue(),date1,arriveName,departName,id_user);
        cService.insrerCov(t);
        Alert2.afficherSuccses("Enregistre", "Covaturage ajouter avec succée");
     
    }

        

    



}
