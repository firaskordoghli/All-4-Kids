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
public class LibrairieController implements Initializable {

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
    private Viewer viewr;
    @FXML
    private JFXTextField cherche1;
    @FXML
    private JFXButton search1;
    @FXML
    private JFXListView<Label> listconte610;
    @FXML
    private AnchorPane detail1;
    @FXML
    private AnchorPane titrecont1;
    @FXML
    private Label contett1;
    @FXML
    private ImageView conteimg1;
    @FXML
    private Label description1;
    @FXML
    private JFXButton like1;
    @FXML
    private JFXButton detest1;
    @FXML
    private Label nblike1;
    @FXML
    private Label nbdslike1;
    @FXML
    private JFXButton lire1;
    @FXML
    private JFXTextField cherche2;
    @FXML
    private JFXButton search2;
    @FXML
    private AnchorPane detail2;
    @FXML
    private AnchorPane titrecont2;
    @FXML
    private Label contett2;
    @FXML
    private ImageView conteimg2;
    @FXML
    private Label description2;
    @FXML
    private JFXButton like2;
    @FXML
    private JFXButton detest2;
    @FXML
    private Label nblike2;
    @FXML
    private Label nbdslike2;
    @FXML
    private JFXButton lire2;
    @FXML
    private JFXListView<Label> listcours;
    @FXML
    private JFXTextField cherche21;
    @FXML
    private JFXButton search21;
    @FXML
    private AnchorPane detail3;
    @FXML
    private AnchorPane titrecont3;
    @FXML
    private ImageView conteimg3;
    @FXML
    private Label description3;
    @FXML
    private JFXButton like3;
    @FXML
    private JFXButton detest3;
    @FXML
    private JFXButton lire3;
    @FXML
    private JFXListView<Label> listparascolaire;
    @FXML
    private Label contett3;
    @FXML
    private Label nblike3;
    @FXML
    private Label nbdslike3;
    @FXML
    private JFXTextField cherche3;
    @FXML
    private JFXButton search3;
    @FXML
    private JFXListView<Label> listgied;
    @FXML
    private AnchorPane detail4;
    @FXML
    private AnchorPane titrecont4;
    @FXML
    private Label contett4;
    @FXML
    private ImageView conteimg4;
    @FXML
    private Label description4;
    @FXML
    private JFXButton like4;
    @FXML
    private JFXButton detest4;
    @FXML
    private Label nblike4;
    @FXML
    private Label nbdslike4;
    @FXML
    private JFXButton lire4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        affichelist(listconte25, lire, detail, "conte 2-5 ans");
        affichelist(listconte610, lire1, detail1, "conte 6-10 ans");
        affichelist(listcours, lire2, detail2, "Cours");
        affichelist(listparascolaire, lire3, detail3, "parascolaire");
        affichelist(listgied, lire4, detail4, "gied parental");
    }

    @FXML
    private void afichdetail(MouseEvent event) {
        detailLiver(detail, lire, listconte25, contett, conteimg, description, nblike, nbdslike, like, detest);

    }

    @FXML
    private void likee(ActionEvent event) {
        likes(listconte25, nblike, detest);
    }

    @FXML
    private void detestt(ActionEvent event) {
        dedetests(listconte25, nbdslike, like);
    }

    @FXML
    private void openconte(ActionEvent event) throws Exception {

        lireconte(listconte25);
    }

    @FXML
    private void afichdetail1(MouseEvent event) {
        detailLiver(detail1, lire1, listconte610, contett1, conteimg1, description1, nblike1, nbdslike1, like1, detest1);
    }

    @FXML
    private void likee1(ActionEvent event) {
        likes(listconte610, nblike1, detest1);
    }

    @FXML
    private void detestt1(ActionEvent event) {
        dedetests(listconte610, nbdslike1, like1);
    }

    @FXML
    private void openconte1(ActionEvent event) throws Exception {
        lireconte(listconte610);
    }

    @FXML
    private void likee2(ActionEvent event) {
        likes(listcours, nblike2, detest2);
    }

    @FXML
    private void detestt2(ActionEvent event) {
        dedetests(listcours, nbdslike2, like2);
    }

    @FXML
    private void afichdetail2(MouseEvent event) {
        detailLiver(detail2, lire2, listcours, contett2, conteimg2, description2, nblike2, nbdslike2, like2, detest2);
    }

    @FXML
    private void openconte2(ActionEvent event) throws Exception {
        lireconte(listcours);
    }

    @FXML
    private void likee3(ActionEvent event) {
        likes(listparascolaire, nblike3, detest3);
    }

    @FXML
    private void detestt3(ActionEvent event) {
        dedetests(listparascolaire, nbdslike3, like3);
    }

    @FXML
    private void openconte3(ActionEvent event) throws Exception {
        lireconte(listparascolaire);
    }

    @FXML
    private void afichdetail3(MouseEvent event) {
        detailLiver(detail3, lire3, listparascolaire, contett3, conteimg3, description3, nblike3, nbdslike3, like3, detest3);
    }

    @FXML
    private void afichdetail4(MouseEvent event) {
        detailLiver(detail4, lire4, listgied, contett4, conteimg4, description4, nblike4, nbdslike4, like4, detest4);
    }

    @FXML
    private void likee4(ActionEvent event) {
        likes(listgied, nblike4, detest4);
    }

    @FXML
    private void detestt4(ActionEvent event) {
        dedetests(listgied, nbdslike4, like4);
    }

    @FXML
    private void openconte4(ActionEvent event) throws Exception {
        lireconte(listgied);
    }

    /**
     * Pour afficher les list des livre
     *
     */
    private void affichelist(JFXListView<Label> list, JFXButton b, AnchorPane ap, String c) {
        List<Livre> l = new ArrayList<>();
        // int u = Session.getIdThisUser();

        l = sl.selectLivreByCatg(c);

        for (Livre lv : l) {

            Label la = new Label(lv.getNom());
            la.setFont(Font.font("DK Cool Crayon", 30));
            ImageView i = new ImageView();
            i.setImage(new Image("ftp://slim:07471917@" + Session.getIp() + "/" + lv.getPhoto()));

            i.setFitHeight(90);
            i.setFitWidth(90);

            la.setGraphic(i);
            list.getItems().add(la);
            list.setStyle("-fx-background-color: #B7D7F2");

        }
        ap.setVisible(false);
        b.setVisible(false);
    }

    /*
    *Aficher detail d'un livre 
     */
    public void detailLiver(AnchorPane ap, JFXButton j, JFXListView<Label> list, Label leb,
            ImageView im, Label des, Label lik, Label notlik, JFXButton jlik, JFXButton jnlik) {
        ap.setVisible(true);
        j.setVisible(true);
        Liverlike lvl = new Liverlike();
        ServiceLiverlike slvl = new ServiceLiverlike();
        lvl = slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8);
        String l = list.getSelectionModel().getSelectedItem().getText();
        livre = sl.selectLivreByName(l);
        leb.setText(livre.getNom());
        Image image = new Image("ftp://slim:07471917@" + Session.getIp() + "/" + livre.getPhoto());
        im.setImage(image);
        des.setText("Type: " + livre.getType() + "\n" + "Description:\n" + livre.getDescription());

        if ((slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8).getId_liver() == 0)) {
            lik.setText(String.valueOf(livre.getGood()) + "persones");
            notlik.setText(String.valueOf(livre.getBad()) + "persones");
            jlik.setDisable(false);
            jnlik.setDisable(false);
        } else if ((slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8).getGood() == 0)) {
            lik.setText(String.valueOf(livre.getGood()) + "persones");
            notlik.setText("vous et " + String.valueOf(livre.getBad() - 1) + "persones");
            jlik.setDisable(true);
            jnlik.setDisable(false);
        } else {
            lik.setText("vous et " + String.valueOf(livre.getGood() - 1) + "persones");
            notlik.setText(String.valueOf(livre.getBad()) + "persones");

            jnlik.setDisable(true);
            jlik.setDisable(false);
        }

    }

    /*
    * Ajouter des like 
     */
    public void likes(JFXListView<Label> list, Label lik, JFXButton jdlik) {
        Liverlike lvl = new Liverlike();
        ServiceLiverlike slvl = new ServiceLiverlike();
        String l = list.getSelectionModel().getSelectedItem().getText();
        livre = sl.selectLivreByName(l);
        lvl = slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8);

        if ((lvl == null) || (lvl.getGood() == 0)) {
            lvl = new Liverlike(livre.getId_livre(), 8, 1, 0);
            slvl.insrerLiverlike(lvl);
            livre.setGood(livre.getGood() + 1);
            sl.updateLivre(livre, livre.getId_livre());
            lik.setText("vous et " + String.valueOf(livre.getGood() - 1) + "autres persones");
            jdlik.setDisable(true);
        } else {
            lvl = new Liverlike(livre.getId_livre(), 8, 1, 0);
            ServiceLiverlike.deleteLiverlike(livre.getId_livre(), 8);
            livre.setGood(livre.getGood() - 1);
            sl.updateLivre(livre, livre.getId_livre());
            lik.setText(String.valueOf(livre.getGood()) + "persones");
            jdlik.setDisable(false);
        }

    }

    /*
     * Ajouter detest 
     */
    public void dedetests(JFXListView<Label> list, Label des, JFXButton lik) {
        Liverlike lvl = new Liverlike();
        ServiceLiverlike slvl = new ServiceLiverlike();
        String l = list.getSelectionModel().getSelectedItem().getText();
        livre = sl.selectLivreByName(l);
        lvl = slvl.selectliverlikeByIdlivreuser(livre.getId_livre(), 8);

        if ((lvl == null) || (lvl.getBad() == 0)) {
            lvl = new Liverlike(livre.getId_livre(), 8, 0, 1);
            slvl.insrerLiverlike(lvl);
            livre.setBad(livre.getBad() + 1);
            sl.updateLivre(livre, livre.getId_livre());
            des.setText("vous et " + String.valueOf(livre.getBad() - 1) + "autres persones");
            lik.setDisable(true);
        } else {
            lvl = new Liverlike(livre.getId_livre(), 8, 1, 0);
            ServiceLiverlike.deleteLiverlike(livre.getId_livre(), 8);
            livre.setBad(livre.getBad() - 1);
            sl.updateLivre(livre, livre.getId_livre());
            des.setText(String.valueOf(livre.getBad()) + "persones");
            lik.setDisable(false);
        }

    }

    /*
     * ouvrir pdf 
     */
    public void lireconte(JFXListView<Label> list) throws Exception {
        String l = list.getSelectionModel().getSelectedItem().getText();
        livre = sl.selectLivreByName(l);
        JFrame f = new JFrame(l);
        f.setSize(1024, 768);
        f.setLocationRelativeTo(null);


        ServicePdf pdf = new ServicePdf("ftp://slim:07471917@" + Session.getIp() + livre.getUrl());
        f.setVisible(true);
        f.getContentPane().add(pdf);

    }

}
