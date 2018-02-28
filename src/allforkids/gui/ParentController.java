/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.service.ServiceEtablissement;
import allforkids.service.ServiceNote;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FATNASSI
 */
public class ParentController implements Initializable {

    @FXML
    private AnchorPane detail;
    @FXML
    private ImageView imageview1;
    @FXML
    private Label nometablissement;
    @FXML
    private Label description;
    @FXML
    private Label type;
    @FXML
    private Label region;
    @FXML
    private Label ville;
    @FXML
    private TableView<Etablissement> tableview;
    @FXML
    private TableColumn<Etablissement, String> nomCol;
    @FXML
    private TableColumn<Etablissement, String> typeCol;
    @FXML
    private AnchorPane detail8;
    @FXML
    private PieChart pourcentage;
    @FXML
    private TableView<Etablissement> tableview8;
    @FXML
    private TableColumn<Etablissement, String> nomCol311;
    @FXML
    private TableColumn<Etablissement, String> typeCol311;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ServiceEtablissement sr1 = new ServiceEtablissement();
        try {
            tableview.setItems(sr1.selectEtablissement1());

        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        /////// stat
        nomCol311.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol311.setCellValueFactory(new PropertyValueFactory<>("type"));

        ServiceEtablissement sr5 = new ServiceEtablissement();

        //User u1 = new User();
        try {
            tableview8.setItems(sr1.selectEtablissement1());

        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        
        detail.setVisible(false);
        detail8.setVisible(false);
    }    

    @FXML
    private void consulter(MouseEvent event) {
        detail.setVisible(true);
        ServiceEtablissement sr1 = new ServiceEtablissement();

        Etablissement e = sr1.GetEtablissemebtById(tableview.getSelectionModel().getSelectedItem().getId());
        nometablissement.setText(e.getNom());
        description.setText(e.getDescription());
        type.setText(e.getType());
        region.setText(e.getRegion());
        ville.setText(e.getVille());
        Image img;
        try {
            img = new Image(new FileInputStream(e.getImage()));
            imageview1.setImage(img);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    

    @FXML
    private void consulter8(MouseEvent event) {
        detail8.setVisible(false);
        int a = tableview8.getSelectionModel().getSelectedItem().getId();
        ServiceNote sn =new ServiceNote();
        if(sn.verification(a)==0){
            Alert.afficher("Impossible","Note non saisi pour cette établissement");
        }
        else{
            detail8.setVisible(true);
        
        
        
        pourcentage.setData(createPieChart().getData());
        pourcentage.setLabelsVisible(true);
        }
        
    }
    private PieChart createPieChart() {
        int et = tableview8.getSelectionModel().getSelectedItem().getId();
        ServiceNote sn = new ServiceNote();
        
        ObservableList<PieChart.Data> donner = FXCollections.observableArrayList(
             new PieChart.Data("Note suppérieur à 10", sn.statistiquesupp(et)),
             new PieChart.Data("Note inférieur à 10", sn.statistiqueinf(et))
        );
        
        
     
    return new PieChart(donner);
    }
    
}
