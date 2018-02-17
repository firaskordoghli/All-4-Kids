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
public class AcceuilController implements Initializable {

    @FXML
    private HBox boxMenusUp;
    @FXML
    private AnchorPane paneDivertissement;
    @FXML
    private HBox boxMenusDown;
    @FXML
    private AnchorPane paneTransport;
    @FXML
    private AnchorPane paneForum;
    @FXML
    private AnchorPane paneEtablissement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void switchToDivertissement(MouseEvent event) {
                try {
                    paneDivertissement.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("Evenement.fxml"));
                    Scene scene = new Scene(root);
                    Stage driverStage = new Stage();
                    driverStage.setScene(scene);
                    driverStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    public void switchToEtablissement(MouseEvent event) {
                try {
                    paneEtablissement.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("Etablissemnt.fxml"));
                    Scene scene = new Scene(root);
                    Stage driverStage = new Stage();
                    driverStage.setScene(scene);
                    driverStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    public void switchToForum(MouseEvent event) {
                try {
                    paneForum.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("Etablissemnt.fxml"));
                    Scene scene = new Scene(root);
                    Stage driverStage = new Stage();
                    driverStage.setScene(scene);
                    driverStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    public void switchToTransport(MouseEvent event) {
                try {
                    paneForum.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("TransportView.fxml"));
                    Scene scene = new Scene(root);
                    Stage driverStage = new Stage();
                    driverStage.setScene(scene);
                    driverStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
}
