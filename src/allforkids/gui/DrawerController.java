/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DrawerController implements Initializable {

    @FXML
    private JFXButton acceuilBtn;
    @FXML
    private JFXButton transportBtn;
    @FXML
    private JFXButton etablissementBtn;
    @FXML
    private JFXButton divertissementBtn;
    @FXML
    private JFXButton reclamationBtn;
    @FXML
    private JFXButton deconnectéBtn;
    @FXML
    private JFXButton quitéBtn;
    @FXML
    private JFXButton profilBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void exit(ActionEvent event) {
        //Platform.exit();
    }
    
    @FXML
    private void Logout(ActionEvent event) throws IOException {
          Parent root =FXMLLoader.load(getClass().getResource("Login.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
}
    
}
