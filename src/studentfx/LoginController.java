/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentfx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LoginController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXButton cx;
    @FXML
    private ImageView imgProgress;
    @FXML
    private Hyperlink MdpOublir;
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
    private void login(ActionEvent event) {
    }

    @FXML
    private void MdpOublir(ActionEvent event) {
    }

    @FXML
    private void valider(ActionEvent event) {
    }

    @FXML
    private void face(ActionEvent event) {
    }
    
}
