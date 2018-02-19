/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.service.ServiceEtablissement;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private TableColumn<Etablissement, Button> ajoutCol;

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
    
}
