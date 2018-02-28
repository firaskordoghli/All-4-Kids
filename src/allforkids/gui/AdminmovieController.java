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
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class AdminmovieController implements Initializable {

    @FXML
    private JFXListView<Label> listmovie;
    @FXML
    private JFXTextField nom;
    @FXML
    private Label nimg;
    @FXML
    private Label nficher;
    @FXML
    private JFXButton modif;
    @FXML
    private JFXButton delete;
    ServiceMovie sm = new ServiceMovie();
    Movie mv ;
    @FXML
    private AnchorPane detail;
    @FXML
    private JFXButton imgge;
    @FXML
    private JFXButton ficherr;
        private static String urlmp4 = "";
    public String fiilePath;
    private static final int BUFFER_SIZE = 4096;
    @FXML
    private Label nnom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         affichermovie();
         detail.setVisible(false);
    }    

    @FXML
    private void afichmodifier(MouseEvent event) {
         detail.setVisible(true);
         String m = listmovie.getSelectionModel().getSelectedItem().getText();
         mv = sm.getIdByName(m);
         nom.setText(m);
     
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
         String m = listmovie.getSelectionModel().getSelectedItem().getText();
         mv = sm.getIdByName(m);
         int id = mv.getId_movie();
         if (this.controleSaisie(m)) {
        if (ServiceImage.getImgg().equals("")) {
                ServiceImage.setImgg(mv.getImg());
            }
        if (urlmp4.equals("")) {
               urlmp4 = mv.getUrl();
            }
            
          Movie me = new Movie(nom.getText(), urlmp4, ServiceImage.getImgg());
        
             sm.updateMovie(me, id);
               Alert2.afficherSuccses("Succes", "Votre film à été modifier avec succses");
           detail.setVisible(false);
           listmovie.getItems().clear();
           affichermovie();
         }
    }

    @FXML
    private void Supprime(ActionEvent event) {
          String l = listmovie.getSelectionModel().getSelectedItem().getText();
        Movie m = sm.getIdByName(l);
           javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("voulez-vous vraiment supprimer " + l + " ?");
        alert.showAndWait();

     if (alert.getResult() == ButtonType.YES) {
         sm.deleteMovie(m.getId_movie());
         listmovie.getItems().clear();
           affichermovie();
     }
     }
        
   

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
            Stage stage = new Stage();
        stage.setTitle("Ajouter Filme");
        Parent root = FXMLLoader.load(getClass().getResource("Addmovie.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void affichermovie(){
      List<Movie> m = new ArrayList<>();
        m = sm.selectMovie();

        for (Movie mv : m) {

            Label la = new Label(mv.getNom());
            la.setFont(Font.font("DK Cool Crayon", 30));
            ImageView i = new ImageView();
            i.setImage(new Image("ftp://slim:07471917@" + Session.getIp() + "/" + mv.getImg()));
            System.out.println(mv.getImg());
            i.setFitHeight(90);
            i.setFitWidth(90);

            la.setGraphic(i);
            listmovie.getItems().add(la);
            listmovie.setStyle("-fx-background-color: #B7D7F2");

        }
    }

    @FXML
    private void ajouterimg(ActionEvent event) {
         Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"));
        File selectedFile = fil.showOpenDialog(stage);
        Image img = new Image(selectedFile.toURI().toString());
        String p = selectedFile.getPath();

        String name = nom.getText();

       ServiceImage.saveimg(img, name, p,selectedFile);
    }

    @FXML
    private void addfaile(ActionEvent event) {
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
      public boolean controleSaisie(String name) throws IOException, SQLException {
      boolean saisie = true;
        ServiceMovie s = new ServiceMovie();
       if (!Validation.texAlphNum(nom, nnom, "* nom doit contenir que des lettre")) {
            saisie = false;
        }
        Movie m = s.getIdByName(nom.getText());
         if (!((m.getNom() == null)||(m.getNom().equals(name)))) {

            nnom.setText("le Nom Existe deja !");
            saisie = false;
        }
         
       return saisie ;
    
    }
}
