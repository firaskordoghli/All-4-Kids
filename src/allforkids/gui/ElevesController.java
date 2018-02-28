/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.entites.Note;
import allforkids.entites.Session;
import allforkids.service.ServiceEtablissement;
import allforkids.service.ServiceNote;
import allforkids.service.ServiceRejoindre;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class ElevesController implements Initializable {

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
    private JFXButton rejoindrebt;
    @FXML
    private JFXButton consulter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ServiceEtablissement sr1 = new ServiceEtablissement();
        ServiceRejoindre sr2= new ServiceRejoindre();
        try {
            tableview.setItems(sr1.selectEtablissement1());

        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        
        detail.setVisible(false);
        rejoindrebt.setVisible(false);
        consulter.setVisible(false);
    }    

    @FXML
    private void consulter(MouseEvent event) {
        detail.setVisible(true);
        rejoindrebt.setVisible(false);
        consulter.setVisible(false);
        
        ServiceRejoindre sr2= new ServiceRejoindre();
        int a = tableview.getSelectionModel().getSelectedItem().getId();
        int b =Session.getIdThisUser();
        if(sr2.SelectIfDejaExiste(a, b) == null){
            rejoindrebt.setVisible(true);
        }
        else{
            if(sr2.SelectIfDejaExiste(a, b).getVerification().equals("Valide")){
             consulter.setVisible(true);   
            } 
        }
        
        
        
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
            System.out.println(ex); }
    }

    @FXML
    private void rejoindre(ActionEvent event) {
        
        
        int a = tableview.getSelectionModel().getSelectedItem().getId();
        int b =Session.getIdThisUser();
        ServiceRejoindre sr1= new ServiceRejoindre();
       
            sr1.insrerRejoindre(a, b);
            Alert.afficher("Succée", " Votre demande à été envoyé avec succès");
        
        
           rejoindrebt.setVisible(false);
           consulter.setVisible(true);
         
    }

    @FXML
    private void comsulter(ActionEvent event) {
        ServiceNote sr = new ServiceNote();
        Float moyenne= sr.SelectMoyenneById(Session.getIdThisUser()).getMoyenne();
        System.out.println(moyenne);
        
            Alert.afficher("Moyenne", "Votre moyenne est : "+moyenne);
        
       
    }
    
}
