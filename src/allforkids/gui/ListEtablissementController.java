/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.service.ServiceEtablissement;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author FATNASSI
 */
public class ListEtablissementController implements Initializable {

    @FXML
    private AnchorPane listview;
    @FXML
    private TableView<Etablissement> tableview;
    @FXML
    private TableColumn<Etablissement, String> nomCol;
    @FXML
    private TableColumn<Etablissement, String> typeCol;

   public static int nb ;
    @FXML
    private JFXButton ajout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
         ServiceEtablissement sr1= new ServiceEtablissement();
         try {
             tableview.setItems(sr1.selectEtablissement1());
          
               
          } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void consulter(MouseEvent event) throws IOException {
        Stage stage = new Stage();
    nb=tableview.getSelectionModel().getSelectedItem().getId();
             stage.setTitle("Ajouter Etablissement");
        Parent root = FXMLLoader.load(getClass().getResource("DétailEtablissement.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        Stage stage = new Stage();
             stage.setTitle("Ajouter Etablissement");
        Parent root = FXMLLoader.load(getClass().getResource("AddEtablissment.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.show();
    }
    
}
