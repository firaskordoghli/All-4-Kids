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
import allforkids.service.ServiceParticipevenement;
import allforkids.util.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private TextField mtemp;
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

    private static String imgg = "";
    public static Double longe = 10.195556;
    public static Double altud = 36.862499;
    private GoogleMapView mapvView;
    @FXML
    private JFXButton mimage1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * *******Evenement******
         */
        ObservableList<Label> a = FXCollections.observableArrayList();
        List<Evenement> e = new ArrayList<>();

        e = s.selectEvenement();

        for (Evenement ev : e) {
            try {

                Label l = new Label(ev.getNom());
                l.setFont(Font.font("DK Cool Crayon", 30));
                ImageView i = new ImageView(new Image(new FileInputStream(ev.getPhoto())));
                i.setFitHeight(150);
                i.setFitWidth(250);

                l.setGraphic(i);
                listevent.getItems().add(l);
                listevent.setStyle("-fx-background-color: #B7D7F2");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        List<Evenement> me = s.selectMesEvenement(Session.getIdThisUser());
        for (Evenement ev : me) {
            try {

                Label l = new Label(ev.getNom());
                l.setFont(Font.font("DK Cool Crayon", 30));
                ImageView i = new ImageView(new Image(new FileInputStream(ev.getPhoto())));
                i.setFitHeight(150);
                i.setFitWidth(250);

                l.setGraphic(i);
                myevent.getItems().add(l);
                myevent.setStyle("-fx-background-color: #B7D7F2");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        /**
         * **********Fin Mes Evenement**********
         */
        detail.setVisible(false);
        modifay.setVisible(false);

    }

    @FXML
    private void afficherImage(MouseEvent me) throws FileNotFoundException {
        detail.setVisible(true);
        String l = listevent.getSelectionModel().getSelectedItem().getText();
        event = s.getIdByName(l);
        altud = event.getLatitude();
        longe = event.getLongitude();
        event = s.getIdByName(l);
        titre.setText(l);
        description.setText("Lieu: " + event.getLieu() + "\n" + "Date: " + event.getDate().toString() + "\n" + "Type: " + event.getType());
        Image image = new Image(new FileInputStream(event.getPhoto()));
        img.setImage(image);

    }

    @FXML
    private void inscription(ActionEvent ev) {
        String l = listevent.getSelectionModel().getSelectedItem().getText();
        event = s.getIdByName(l);
        Participevenement p = new Participevenement(event.getId_evenement(), Session.getIdThisUser(), 6);
        ServiceParticipevenement sp = new ServiceParticipevenement();
        sp.insrerParticipevenement(p);

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

        save(img, name, p);
    }

    public void save(Image image, String name, String p) throws IOException {
        if (p.indexOf(".png") != -1) {
            File fileoutput = new File("src/icons/" + name + ".png");
            BufferedImage BI = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(BI, "png", fileoutput);
            imgg = "src/icons/" + name + ".png";
        } else {
            File fileoutput = new File("src/icons/" + name + ".jpeg");
            BufferedImage BI = SwingFXUtils.fromFXImage(image, null);

            ImageIO.write(BI, "jpeg", fileoutput);
            imgg = "src/icons/" + name + ".png";
        }
    }

    @FXML
    private void UpdateEvenement(ActionEvent even) throws IOException, SQLException {

        String l = myevent.getSelectionModel().getSelectedItem().getText();

        if (this.controleSaisie(l)) {
            event = s.getIdByName(l);
            int id = event.getId_evenement();
            LocalDate d = mdate.getValue();
            Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (imgg == "") {
                imgg = event.getPhoto();
            }
            Evenement e = new Evenement(mnom.getText(), mlieu.getText(), date,
                    mtype.getValue(), Integer.parseInt(mnb.getText()),
                    event.isEtat(), Session.getIdThisUser(), imgg, altud, longe);
            s.updateEvenement(e, id);
        }
    }

    @FXML
    private void deleteEvenement(ActionEvent even) {

        String l = myevent.getSelectionModel().getSelectedItem().getText();
        event = s.getIdByName(l);
        int id = event.getId_evenement();
        s.deleteEvenement(id);
        int a = myevent.getSelectionModel().getSelectedIndex();
        myevent.getItems().remove(a);
        modifay.setVisible(false);

    }

    @FXML
    private void affichemodifay(MouseEvent even) throws FileNotFoundException {
        modifay.setVisible(true);
    
       
        String l = myevent.getSelectionModel().getSelectedItem().getText();
        event = s.getIdByName(l);
     altud = event.getLatitude();
     longe = event.getLongitude();
        mnom.setText(event.getNom());
        mlieu.setText(event.getLieu());
        Instant instant = Instant.ofEpochMilli(event.getDate().getTime());
        LocalDate datt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .toLocalDate();
        mdate.setValue(datt);
        mtype.setValue(event.getType());
        if (event.isEtat() == false) {
            metat.setText("not full");
        }
        Image image = new Image(new FileInputStream(event.getPhoto()));

        mnb.setText(String.valueOf(event.getNbr_participation()));
        mimg.setImage(image);

    }

    public boolean controleSaisie(String nom) throws IOException, SQLException {
        boolean saisie = true;
        ServiceEvenement es = new ServiceEvenement();

        if (!Validation.textalphabet(mnom, enom, "* le nom de doit contenir que des lettre")) {
            saisie = false;
        }

        if (!Validation.texAlphNum(mlieu, nlieu, "* le prenom de doit contenir que des lettre")) {
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

}
