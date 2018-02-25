/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DetailCovController implements Initializable {

    @FXML
    private WebView directionswebview;
    private WebEngine webEngine;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       webEngine = directionswebview.getEngine();
       webEngine.load(getClass().getResource("directionspage.html").toString());
    }    

    @FXML
    private void getDirections(ActionEvent event) {
        webEngine.executeScript("getDirections(35.82450290000001, 10.634584000000018,33.886917, 9.537499000000025);");
  //webEngine.executeScript("initMap();");
    }
    
}
