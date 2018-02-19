/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Session;
import allforkids.entites.User;
import allforkids.service.ServiceUser;
import allforkids.util.BCrypt;
import allforkids.util.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class CreationCompteController implements Initializable {

    @FXML
    private JFXTextField NomC;
    @FXML
    private JFXTextField PrenomC;
    @FXML
    private JFXTextField MailC;
    @FXML
    private JFXPasswordField PassCC;
    @FXML
    private JFXTextField CinC;
    @FXML
    private JFXPasswordField PassC;
    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label Mail;
    @FXML
    private Label Photo;
    @FXML
    private Label Datee;
    @FXML
    private Label Cin;
    @FXML
    private Label Passc;
    @FXML
    private Label Pass;
    @FXML
    private JFXDatePicker DateC;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXButton vider;

    @FXML
    private ChoiceBox<String> RoleC;
    @FXML
    private JFXButton photoC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<String> list = new ArrayList();
        list.add("Enfant");
        list.add("Parent");
        list.add("Eatblissement");

        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        RoleC.setItems(ob);
        // TODO
    }

    public boolean controleSaisie() throws IOException, SQLException {
        boolean saisie = true;
        ServiceUser us = new ServiceUser();
        String z;
        int r = 0;
        z = RoleC.getValue();
        if (z == "Enfant") {
            r = 1;
        } else if (z == "Parent") {
            r = 2;
        } else if (z == "Eatblissement") {
            r = 3;
        }

        if (!Validation.textalphabet(NomC, Nom, "* le nom de doit contenir que des lettre")) {
            saisie = false;
        }

        if (!Validation.textalphabet(PrenomC, Prenom, "* le prenom de doit contenir que des lettre")) {
            saisie = false;
        }
        if (!Validation.texMail(MailC, Mail, "* verifier votre mail")) {
            saisie = false;
        }

        if (!Validation.textValidation(NomC, Nom, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(PrenomC, Prenom, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(MailC, Mail, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if ((r == 3) || (r == 2)) {
            if (!Validation.textValidation(CinC, Cin, "* tout les champs doivent etre remplis")) {
                saisie = false;
                if (!Validation.texNum(CinC, Cin, "* le nom de doit contenir que des numero")) {
                    saisie = false;
                }
            }
        }

        if (!Validation.textValidation(PassC, Pass, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(PassCC, Passc, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        /*   if (!us.uniqueUserName(usernameTF.getText())) {
            usernameL.setText("* le username est déjà utilisé");
            saisie = false;
        }*/
        if (!PassC.getText().equals(PassCC.getText())) {
            Passc.setText("* vous devez confirmer le mot de passe");
            saisie = false;
        }
        if (Validation.texMail(MailC, Mail, "* la forme de mail est invalide")) {

            /*String validationString = null;
            if (!mailTF.getText().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$")) {
                saisie = false;

            }

            mailL.setText("* la forme de mail est invalide");
            System.out.println(saisie);*/
            return saisie;
        }
        return saisie;

    }
   /* public void photo(ActionEvent e,Image image, String name) throws IOException{
        Stage stage = new Stage();
       FileChooser fil = new FileChooser();
       fil.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("PNG","*.png"), new FileChooser.ExtensionFilter("JPEG","*.jpeg") );
       File selectedFile = fil.showOpenDialog(stage);
       Image img =new Image(selectedFile.toURI().toString());
    File fileoutput = new File("C:\\Users\\casa-net\\Documents\\NetBeansProjects\\All-4-Kids\\src\\allforkids\\icons");
        BufferedImage BI = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(BI, "png", fileoutput);
    }
    
    public void save (Image image, String name) throws IOException{
    File fileoutput = new File("C:\\Users\\casa-net\\Documents\\NetBeansProjects\\All-4-Kids\\src\\allforkids\\icons");
        BufferedImage BI = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(BI, "png", fileoutput);
    }*/
    
    
    @FXML
    private void valider(ActionEvent event) throws IOException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Validation v = new Validation();

        ServiceUser a = new ServiceUser();
        if (this.controleSaisie()) {

            LocalDate d = DateC.getValue();
            Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            
            
            
          
             
             
             
             
             
             
             
             
             String z;
            int r = 0;
            z = RoleC.getValue();
            if (z == "Enfant") {
                r = 1;
            } else if (z == "Parent") {
                r = 2;
            } else if (z == "Eatblissement") {
                r = 3;
            }
            String s = PassC.getText();
            String pw_hash = BCrypt.hashpw(s, BCrypt.gensalt());

            User u = new User(CinC.getText(), NomC.getText(), PrenomC.getText(), MailC.getText(), date, "1", r, pw_hash);
            a.insrerUser(u);

            /*   Notifications notificiationBuilder = Notifications.create()
                        .title("créer")
                        .text("votre compte a été créer !")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.BOTTOM_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Missing!");
                            }
                        });
                notificiationBuilder.darkStyle();
                notificiationBuilder.showConfirm();
               //Login(event);*/
         
            Parent root = FXMLLoader.load(getClass().getResource("CreationCompte.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();

        }

    }

    @FXML
    private void vider(ActionEvent event) {
    }

    @FXML
    private void photoo(ActionEvent event) throws IOException {
        
         Stage stage = new Stage();
       FileChooser fil = new FileChooser();
       fil.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("PNG","*.png"), new FileChooser.ExtensionFilter("JPEG","*.jpeg") );
       File selectedFile = fil.showOpenDialog(stage);
       Image img =new Image(selectedFile.toURI().toString());
        int name = Session.getIdThisUser();
        save(img , name);
        
    }
    
    public void save (Image image,int name) throws IOException{
       
    File fileoutput = new File("C:/Users/casa-net/Documents/NetBeansProjects/All-4-Kids/src/allforkids/icons/"+name+".png");
        BufferedImage BI = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(BI, "png", fileoutput);
    }

   
    

   
   

   

}
