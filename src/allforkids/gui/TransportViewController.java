/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TransportViewController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private HBox boxMenus;
    @FXML
    private AnchorPane paneBus;
    @FXML
    private AnchorPane paneCovoiturage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void switchToBus(MouseEvent event) {
            try {
                paneBus.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("BusView.fxml"));
                Scene scene = new Scene(root);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
            } catch (IOException ex) {
                Logger.getLogger(TransportViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    
    public void switchToCov(MouseEvent event) {
            try {
                paneCovoiturage.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("CovoiturageView.fxml"));
                Scene scene = new Scene(root);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
            } catch (IOException ex) {
                Logger.getLogger(TransportViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    
}
