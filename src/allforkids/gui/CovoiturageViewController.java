/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Session;
import allforkids.entites.Transport;
import allforkids.entites.Transportrejoindr;
import allforkids.service.ServiceCovoiturage;
import allforkids.service.ServiceTransportrejoindr;
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
import javafx.scene.layout.GridPane;
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
    @FXML
    private Label description;
    @FXML
    private Label departt;
    @FXML
    private Label arrivee;
    @FXML
    private Label tel;
    @FXML
    private Label frais;
    @FXML
    private Label typee;
    @FXML
    private Label placee;
    @FXML
    private Label tttype;
    @FXML
    private AnchorPane details;
    @FXML
    private TableView<Transport> tableTransport1;
    @FXML
    private TableColumn<Transport, String> depart1;
    @FXML
    private TableColumn<Transport, String> arrivé1;
    @FXML
    private TableColumn<Transport, String> place1;
    @FXML
    private Button load1;
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
       showTransportHistor();
       
    }
    public void showTransport()
    {
     // TODO
        details.setVisible(false);
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
        details.setVisible(true);
        // TODO
        row = tableTransport.getSelectionModel().getSelectedItem();
        region.setText(row.getRegion());
        ville.setText(row.getVille());
        departt.setText(row.getDepart());
        arrivee.setText(row.getArrivé());
        description.setText(row.getDescription());
        tel.setText(row.getTelephone());
        placee.setText(row.getPlace());
        frais.setText(row.getFrais());
        if(row.getType()==1){
        typee.setText("Full");
        }else{
           typee.setVisible(false);
           tttype.setVisible(false);
        }
    }
    
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

    @FXML
    private void RejoindreCov(ActionEvent event) {
       int u  = Session.getIdThisUser();
        row = tableTransport.getSelectionModel().getSelectedItem();
        int id = row.getId_transport();
        Transportrejoindr tr = new Transportrejoindr(id,u);
        ServiceTransportrejoindr str = new ServiceTransportrejoindr();
        str.insrerTransportrejoindr(tr);
        
    }
     public void showTransportHistor()
    {
           
        details.setVisible(false);
        data = FXCollections.observableArrayList();
        ServiceTransportrejoindr str = new ServiceTransportrejoindr();
        data.addAll(str.selectTransportrejoindhist(Session.getIdThisUser()));
        
        depart1.setCellValueFactory(new PropertyValueFactory<>("depart"));
        arrivé1.setCellValueFactory(new PropertyValueFactory<>("arrivé"));
        place1.setCellValueFactory(new PropertyValueFactory<>("place"));

        tableTransport1.setItems(null);
        tableTransport1.setItems(data);
    }
}
