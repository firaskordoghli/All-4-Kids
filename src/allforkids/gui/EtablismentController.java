/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Etablissement;
import allforkids.entites.Note;
import allforkids.entites.Rejoindre;
import allforkids.entites.Session;
import allforkids.entites.User;
import allforkids.service.ServiceEtablissement;
import allforkids.service.ServiceNote;
import allforkids.service.ServiceRejoindre;
import allforkids.service.ServiceUser;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer.Record;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.swing.text.html.HTML;

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
    private TableColumn<Etablissement, String> nomCol2;
    private TableColumn<Etablissement, String> typeCol2;
    private TableView<Etablissement> tableview2;
    private ImageView viewimage;
    private JFXButton modifier;
    private JFXButton delete;
    private JFXTextField nom;
    private AnchorPane detail2;
    private JFXComboBox<String> type2;
    private JFXComboBox<String> region2;
    private JFXComboBox<String> ville2;

    private JFXTextField description2;
    @FXML
    private JFXButton ajouter;
    private static String src = "";
    @FXML
    private ImageView imageview1;
    private TableColumn<Etablissement, String> etatCol2;
    private AnchorPane detail3;
    private TableView<User> tableview4;
    private TableColumn<User, String> nomeleve;
    private TableColumn<User, String> prenomeleve;
    private TableView<Etablissement> tableview3;
    private TableColumn<Etablissement, String> nomCol3;
    private TableColumn<Etablissement, String> typeCol3;
    private JFXListView<String> listview;
    private AnchorPane detail5;
    private JFXButton accepter;
    private AnchorPane detail6;
    private TableView<User> tableview7;
    private TableColumn<User, String> nomeleve1;
    private TableColumn<User, String> prenomeleve1;
    private TableView<Etablissement> tableview6;
    private TableColumn<Etablissement, String> nomCol31;
    private TableColumn<Etablissement, String> typeCol31;
    private JFXTextField moyenne;
    private AnchorPane detail7;
    private JFXButton pdf;
    @FXML
    private PieChart pourcentage;
    @FXML
    private TableView<Etablissement> tableview8;
    @FXML
    private TableColumn<Etablissement, String> nomCol311;
    @FXML
    private TableColumn<Etablissement, String> typeCol311;
    @FXML
    private AnchorPane detail8;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ////////////////////liste etablissement

        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ServiceEtablissement sr1 = new ServiceEtablissement();
        try {
            tableview.setItems(sr1.selectEtablissement1());

        } catch (Exception ex) {
            System.out.println(ex);
        }
        //////////////////////Ma liste etablissements
        modifier.setVisible(false);
        delete.setVisible(false);
        nomCol2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol2.setCellValueFactory(new PropertyValueFactory<>("type"));
        etatCol2.setCellValueFactory(new PropertyValueFactory<>("verification"));

        ServiceEtablissement sr2 = new ServiceEtablissement();

        try {
            tableview2.setItems(sr2.selectEtablissementById(32));

        } catch (Exception ex) {
            System.out.println(ex);
        }
        /////////////////////liste eleve par etablissement
        nomCol3.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol3.setCellValueFactory(new PropertyValueFactory<>("type"));

        ServiceEtablissement sr3 = new ServiceEtablissement();

        //User u1 = new User();
        try {
            tableview3.setItems(sr3.selectEtablissementById2(32));

        } catch (Exception ex) {
            System.out.println(ex);
        }
        /////////////////////////acceptation des éleves
        ServiceRejoindre sr9 = new ServiceRejoindre();
        ServiceUser su = new ServiceUser();
        try {

            List<Etablissement> list1 = sr3.selectEtablissementById(32);
            for (Etablissement etablissement : list1) {
                List<Rejoindre> r = sr9.selectIdUserById(etablissement.getId());
                String a = etablissement.getNom();
                int b = etablissement.getId();

                for (Rejoindre re : r) {

                    List<User> u = su.GetUserById3(re.getId_user());
                    for (User user : u) {
                        listview.getItems().add("L'éleve : "
                                + user.getNom()
                                + " "
                                + user.getPrenom()
                                + " veut rejoindre l'établissement : " + a);
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        /////////////////////////Ajout note
        nomCol31.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol31.setCellValueFactory(new PropertyValueFactory<>("type"));

        try {
            tableview6.setItems(sr3.selectEtablissementById2(32));

        } catch (Exception ex) {
            System.out.println(ex);
        }
        ////////////////////statistique
        nomCol311.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol311.setCellValueFactory(new PropertyValueFactory<>("type"));

        ServiceEtablissement sr5 = new ServiceEtablissement();

        //User u1 = new User();
        try {
            tableview8.setItems(sr5.selectEtablissementById2(32));

        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        

        //Session.getIdThisUser();
        detail.setVisible(false);
        detail2.setVisible(false);
        detail3.setVisible(false);
        detail5.setVisible(false);
        detail6.setVisible(false);
        detail7.setVisible(false);
        detail8.setVisible(false);

        pdf.setVisible(false);

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
            System.out.println(ex);
        }
    }

    private void consulter2(MouseEvent event) {
        detail2.setVisible(true);
        modifier.setVisible(true);
        delete.setVisible(true);
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
        type2.setValue(e.getType());
        region2.setValue(e.getRegion());
        ville2.setValue(e.getVille());
        description2.setText(e.getDescription());
        Image img;
        try {
            img = new Image(new FileInputStream(e.getImage()));
            viewimage.setImage(img);
        } catch (Exception ex) {
            System.out.println("erreur");
        }

    }

    private void modifer(ActionEvent event) {
        ServiceEtablissement sr1 = new ServiceEtablissement();
        Etablissement e = sr1.GetEtablissemebtById(tableview2.getSelectionModel().getSelectedItem().getId());
        int id = e.getId();
        System.out.println(e);
        if (src.equals("")) {
            src = e.getImage();
        }
        Etablissement et = new Etablissement(nom.getText(),
                type2.getValue(),
                region2.getValue(),
                ville2.getValue(),
                description2.getText(),
                src
        );
        sr1.updateEtablissement(et, id);
        /*nom.setText("");
        description2.setText("");
        type2.setValue("");
        region2.setValue("");
        ville2.setValue("");*/

    }

    private void DeleteEtablissement(ActionEvent event) {
        ServiceEtablissement sr1 = new ServiceEtablissement();
        int a = tableview2.getSelectionModel().getSelectedIndex();
        sr1.deleteEtablissement(tableview2.getSelectionModel().getSelectedItem().getId());
        tableview2.getItems().remove(a);
        detail2.setVisible(false);
    }

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

    private void consulter3(MouseEvent event) {
        detail3.setVisible(true);
        detail5.setVisible(false);
        pdf.setVisible(true);
        int a = tableview3.getSelectionModel().getSelectedItem().getId();
        //ServiceRejoindre sr1 = new ServiceRejoindre();
        ServiceUser su = new ServiceUser();
        //Rejoindre r = sr1.GetIdUserById(a);

        //User u = su.GetUserById(r.getId_user());
        // u.getNom();
        //u.getPrenom();
        nomeleve.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomeleve.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        try {
            tableview4.setItems(su.GetUserById2(a));
            // tableview3.setItems(sr3.selectEtablissementById(32));

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void consulter4(MouseEvent event) {
        detail5.setVisible(true);
        accepter.setVisible(true);
        pdf.setVisible(false);
        ServiceRejoindre sr1 = new ServiceRejoindre();

        if (sr1.SelectIfDejaExiste(tableview3.getSelectionModel().getSelectedItem().getId(),
                tableview4.getSelectionModel().getSelectedItem().getId())
                .getVerification().equals("Valide")) {
            accepter.setVisible(false);
        }

    }

    private void DeleteEleve(ActionEvent event) {
        ServiceRejoindre sr1 = new ServiceRejoindre();
        int a = tableview4.getSelectionModel().getSelectedIndex();
        sr1.deleteEleve(tableview3.getSelectionModel().getSelectedItem().getId(), tableview4.getSelectionModel().getSelectedItem().getId());
        tableview4.getItems().remove(a);
        detail5.setVisible(false);
    }

    private void AccepterEleve(ActionEvent event) {
        ServiceRejoindre sr1 = new ServiceRejoindre();

        sr1.accepterEleve(tableview3.getSelectionModel().getSelectedItem().getId(), tableview4.getSelectionModel().getSelectedItem().getId());

    }

    private void consulter6(MouseEvent event) {
        detail6.setVisible(true);
        int a = tableview6.getSelectionModel().getSelectedItem().getId();
        //ServiceRejoindre sr1 = new ServiceRejoindre();
        ServiceUser su = new ServiceUser();
        //Rejoindre r = sr1.GetIdUserById(a);

        //User u = su.GetUserById(r.getId_user());
        // u.getNom();
        //u.getPrenom();
        nomeleve1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomeleve1.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        try {
            tableview7.setItems(su.GetUserById2(a));
            // tableview3.setItems(sr3.selectEtablissementById(32));

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void consulter7(MouseEvent event) {
        ServiceNote sn = new ServiceNote();
        if (sn.SelectMoyenneById(tableview7.getSelectionModel().getSelectedItem().getId()) == null) {
            detail7.setVisible(true);
        } else {
            detail7.setVisible(false);
            Alert.afficher("", "vous avez déja saisi la moyenne de cette éleve");
        }

    }

    private void insertmoyenne(ActionEvent event) {
        ServiceNote sn = new ServiceNote();
        Note n = new Note(tableview7.getSelectionModel().getSelectedItem().getId(),
                 Float.parseFloat(moyenne.getText()));

        sn.insrerMoyenne(n);
        moyenne.clear();
        detail7.setVisible(false);

    }

    private void pdf(ActionEvent event) throws DocumentException {

        String nometablissement = tableview3.getSelectionModel().getSelectedItem().getNom();
        Document document = new Document(PageSize.A4);

        //document.setMargins(50,50,50,50);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\MBM info\\Desktop\\ListeEleve" + nometablissement + ".pdf"));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        document.open();

        String titre = "                                                      La liste des éléves de l'établissement " + nometablissement;
        Paragraph para1 = new Paragraph();
        para1.add(titre);
        para1.setSpacingAfter(50);

        document.add(para1);
        for (User u : tableview4.getItems()) {
            String nom = u.getNom();
            String prenom = u.getPrenom();

            String n = ("Nom et prenom éleve : " + nom + " " + prenom + "\n");

            Paragraph para = new Paragraph();

            //para.setSpacingBefore(50);
            para.add(n);

            document.add(para);
        }

        document.close();

        Alert.afficher("", "Votre PDF a été crée avec succès");
    }

    private PieChart createPieChart() {
        int et = tableview8.getSelectionModel().getSelectedItem().getId();
        ServiceNote sn = new ServiceNote();
        
        ObservableList<PieChart.Data> donner = FXCollections.observableArrayList(
             new PieChart.Data("Note suppérieur à 10", sn.statistiquesupp(et)),
             new PieChart.Data("Note inférieur à 10", sn.statistiqueinf(et))
        );
        
    return new PieChart(donner);
    }

    @FXML
    private void consulter8(MouseEvent event) {
        int a = tableview8.getSelectionModel().getSelectedItem().getId();
        ServiceNote sn =new ServiceNote();
        if(sn.verification(a)==0){
            Alert.afficher("Note non saisi pour cette établissement","Impossible d'afficher les satatistique");
        }
        else{
            detail8.setVisible(true);
        
        
        
        pourcentage.setData(createPieChart().getData());
        pourcentage.setLabelsVisible(true);
        }
        
        
    }
    

}
