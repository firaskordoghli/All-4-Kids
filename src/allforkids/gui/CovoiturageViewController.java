/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Transport;
import allforkids.service.ServiceCovoiturage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private Label region;
    @FXML
    private Label ville;
    
    private Transport row;
    @FXML
    private AnchorPane ajoutCovoiturage;
    @FXML
    private VBox detailsBox;
    @FXML
    private AnchorPane pane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    showTransport();
    }
    
    @FXML
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
    
    @FXML
    public void showDetail(MouseEvent D)
    {
        // TODO
        row = tableTransport.getSelectionModel().getSelectedItem();
        region.setText(row.getRegion());
        ville.setText(row.getVille());
        
    }
    
    @FXML
    public void ajoutCov(MouseEvent event) {
            try {
                ajoutCovoiturage.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("CovFormulaire.fxml"));
                Scene scene = new Scene(root);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
            } catch (IOException ex) {
                Logger.getLogger(CovoiturageViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    @FXML
    public void toFormulaire(MouseEvent event) throws IOException {
            
                Parent covViewOarent = FXMLLoader.load(getClass().getResource("CovFormulaire.fxml"));
                Scene covViewScene = new Scene(covViewOarent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                window.setScene(covViewScene);
                window.show();
            
        }

    @FXML
    private void deleteCov(ActionEvent event) {
        row = tableTransport.getSelectionModel().getSelectedItem();
        int id = row.getId_transport();
        s.deleteCovoiturage(id);
          int a = tableTransport.getSelectionModel().getFocusedIndex();
          tableTransport.getItems().remove(a);
    }
    
    @FXML
    public void backToTransport(MouseEvent event) throws IOException {
            
                Parent covViewOarent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Scene covViewScene = new Scene(covViewOarent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                window.setScene(covViewScene);
                window.show();
            
        }
}
