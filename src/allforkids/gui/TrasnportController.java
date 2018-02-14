/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TrasnportController implements Initializable {

    @FXML
    private HBox boxMenus;
    @FXML
    private AnchorPane paneUsers;
    @FXML
    private AnchorPane paneTickets;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToUsers(MouseEvent event) {
    }

    @FXML
    private void switchToTickets(MouseEvent event) {
    }
    
}
