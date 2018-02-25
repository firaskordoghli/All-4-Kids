/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Liverlike;
import allforkids.entites.Livre;
import allforkids.entites.Session;
import allforkids.service.ServiceLiverlike;
import allforkids.service.ServiceLivre;
import allforkids.service.ServicePdf;
import com.adobe.acrobat.Viewer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class LibrairieController  implements Initializable {

    @FXML
    private AnchorPane gied;
    @FXML
    private Tab conte25;
    @FXML
    private JFXTextField cherche;
    @FXML
    private JFXButton search;
    @FXML
    private AnchorPane titrecont;
    @FXML
    private ImageView conteimg;
    @FXML
    private Label description;
    @FXML
    private Tab parascolaire;
    @FXML
    private Label titre;
    @FXML
    private JFXListView<Label> listconte25;
    ServiceLivre sl = new ServiceLivre();
    Livre livre = new Livre();
    @FXML
    private AnchorPane detail;
    @FXML
    private Label contett;
    @FXML
    private Label nblike;
    @FXML
    private Label nbdslike;
    @FXML
    private JFXButton like;
    @FXML
    private JFXButton detest;
    @FXML
    private JFXButton lire;
    private Viewer viewr ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Label> a = FXCollections.observableArrayList();
        List<Livre> l = new ArrayList<>();
        // int u = Session.getIdThisUser();

        l = sl.selectLivreByCatg("conte 2-5 ans");
  
        for (Livre lv : l) {

            Label la = new Label(lv.getNom());
            la.setFont(Font.font("DK Cool Crayon", 30));
            ImageView i = new ImageView();
            i.setImage(new Image("ftp://slim:07471917@" + Session.getIp() + "/" + lv.getPhoto()));

            i.setFitHeight(90);
            i.setFitWidth(90);

            la.setGraphic(i);
            listconte25.getItems().add(la);
            listconte25.setStyle("-fx-background-color: #B7D7F2");

        }
        detail.setVisible(false);
    }

    @FXML
    private void afichdetail(MouseEvent event) {
        detail.setVisible(true);
           Liverlike lvl = new Liverlike();
            ServiceLiverlike slvl = new ServiceLiverlike();
            lvl = slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8);
        String l = listconte25.getSelectionModel().getSelectedItem().getText();
        livre = sl.selectLivreByName(l);
        contett.setText(livre.getNom());
        Image image = new Image("ftp://slim:07471917@" + Session.getIp() + "/" + livre.getPhoto());
        conteimg.setImage(image);
        description.setText("Type: " + livre.getType() + "\n" + "Description:\n" + livre.getDescription());
        
        if((slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8).getId_liver()==0) ){
         nblike.setText(String.valueOf(livre.getGood())+"persones");
         nbdslike.setText(String.valueOf(livre.getBad()) + "persones");
          like.setDisable(false);
          detest.setDisable(false);
        }else if((slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8).getGood() == 0)){
         nblike.setText(String.valueOf(livre.getGood())+"persones");
         nbdslike.setText("vous et " +String.valueOf(livre.getBad()-1) + "persones");
          like.setDisable(true);
          detest.setDisable(false);
        }else{
        nblike.setText("vous et " +String.valueOf(livre.getGood()-1)+"persones");
         nbdslike.setText(String.valueOf(livre.getBad()) + "persones");
       
          detest.setDisable(true);
          like.setDisable(false);
        }
        
    }

    @FXML
    private void likee(ActionEvent event) {
        Liverlike lvl = new Liverlike();
        ServiceLiverlike slvl = new ServiceLiverlike();
        String l = listconte25.getSelectionModel().getSelectedItem().getText();
        livre = sl.selectLivreByName(l);
        lvl = slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8);

        if ((lvl == null) || (lvl.getGood() == 0)) {
            lvl = new Liverlike(livre.getId_livre(), 8, 1, 0);
            slvl.insrerLiverlike(lvl);
            livre.setGood(livre.getGood() + 1);
            sl.updateLivre(livre, livre.getId_livre());
            nblike.setText("vous et " + String.valueOf(livre.getGood() - 1) + "autres persones");
            detest.setDisable(true);
        } else {
            lvl = new Liverlike(livre.getId_livre(), 8, 1, 0);
            ServiceLiverlike.deleteLiverlike(livre.getId_livre(), 8);
            livre.setGood(livre.getGood() - 1);
            sl.updateLivre(livre, livre.getId_livre());
            nblike.setText(String.valueOf(livre.getGood()) + "persones");
            detest.setDisable(false);
        }
    }

    @FXML
    private void detestt(ActionEvent event) {
        Liverlike lvl = new Liverlike();
        ServiceLiverlike slvl = new ServiceLiverlike();
        String l = listconte25.getSelectionModel().getSelectedItem().getText();
        livre = sl.selectLivreByName(l);
        lvl = slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8);

        if ((lvl == null) || (lvl.getBad() == 0)) {
            lvl = new Liverlike(livre.getId_livre(), 8, 0, 1);
            slvl.insrerLiverlike(lvl);
            livre.setBad(livre.getBad() + 1);
            sl.updateLivre(livre, livre.getId_livre());
            nbdslike.setText("vous et " + String.valueOf(livre.getBad() - 1) + "autres persones");
            like.setDisable(true);
        } else {
            lvl = new Liverlike(livre.getId_livre(), 8, 1, 0);
            ServiceLiverlike.deleteLiverlike(livre.getId_livre(), 8);
            livre.setBad(livre.getBad() - 1);
            sl.updateLivre(livre, livre.getId_livre());
            nbdslike.setText(String.valueOf(livre.getBad()) + "persones");
            like.setDisable(false);
        }
    }

    @FXML
    private void openconte(ActionEvent event) throws Exception {
       
        String l = listconte25.getSelectionModel().getSelectedItem().getText();
        livre = sl.selectLivreByName(l);
        JFrame f = new JFrame(l);
        f.setSize(1024, 768);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ServicePdf pdf = new ServicePdf("ftp://slim:07471917@"+Session.getIp()+livre.getUrl());
        f.setVisible(true);
        f.getContentPane().add(pdf);
    }

}
