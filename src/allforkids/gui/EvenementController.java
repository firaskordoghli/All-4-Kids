/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Participevenement;
import allforkids.service.ServiceEvenement;
import allforkids.service.ServiceParticipevenement;
import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum ;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class EvenementController implements Initializable , MapComponentInitializedListener{

    @FXML
    private Tab consultt;
    @FXML
    private ListView<Label> listevent;
    @FXML
    private AnchorPane detail;
    @FXML
    private Label titre;
    @FXML
    private ImageView img;
    @FXML
    private Label description;
    @FXML
    private JFXButton inscript;
    @FXML
    private Tab mesconsult;
    @FXML
    private Tab inscrit;
    private Evenement event;
    private ServiceEvenement s = new ServiceEvenement();
    @FXML
    private ListView<?> myevent;
    @FXML
    private GoogleMapView mapView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Label> a = FXCollections.observableArrayList() ;
          List<Evenement> e = new ArrayList<>() ;
         
          e= s.selectEvenement();
          
          
          for (Evenement ev : e) {
                  try {
                      
             Label l = new Label( ev.getNom());
             l.setFont(Font.font("DK Cool Crayon", 30));
           ImageView i = new ImageView( new Image( new FileInputStream(ev.getPhoto())));
           i.setFitHeight(150);
           i.setFitWidth(250);
           
                 l.setGraphic(i);
                 listevent.getItems().add(l);
                 listevent.setStyle("-fx-background-color: #B7D7F2");
             }catch (FileNotFoundException ex) {
                 Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex); 
             }
         
        }
             ///map view 
              mapView.addMapInializedListener(this);
            /////////////////////////
         detail.setVisible(false);
    }    

    @FXML
    private void afficherImage(MouseEvent me) throws FileNotFoundException {
           detail.setVisible(true);
            String l = listevent.getSelectionModel().getSelectedItem().getText();
            event = s.getIdByName(l);
            titre.setText(l);
            description.setText("Lieu: "+event.getLieu()+"\n"+"Date: "+event.getDate().toString()+"\n"+"Type: "+event.getType());
            Image image =new Image(new FileInputStream(event.getPhoto()));
            img.setImage(image);
         
            
    }

    @FXML
    private void inscription(ActionEvent ev) {
         String l = listevent.getSelectionModel().getSelectedItem().getText();
            event = s.getIdByName(l);
          Participevenement p = new Participevenement(event.getId_evenement(),6,6);
          ServiceParticipevenement sp = new ServiceParticipevenement();
          sp.insrerParticipevenement(p);
         
    }

    @Override
    public void mapInitialized() {
      LatLong joeSmithLocation = new LatLong(47.6197, -122.3231);
        LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
        LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
        LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);
        
        
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
                  
        GoogleMap map = mapView.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);
        
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(joshAndersonLocation);
        
        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(bobUnderwoodLocation);
        
        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(tomChoiceLocation);
        
        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(fredWilkieLocation);
        
        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);
        Marker bobUnderwoodMarker = new Marker(markerOptions3);
        Marker tomChoiceMarker= new Marker(markerOptions4);
        Marker fredWilkieMarker = new Marker(markerOptions5);
        
        map.addMarker( joeSmithMarker );
        map.addMarker( joshAndersonMarker );
        map.addMarker( bobUnderwoodMarker );
        map.addMarker( tomChoiceMarker );
        map.addMarker( fredWilkieMarker );
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker);
    }
    
}
