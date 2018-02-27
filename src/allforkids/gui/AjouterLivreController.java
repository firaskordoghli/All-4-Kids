/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Livre;
import allforkids.entites.Session;
import allforkids.service.ServiceEvenement;
import allforkids.service.ServiceImage;
import allforkids.service.ServiceLivre;
import allforkids.util.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class AjouterLivreController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXButton photo;
    @FXML
    private JFXButton ficher;
    @FXML
    private JFXComboBox<String> categorie;
    @FXML
    private JFXComboBox<String> typee;
    @FXML
    private Label ndescription;
    @FXML
    private Label nnom;
    @FXML
    private Label ntypee;
    @FXML
    private Label ncategorie;
    @FXML
    private Label nphoto;
    @FXML
    private Label nfichier;
    @FXML
    private JFXButton ajouterlivre;
   
    private static String urlpdf = "";
    public String fiilePath;
    private static final int BUFFER_SIZE = 4096;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typee.getItems().addAll(
                "educatif",
                "Histoire",
                "narratif",
                "théâtre",
                "professionnels"
        );
        typee.setPromptText("Type");
        categorie.getItems().addAll(
                "conte 2-5 ans",
                "conte 6-10 ans",
                "Cours",
                "parascolaire",
                "gied parental"
        );
        categorie.setPromptText("Categorie ");
    }

    @FXML
    private void Ajouterphoto(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"));
        File selectedFile = fil.showOpenDialog(stage);
        Image img = new Image(selectedFile.toURI().toString());
        String p = selectedFile.getPath();

        String name = nom.getText();

        ServiceImage.saveimg(img, name, p, selectedFile);
    }

    @FXML
    private void Ajouterficher(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File selectedFile = fil.showOpenDialog(stage);
        String name = nom.getText();
        String p = selectedFile.getPath();

        savePdf(selectedFile, name);
    }

    @FXML
    private void EnregistreLivre(ActionEvent event) throws IOException, SQLException {
        if (controleSaisie()) {
            Livre l = new Livre(nom.getText(), categorie.getValue(), description.getText(), typee.getValue(), 0, 0, ServiceImage.getImgg(), urlpdf);
            ServiceLivre sl = new ServiceLivre();
            sl.insrerLivre(l);
            Alert2.afficherSuccses("Succses", "Liver  Ajouter avec succses");
            Parent covViewOarent = FXMLLoader.load(getClass().getResource("Librairie.fxml"));
            Scene covViewScene = new Scene(covViewOarent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(covViewScene);
            window.show();
        }
    }


    public void savePdf(File file, String name) {
        fiilePath = file.getPath();
        System.out.println(fiilePath);

        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = Session.getIp();
        String user = "slim";
        String pass = "07471917";

        String uploadPath = "/Livre/" + name + ".pdf";

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);

        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(fiilePath);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File uploaded");
            urlpdf = "/Livre/" + name + ".pdf";

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public boolean controleSaisie() throws IOException, SQLException {
        boolean saisie = true;
        ServiceLivre sl = new ServiceLivre();

        if (!Validation.textalphabet(nom, nnom, "* le nom  doit contenir que des lettre")) {
            saisie = false;
        }

        if (!Validation.texAlphNum(description, ndescription, "* la  Description  doit contenir que des lettre")) {
            saisie = false;
        }

        if (!Validation.textValidation(nom, nnom, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(description, ndescription, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        Livre l = sl.getIdByName(nom.getText());
        if (!(l.getNom() == null)) {

            nnom.setText("le Nom Existe deja !");
            saisie = false;
        }

        if (typee.getValue() == null) {
            ntypee.setText("* tout les champs doivent etre remplis");
            saisie = false;
        }
        if (typee.getValue() != null) {
            ntypee.setText("");

        }
        if (categorie.getValue() == null) {
            ncategorie.setText("* tout les champs doivent etre remplis");
            saisie = false;
        }
        if (categorie.getValue() != null) {
            ncategorie.setText("");

        }

        if (ServiceImage.getImgg().equals("")) {
            nphoto.setText("*vous devez ajouter une image");
            saisie = false;
        }
        if (urlpdf.equals("")) {
            nfichier.setText("*vous devez ajouter le Fichier ");
            saisie = false;
        }

        return saisie;
    }

}
