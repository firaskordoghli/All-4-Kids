/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.service.ServiceEvenement;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


/**
 * FXML Controller class
 *
 * @author slim
 */
public class GoogleMapEvenementController implements Initializable , MapComponentInitializedListener{
 
    @FXML
    private GoogleMapView mapView;
    @FXML
    private  Label titre;
   
       
    
    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       mapView.addMapInializedListener(this);
     }   
    public void setNom(String l){
        titre.setText(l);
    }
   ServiceEvenement se = new ServiceEvenement();
      
     
    @Override
    public void mapInitialized() {
        
         Evenement e = se.getIdByName("slim");
      LatLong location = new LatLong(e.getLatitude(), e.getLongitude());


       
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(e.getLatitude(), e.getLongitude()))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(9);

        GoogleMap map = mapView.createMap(mapOptions);

   
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(location).visible(Boolean.TRUE).title(e.getNom());
        Marker marker = new Marker(markerOptions1);
        map.addMarker(marker);

    }

   
    
}
