/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import static allforkids.gui.EvenementController.altud;
import static allforkids.gui.EvenementController.longe;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class ModifgooglemapController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }    

    @Override
    public void mapInitialized() {
          
        LatLong location = new LatLong(EvenementController.altud,  EvenementController.longe);
       

        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(EvenementController.altud,  EvenementController.longe))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        GoogleMap map = mapView.createMap(mapOptions);

        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(location).visible(Boolean.TRUE).title("My Event");
        Marker marker1 = new Marker(markerOptions1);
        map.addMarker(marker1);
        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent even) -> {

            map.clearMarkers();

            LatLong latLong = even.getLatLong();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLong).visible(Boolean.TRUE).title("fdshg");
            Marker marker = new Marker(markerOptions);
            map.addMarker(marker);
            EvenementController.altud = latLong.getLatitude();
            EvenementController.longe = latLong.getLongitude();
        });
    }
    
}
