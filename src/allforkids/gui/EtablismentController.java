/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.entites.Session;
import static allforkids.gui.ListEtablissementController.nb;
import allforkids.service.ServiceEtablissement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author FATNASSI
 */
public class EtablismentController implements Initializable {

    @FXML
    private TableView<Etablissement> tableview;
    @FXML
    private TableColumn<Etablissement, String> nomCol;
    @FXML
    private TableColumn<Etablissement, String> typeCol;
    @FXML
    private AnchorPane detail;
    private ImageView image;
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
    private TableColumn<Etablissement, String> nomCol2;
    @FXML
    private TableColumn<Etablissement, String> typeCol2;
    @FXML
    private TableView<Etablissement> tableview2;
    @FXML
    private ImageView viewimage;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXButton boutonimage;
    @FXML
    private AnchorPane detail2;
    @FXML
    private JFXComboBox<String> type2;
    @FXML
    private JFXComboBox<String> region2;
    @FXML
    private JFXComboBox<String> ville2;

    
    @FXML
    private JFXTextField description2;
    @FXML
    private JFXButton ajouter;
    private static String src="" ;
    @FXML
    private ImageView imageview1;
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

        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        nomCol2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol2.setCellValueFactory(new PropertyValueFactory<>("type"));
        ServiceEtablissement sr2 = new ServiceEtablissement();
        try {
            tableview2.setItems(sr2.selectEtablissementById(32));

        } catch (SQLException ex) {
            Logger.getLogger(allforkids.gui.AllForKids.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Session.getIdThisUser();
        detail.setVisible(false);
        detail2.setVisible(false);
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
            System.out.println(ex); }
    }

    @FXML
    private void consulter2(MouseEvent event) {
        detail2.setVisible(true);
        type2.getItems().addAll(
                "Garderie",
                "jardin d'enfant",
                "ecole primaire",
                "colége",
                "lycée"
        );
        type2.setPromptText("Type établissement");

        region2.getItems().addAll(
                "Tunis",
                "Kairoun",
                "Nabeul",
                "Bizerte",
                "Sousse"
        );
        region2.setPromptText("Region établissement");

        ville2.getItems().addAll(
                "Centre ville",
                "Ariana",
                "Hay Tahrire",
                "Bardo",
                "Manar"
        );
        ville2.setPromptText("Ville établissement");

        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(tableview2.getSelectionModel().getSelectedItem().getId());
        System.out.println(e);
        nom.setText(e.getNom());
//        System.out.println(e.getType());
//        System.out.println(e.getRegion());
        type2.setValue(e.getType());
        region2.setValue(e.getRegion());
        ville2.setValue(e.getVille());
        description2.setText(e.getDescription());
        Image img;
        try {
            img = new Image(new FileInputStream(e.getImage()));

            viewimage.setImage(img);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DétailEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void modifer(ActionEvent event) {
        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(tableview2.getSelectionModel().getSelectedItem().getId());
        int id = e.getId();
        System.out.println(e);
        if (src.equals("")){
            src=e.getImage();
        }
        Etablissement et = new Etablissement(nom.getText(),
                type2.getValue(),
                region2.getValue(),
                ville2.getValue(),
                description2.getText(),
               src );
        sr1.updateEtablissement(et, id);
        nom.setText("");
        description2.setText("");
        type2.setValue("");
        region2.setValue("");
        ville2.setValue("");
        
    }

    @FXML
    private void DeleteEtablissement(ActionEvent event) {
        ServiceEtablissement sr1 = new ServiceEtablissement();
        int a = tableview2.getSelectionModel().getSelectedIndex();
        sr1.deleteEtablissement(tableview2.getSelectionModel().getSelectedItem().getId());
        tableview2.getItems().remove(a);
        detail2.setVisible(false);
    }

    @FXML
    private void updateimage(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"));
        File selectedFile = fil.showOpenDialog(stage);
        Image boutonimage = new Image(selectedFile.toURI().toString());
        String p = selectedFile.getPath();
        int id = ServiceEtablissement.GetLastId();
        String name = nom.getText();

        save(boutonimage, id, name, p);

    }

    public void save(Image image, int id, String name, String p) throws IOException {
        if (p.indexOf(".png") != -1) {
            File fileoutput = new File("src/icons/" + id + name + ".png");
            BufferedImage BI = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(BI, "png", fileoutput);
            src = "src/icons/" + id + name + ".png";
        } else {
            File fileoutput = new File("src/icons/" + id + name + ".jpeg");
            BufferedImage BI = SwingFXUtils.fromFXImage(image, null);

            ImageIO.write(BI, "jpeg", fileoutput);
            src = "src/icons/" + id + name + ".png";
        }
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
