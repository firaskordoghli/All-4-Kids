/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
        Platform.exit();
    }
    
}
