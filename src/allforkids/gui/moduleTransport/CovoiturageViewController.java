/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui.moduleTransport;

import allforkids.entites.Transport;
import allforkids.service.ServiceCovoiturage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CovoiturageViewController implements Initializable {

    @FXML
    private TableView<Transport> tableTransport;
    @FXML
    private TableColumn<Transport, String> depart;
    @FXML
    private TableColumn<Transport, String> arrivé;
    @FXML
    private TableColumn<Transport, String> place;
    @FXML
    private Button load;
    
    private final ServiceCovoiturage s = new ServiceCovoiturage();
    
    ObservableList<Transport> data;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    showTransport();
    }
    
    public void loadDataFromDatabase(ActionEvent event) {
       showTransport();
    }
    public void showTransport()
    {
     // TODO
        data = FXCollections.observableArrayList();
        data.addAll(s.selectCov());
        
        depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        arrivé.setCellValueFactory(new PropertyValueFactory<>("arrivé"));
        place.setCellValueFactory(new PropertyValueFactory<>("place"));

        tableTransport.setItems(null);
        tableTransport.setItems(data);
    }
}
