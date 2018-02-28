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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyEvent;
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
    private TableColumn<Transport, String> Type;
    @FXML
    private Button load;
    
    private ServiceCovoiturage s = new ServiceCovoiturage();
    
    private ServiceTransportrejoindr st = new ServiceTransportrejoindr();
    
    ObservableList<Transport> data;
    
    FilteredList filter ;
    
    @FXML
    private Label region;
    
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
    private TableColumn<Transport, String> Type1;
    @FXML
    private Button load1;
    
    public static String CorDep ;
    
    public static String CorArr;
    
    public static String DepLat ;
    public static String DepLng ;
    public static String ArrLat;
    public static String ArrLng ;
    @FXML
    private JFXTextField searsh;
    @FXML
    private JFXButton rejoindrebtn;
    @FXML
    private JFXButton supBtn;
    
    
    
    
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
        detailsBox.setVisible(false);
        data = FXCollections.observableArrayList();
        data.addAll(s.selectCov());
        
        filter = new FilteredList(data,e->true);
        
        depart.setCellValueFactory(new PropertyValueFactory<>("departName"));
        arrivé.setCellValueFactory(new PropertyValueFactory<>("arriveName"));
        place.setCellValueFactory(new PropertyValueFactory<>("place"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        

        tableTransport.setItems(null);
        tableTransport.setItems(data);
        
        
        
    }
    
    @FXML
    public void showDetail(MouseEvent D)
    {   
        detailsBox.setVisible(true);
        rejoindrebtn.setVisible(true);
        supBtn.setVisible(true);
        // TODO
        row = tableTransport.getSelectionModel().getSelectedItem();
        region.setText(row.getRegion());
        departt.setText(row.getDepartName());
        arrivee.setText(row.getArriveName());
        description.setText(row.getDescription());
        tel.setText(row.getTelephone());
        placee.setText(row.getPlace());
        frais.setText(row.getFrais());
        typee.setText(row.getType());
        
        int u  = Session.getIdThisUser();
        row = tableTransport.getSelectionModel().getSelectedItem();
        int id = row.getId_transport();
        Transportrejoindr tr = new Transportrejoindr(id,u);
        
        if (st.getCovUser(tr).isEmpty() == false ){
            rejoindrebtn.setVisible(false);
            System.out.println(st.getCovUser(tr));
        } 
        
        if (row.getId_user()== Session.getIdThisUser() ){
            supBtn.setVisible(false);
            System.out.println(st.getCovUser(tr));
        } 
        
    }
    
    @FXML
    public void getCov(ActionEvent event) throws IOException{
        
        row = tableTransport.getSelectionModel().getSelectedItem();
        
        CorDep = (row.getDepart());
        CorArr = (row.getArrivé());

        Parent root = FXMLLoader.load(getClass().getResource("DetailCov.fxml"));
                Scene scene = new Scene(root);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
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
            /*
                Parent covViewOarent = FXMLLoader.load(getClass().getResource("CovFormulaire.fxml"));
                Scene covViewScene = new Scene(covViewOarent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                window.setScene(covViewScene);
                window.show();
                */
                Parent root = FXMLLoader.load(getClass().getResource("CovFormulaire.fxml"));
                Scene scene = new Scene(root);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
            
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
        
        String num = row.getTelephone();
        System.out.println(num);
        
        sendSMS  send = new sendSMS() ;
        
        send.sendSms(num);
    }
    
    
     public void showTransportHistor()
    {
           
        detailsBox.setVisible(false);
        data = FXCollections.observableArrayList();
        ServiceTransportrejoindr str = new ServiceTransportrejoindr();
        data.addAll(str.selectTransportrejoindhist(Session.getIdThisUser()));
        
        depart1.setCellValueFactory(new PropertyValueFactory<>("departName"));
        arrivé1.setCellValueFactory(new PropertyValueFactory<>("arriveName"));
        place1.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableTransport1.setItems(null);
        tableTransport1.setItems(data);
    }

    private void voirMap(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("DetailCov.fxml"));
                Scene scene = new Scene(root);
                Stage driverStage = new Stage();
                driverStage.setScene(scene);
                driverStage.show();
    }
    
    @FXML
    private void searshDepart(KeyEvent event) {
        searsh.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super Transport>) (Transport transport)->{
                if (newValue.isEmpty() || newValue==null){
                    return true;
                }
                else if (transport.getDepartName().contains(newValue)){
                    return true;
                }
                return false; 
            });
            SortedList sort = new SortedList(filter);
            sort.comparatorProperty().bind(tableTransport.comparatorProperty());
            tableTransport.setItems(sort);
            System.out.println(filter);
            
        });
    }
    


}
//rejoindrebtn.setVisible(false);