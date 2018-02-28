/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Movie;
import allforkids.entites.Session;
import allforkids.service.ServiceImage;
import allforkids.service.ServiceMovie;
import allforkids.util.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class AddmovieController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXButton ajouterimg;
    @FXML
    private JFXButton ajouterfich;
    @FXML
    private Label nnom;
    @FXML
    private Label nimg;
    @FXML
    private Label nfich;
    @FXML
    private JFXButton ajout;

    private static String urlmp4 = "";
    public String fiilePath;
    private static final int BUFFER_SIZE = 4096;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Imagadd(ActionEvent event) {
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
    private void addfile(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP4", "*.mp4"));
        File selectedFile = fil.showOpenDialog(stage);

        String p = selectedFile.getPath();
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = Session.getIp();
        String user = "slim";
        String pass = "07471917";

        String uploadPath = "/movie/" + nom.getText() + ".mp4";

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);

        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(p);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File uploaded");
            urlmp4 = "/movie/" + nom.getText() + ".mp4";

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Enregistre(ActionEvent event) throws IOException, SQLException {
        if (controleSaisie()) {
            Movie m = new Movie(nom.getText(), urlmp4, ServiceImage.getImgg());
            ServiceMovie s = new ServiceMovie();
            s.insrerMovie(m);
            Alert2.afficherSuccses("Succses", "Movie  Ajouter avec succses");
        }
    }

    public boolean controleSaisie() throws IOException, SQLException {
        boolean saisie = true;
        ServiceMovie s = new ServiceMovie();
        if (!Validation.texAlphNum(nom, nnom, "* le nom  doit contenir que des lettre")) {
            saisie = false;
        }
        Movie m = s.getIdByName(nom.getText());
        if (!(m.getNom() == null)) {

            nnom.setText("le Nom Existe deja !");
            saisie = false;
        }
        if (ServiceImage.getImgg().equals("")) {
            nimg.setText("*vous devez ajouter une image");
            saisie = false;
        }
        if (urlmp4.equals("")) {
            nfich.setText("*vous devez ajouter le Fichier ");
            saisie = false;
        }
        return saisie;

    }
}
