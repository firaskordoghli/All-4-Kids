/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Livre;
import allforkids.entites.Session;
import allforkids.service.ServiceImage;
import allforkids.service.ServiceLivre;
import allforkids.util.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
public class AdminlaiberiController implements Initializable {

    @FXML
    private JFXListView<Label> listliver;
    @FXML
    private AnchorPane detail;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXButton photo;
    @FXML
    private JFXButton ficher;
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
    private JFXComboBox<String> categorie;
    @FXML
    private JFXComboBox<String> typee;
    @FXML
    private JFXButton mdif;
    @FXML
    private JFXButton supprim;
  private ServiceLivre sl = new ServiceLivre() ;
  private Livre livre ;
    private static String urlpdf = "";
    public String fiilePath;
    private static final int BUFFER_SIZE = 4096;
    @FXML
    private JFXButton addlivre;
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
         afficher();
        detail.setVisible(false);
    }    

    @FXML
    private void aficherdetail(MouseEvent event) {
         detail.setVisible(true);
         String s = listliver.getSelectionModel().getSelectedItem().getText();
         livre = sl.getIdByName(s);
         nom.setText(s);
         description.setText(livre.getDescription());
         categorie.setValue(livre.getCategorie());
         typee.setValue(livre.getType());
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
    private void modifier(ActionEvent event) throws IOException, SQLException {
        String s = listliver.getSelectionModel().getSelectedItem().getText();
         livre = sl.getIdByName(s);
           if (this.controleSaisie(s)) {
             
                  if (ServiceImage.getImgg().equals("")) {
                ServiceImage.setImgg(livre.getPhoto());
            }
        if (urlpdf.equals("")) {
               urlpdf = livre.getUrl();
            }
        Livre lv = new Livre(nom.getText(), categorie.getValue(), description.getText(), typee.getValue(), 
                livre.getGood(), livre.getBad(),ServiceImage.getImgg() , urlpdf);
            sl.updateLivre(lv,livre.getId_livre() );
              Alert2.afficherSuccses("Succes", "Votre livre à été modifier avec succses");
                detail.setVisible(false);
                listliver.getItems().clear();
                afficher();
           }
         
    }

    @FXML
    private void delete(ActionEvent event) {
           String s = listliver.getSelectionModel().getSelectedItem().getText();
            livre = sl.getIdByName(s);
            javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("voulez-vous vraiment supprimer " + s+ " ?");
        alert.showAndWait();

     if (alert.getResult() == ButtonType.YES) {
         sl.deleteLivre(livre.getId_livre());
          detail.setVisible(false);
                listliver.getItems().clear();
                afficher();
    }
    }
         
    private void afficher(){
     List<Livre> l = new ArrayList<>();
        // int u = Session.getIdThisUser();

        l = sl.selectLivre();

        for (Livre lv : l) {

            Label la = new Label(lv.getNom());
            la.setFont(Font.font("DK Cool Crayon", 30));
            ImageView i = new ImageView();
            i.setImage(new Image("ftp://slim:07471917@" + Session.getIp() + "/" + lv.getPhoto()));

            i.setFitHeight(90);
            i.setFitWidth(90);

            la.setGraphic(i);
            listliver.getItems().add(la);
            listliver.setStyle("-fx-background-color: #B7D7F2");

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

    public boolean controleSaisie( String name) throws IOException, SQLException {
        boolean saisie = true;
       ServiceLivre sl = new ServiceLivre();

        if (!Validation.texAlphNum(nom, nnom, "* le nom  doit contenir que des lettre")) {
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
        if (!((l.getNom() == null)||(name.equals(l.getNom())))) {

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

       

        return saisie;
    }

    @FXML
    private void ajouterlivre(ActionEvent event) throws IOException {
                Stage stage = new Stage();
        stage.setTitle("Ajouter Filme");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("AjouterLivre.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
