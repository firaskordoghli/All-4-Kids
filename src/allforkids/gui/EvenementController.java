/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Participevenement;
import allforkids.entites.Session;
import allforkids.service.ServiceEvenement;
import allforkids.service.ServiceImage;
import allforkids.service.ServiceParticipevenement;
import allforkids.util.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class EvenementController implements Initializable {

    @FXML
    private Tab consultt;
    @FXML
    private ListView<Label> listevent;
    @FXML
    private AnchorPane detail;
    @FXML
    private Label titre;
    @FXML
    private ImageView img;
    @FXML
    private Label description;
    @FXML
    private JFXButton inscript;
    @FXML
    private Tab mesconsult;
    @FXML
    private Tab inscrit;
    public Evenement event;
    private ServiceEvenement s = new ServiceEvenement();
    @FXML
    private ListView<Label> myevent;
    private static String nnom;

    private DecimalFormat formatter = new DecimalFormat("###.00000");
    @FXML
    private DatePicker mdate;
    @FXML
    private JFXButton mimage;
    @FXML
    private ImageView mimg;
    @FXML
    private TextField mnom;
    @FXML
    private TextField mlieu;
    @FXML
    private TextField mnb;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton delete;
    @FXML
    private ChoiceBox<String> mtype;
    @FXML
    private Label metat;
    @FXML
    private AnchorPane modifay;
    @FXML
    private TextField mdescription;

    @FXML
    private Label enom;
    @FXML
    private Label nlieu;
    @FXML
    private Label ndate;
    @FXML
    private Label nnb;
    @FXML
    private Label nimg;
    public static Double longe = 10.195556;
    public static Double altud = 36.862499;
    private GoogleMapView mapvView;
    @FXML
    private JFXButton mimage1;
    @FXML
    private ListView<Label> listevent1;
    @FXML
    private AnchorPane detail1;
    @FXML
    private Label titre1;
    @FXML
    private ImageView img1;
    @FXML
    private Label description1;
    @FXML
    private JFXButton inscript1;
    private ServiceParticipevenement sp = new ServiceParticipevenement();

    @FXML
    private JFXTimePicker temp;
    @FXML
    private JFXButton libbtn;
    @FXML
    private JFXButton filmbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 libbtn.setVisible(false);
 filmbtn.setVisible(false);
        List<Evenement> me = s.selectMesEvenement(Session.getIdThisUser());
        List<Participevenement> pe = sp.selectParticipevenementByid(Session.getIdThisUser());
        List<Evenement> ppe = sp.selectEvenementByid(Session.getIdThisUser());
        if (pe.isEmpty()) {
            inscrit.setDisable(true);
        }
        if (me.isEmpty()) {
            mesconsult.setDisable(true);
        }
        /**
         * *******Evenement******
         */

        List<Evenement> e = new ArrayList<>();
        // int u = Session.getIdThisUser();

        e = s.selectEvenement();
        for (Evenement ev : e) {

            Label l = new Label(ev.getNom());
            l.setFont(Font.font("DK Cool Crayon", 30));
            ImageView i = new ImageView();
            i.setImage(new Image("ftp://slim:07471917@" + Session.getIp() + "/" + ev.getPhoto()));
            i.setFitHeight(90);
            i.setFitWidth(90);

            l.setGraphic(i);
            listevent.getItems().add(l);
            listevent.setStyle("-fx-background-color: #B7D7F2");

        }

        /**
         * **********Fin Evenement****************
         */
        /**
         * **********Mes Evenemet ***************
         */
        List<String> list = new ArrayList();
        list.add("musique");
        list.add("cinema");
        list.add("theatre");
        list.add("randonnée");
        list.add("magicien");
        list.add("Parck");
        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        mtype.setItems(ob);

        for (Evenement ev : me) {

            Label l = new Label(ev.getNom());
            l.setFont(Font.font("DK Cool Crayon", 30));
            ImageView i = new ImageView();
            i.setImage(new Image("ftp://slim:07471917@" + Session.getIp() + "/" + ev.getPhoto()));
            i.setFitHeight(90);
            i.setFitWidth(90);

            l.setGraphic(i);
            myevent.getItems().add(l);
            myevent.setStyle("-fx-background-color: #B7D7F2");

        }

        /**
         * **********Fin Mes Evenement**********
         */
        /**
         * ******* Evenment Inscrit*************
         */
        for (Evenement ev : ppe) {

            Label l = new Label(ev.getNom());
            l.setFont(Font.font("DK Cool Crayon", 30));
            ImageView i = new ImageView();
            i.setImage(new Image("ftp://slim:07471917@" + Session.getIp() + "/" + ev.getPhoto()));
            i.setFitHeight(90);
            i.setFitWidth(90);

            l.setGraphic(i);
            listevent1.getItems().add(l);
            listevent1.setStyle("-fx-background-color: #B7D7F2");

        }

        /*
        ********** FIn Evenment Inscrit*********
         */
        detail.setVisible(false);
        modifay.setVisible(false);
        detail1.setVisible(false);
    }

    @FXML
    private void afficherImage(MouseEvent me) throws FileNotFoundException {
        detail.setVisible(true);
        inscript.setDisable(false);
        String l = listevent.getSelectionModel().getSelectedItem().getText();
        for (Label o : myevent.getItems()) {
            if (o.getText().equals(l)) {
                inscript.setDisable(true);
            }
        }

        event = s.getIdByName(l);
        Participevenement e;
        e = sp.selectParticipevenementByid2(Session.getIdThisUser(), event.getId_evenement());
        int a = sp.nbparticipent(event.getId_evenement());
        a = event.getNbr_participation() - a;
        if ((e != null) || (a == 0)) {
            inscript.setDisable(true);
        }
        altud = event.getLatitude();
        longe = event.getLongitude();
        event = s.getIdByName(l);
        titre.setText(l);

        description.setText("Description: " + event.getDescriptionn() + "\n" + "Date: " + event.getDate().toString() + "A" + event.getTemp().toString() + "\n" + "Type: " + event.getType());
        Image image = new Image("ftp://slim:07471917@" + Session.getIp() + "/" + event.getPhoto());
        img.setImage(image);

    }

    @FXML
    private void inscription(ActionEvent ev) {
        String l = listevent.getSelectionModel().getSelectedItem().getText();
        event = s.getIdByName(l);
        Participevenement p = new Participevenement(event.getId_evenement(), Session.getIdThisUser(), 6);
        ServiceParticipevenement sp = new ServiceParticipevenement();
        sp.insrerParticipevenement(p);
        Alert2.afficherSuccses("Succses", "Votre inscription  à été  Enregistre avec succses");
        inscript.setDisable(true);
    }

    @FXML
    public void afficherMap(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GoogleMapEvenement.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            GoogleMapEvenementController c = fxmlLoader.getController();

            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void Modifierimg(ActionEvent even) throws IOException {
        Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"));
        File selectedFile = fil.showOpenDialog(stage);
        Image img = new Image(selectedFile.toURI().toString());
        String p = selectedFile.getPath();

        String name = mnom.getText();

        ServiceImage.saveimg(img, name, p, selectedFile);
    }

    @FXML
    private void UpdateEvenement(ActionEvent even) throws IOException, SQLException {

        String l = myevent.getSelectionModel().getSelectedItem().getText();

        if (this.controleSaisie(l)) {
            event = s.getIdByName(l);
            int id = event.getId_evenement();
            LocalDate d = mdate.getValue();
            Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (ServiceImage.getImgg().equals("")) {
                ServiceImage.setImgg(event.getPhoto());
            }
            Evenement e = new Evenement(mnom.getText(), mlieu.getText(), date,
                    mtype.getValue(), Integer.parseInt(mnb.getText()),
                    event.isEtat(), Session.getIdThisUser(), ServiceImage.getImgg(), altud, longe, java.sql.Time.valueOf(temp.getValue()));
            s.updateEvenement(e, id);
            Alert2.afficherSuccses("Succes", "Votre évenement à été modifier avec succses");

            modifay.setVisible(false);
        }
    }

    @FXML
    private void deleteEvenement(ActionEvent even) {

        String l = myevent.getSelectionModel().getSelectedItem().getText();
        event = s.getIdByName(l);
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("voulez-vous vraiment supprimer " + l + " ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            int id = event.getId_evenement();
            s.deleteEvenement(id);
            int a = myevent.getSelectionModel().getSelectedIndex();
            myevent.getItems().remove(a);
            modifay.setVisible(false);
        }

    }

    @FXML
    private void affichemodifay(MouseEvent even) throws FileNotFoundException {
        modifay.setVisible(true);
        String l = myevent.getSelectionModel().getSelectedItem().getText();
        event = s.getIdByName(l);
        altud = event.getLatitude();
        longe = event.getLongitude();
        mnom.setText(event.getNom());
        mlieu.setText(event.getDescriptionn());
        temp.setValue(event.getTemp().toLocalTime());
        Instant instant = Instant.ofEpochMilli(event.getDate().getTime());
        LocalDate datt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .toLocalDate();
        mdate.setValue(datt);
        mtype.setValue(event.getType());
        if (event.isEtat() == true) {
            metat.setText("L'evenment est complet");
        } else {
            int a = sp.nbparticipent(event.getId_evenement());
            metat.setText(String.valueOf(a) + "Participent");
        }
        Image image = new Image("ftp://slim:07471917@" + Session.getIp() + "/" + event.getPhoto());

        mnb.setText(String.valueOf(event.getNbr_participation()));
        mimg.setImage(image);

    }

    public boolean controleSaisie(String nom) throws IOException, SQLException {
        boolean saisie = true;
        ServiceEvenement es = new ServiceEvenement();

        if (!Validation.textalphabet(mnom, enom, "* le nom de doit contenir que des lettre")) {
            saisie = false;
        }

        if (!Validation.texAlphNum(mlieu, nlieu, "* la descrpiont  doit contenir que des lettre")) {
            saisie = false;
        }
        if (!Validation.texNum(mnb, nnb, "*  Nb Doit contenir que des Nombre")) {
            saisie = false;
        }

        if (!Validation.textValidation(mnom, enom, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(mlieu, nlieu, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(mnb, nnb, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        Evenement e = es.getIdByName(mnom.getText());
        if (!((e.getNom() == null) || (mnom.getText().equals(nom)))) {

            enom.setText("le Nom Existe deja !");
            saisie = false;
        }
        if ((mdate.getValue() == null)) {
            ndate.setText("* tout les champs doivent etre remplis");
            saisie = false;
        }

        if (mdate.getValue() != null) {
            LocalDate d = mdate.getValue();
            Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date date1 = new Date();
            if (date.before(date1)) {
                ndate.setText("* La Date est Deja passe");
                saisie = false;
            } else {
                ndate.setText("");
            }
        }
        if (temp.getValue() == null) {

        }
        int np = sp.nbparticipent(e.getId_evenement());
        int a = Integer.parseInt(mnb.getText());
        a = a - np;
        if (a <= 0) {
            nnb.setText("le nombre sasier est deja depasse");
            saisie = false;
        }

        return saisie;
    }

    @FXML
    private void ModifierLieu(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Modifgooglemap.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ModifgooglemapController c = fxmlLoader.getController();

            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void add(MouseEvent even) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Ajouter Evenement");
        Parent root = FXMLLoader.load(getClass().getResource("AjoutEvenment.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void afficherDetail(MouseEvent even) {
        detail1.setVisible(true);
        String l = listevent1.getSelectionModel().getSelectedItem().getText();
        event = s.getIdByName(l);
        altud = event.getLatitude();
        longe = event.getLongitude();
        event = s.getIdByName(l);
        titre1.setText(l);
        description1.setText("Description: " + event.getDescriptionn() + "\n" + "Date: " + event.getDate().toString() + " A" + event.getTemp().toString() + "\n" + "Type: " + event.getType());
        Image image1 = new Image("ftp://slim:07471917@" + Session.getIp() + "/" + event.getPhoto());
        img1.setImage(image1);
    }

    @FXML
    private void dinscription(ActionEvent even) {
        String l = listevent1.getSelectionModel().getSelectedItem().getText();
        event = s.getIdByName(l);
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("voulez-vous vraiment Anuller votre Reservation  " + l + " ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            int id = event.getId_evenement();
            sp.deleteParticipevenement(id, Session.getIdThisUser());
            int a = listevent1.getSelectionModel().getSelectedIndex();
            listevent1.getItems().remove(a);
            detail1.setVisible(false);
        }
    }

    public void toLib(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Librairie.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void toFilm(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Movie.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
