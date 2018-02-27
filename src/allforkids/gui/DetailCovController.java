/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import static allforkids.gui.CovoiturageViewController.CorArr;
import static allforkids.gui.CovoiturageViewController.CorDep;
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
        String CorDep =CovoiturageViewController.CorDep;
        String CorArr =CovoiturageViewController.CorArr;
        
        CorDep = CorDep.substring(1);
        CorArr = CorArr.substring(1);
        
        CorDep = CorDep.substring(0, CorDep.length() - 1);
        CorArr = CorArr.substring(0, CorArr.length() - 1);
        
        String[] partsDepart = CorDep.split(",");
        String DepLat = partsDepart[0]; 
        String DepLng = partsDepart[1];
        
        String[] partsArrivé = CorArr.split(",");
        String ArrLat = partsArrivé[0]; 
        String ArrLng = partsArrivé[1];

        
        
        webEngine.executeScript("getDirections("+DepLat+","+DepLng+","+ArrLat+","+ArrLng+");");
  //webEngine.executeScript("initMap();");
    }
    
}
 
