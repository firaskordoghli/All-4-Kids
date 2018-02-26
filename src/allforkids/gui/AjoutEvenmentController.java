/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Session;
import allforkids.service.ServiceEvenement;
import allforkids.util.Validation;
import com.jfoenix.controls.JFXButton;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeArray.map;


/**
 * FXML Controller class
 *
 * @author slim
 */
public class AjoutEvenmentController implements Initializable, MapComponentInitializedListener {

    @FXML
    private TextField tflieu;
    @FXML
    private TextField tfNom;
    @FXML
    private DatePicker tfDate;
    @FXML
    private ChoiceBox<String> tftype;
    @FXML
    private TextField tfnb;
    @FXML
    private JFXButton img;
    @FXML
    private Label Nom;
    @FXML
    private Label Lieu;
    @FXML
    private Label Nb;

    @FXML
    private Label Type;
    @FXML
    private Label Datee;
    @FXML
    private AnchorPane maps;
    @FXML
    private GoogleMapView mapView;
    GoogleMap map;
   private static Double longe  ;
   private static Double altud  ;
   private static String imgg="" ;
    @FXML
    private Label Imegee;
    public String filePath ;
    private static final int BUFFER_SIZE = 4096;
    @FXML
    private JFXTimePicker temp;
    @FXML
    private Label tempp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> list = new ArrayList();
        list.add("musique");
        list.add("cinema");
        list.add("theatre");
        list.add("randonnée");
        list.add("magicien");
        list.add("Parck");
        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        tftype.setItems(ob);

        mapView.addMapInializedListener(this);
        
    }

    @FXML
    private void saveEvenment(ActionEvent event) throws IOException, SQLException {
   
        if (this.controleSaisie()) {
                ServiceEvenement eService = new ServiceEvenement();
              
       
        LocalDate d = tfDate.getValue();
         Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
         
          Evenement e =new Evenement(tfNom.getText()
                 ,tflieu.getText(),date,tftype.getValue()
            ,Integer.parseInt(tfnb.getText()),false,8,imgg,altud,longe,java.sql.Time.valueOf(temp.getValue()));
          eService.insrerEvenement(e);
          
          Parent covViewOarent = FXMLLoader.load(getClass().getResource("Evenement.fxml"));
                Scene covViewScene = new Scene(covViewOarent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                window.setScene(covViewScene);
                window.show();
        }
    }

    @FXML
    private void AjoutImg(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"));
        File selectedFile = fil.showOpenDialog(stage);
        Image img = new Image(selectedFile.toURI().toString());
        String p = selectedFile.getPath();

        String name = tfNom.getText();

        saveimg(img, name, p,selectedFile);
    }

    
    public boolean controleSaisie() throws IOException, SQLException {
        boolean saisie = true;
        ServiceEvenement es = new ServiceEvenement();

        if (!Validation.textalphabet(tfNom, Nom, "* le nom de doit contenir que des lettre")) {
            saisie = false;
        }

        if (!Validation.texAlphNum(tflieu, Lieu, "* le prenom de doit contenir que des lettre")) {
            saisie = false;
        }
        if (!Validation.texNum(tfnb, Nb, "* Nb Doit contenir que des Nombre")) {
            saisie = false;
        }

        if (!Validation.textValidation(tfNom, Nom, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(tflieu, Lieu, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(tfnb, Nb, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        Evenement e = es.getIdByName(tfNom.getText());
        if (!(e.getNom() == null)) {

            Nom.setText("le Nom Existe deja !");
            saisie = false;
        }
        if ((tfDate.getValue() == null)) {
            Datee.setText("* tout les champs doivent etre remplis");
            saisie = false;
        }
        if (tftype.getValue() == null) {
            Type.setText("* tout les champs doivent etre remplis");
            saisie = false;
        }
        if (tftype.getValue() != null) {
            Type.setText("");

        }
        if (tfDate.getValue() != null) {
            LocalDate d = tfDate.getValue();
            Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date date1 = new Date();
            if (date.before(date1)) {
                Datee.setText("* La Date est Deja passe");
                saisie = false;
            } else {
                Datee.setText("");
            }
        }
        if(imgg.equals("")){
           Imegee.setText("*vous devez ajouter une image");
        }
        if(temp.getValue()== null){
        tempp.setText("*vous devez ajouter le temps");
        
        }
        return saisie;
    }

    @Override
    public void mapInitialized() {
    
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.862499, 10.195556))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        GoogleMap map = mapView.createMap(mapOptions);

        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {

            map.clearMarkers();

            LatLong latLong = event.getLatLong();
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(latLong).visible(Boolean.TRUE).title("fdshg");
            Marker marker = new Marker(markerOptions1);
            map.addMarker(marker);
             altud =latLong.getLatitude() ;
             longe  = latLong.getLongitude();
        });

    }
 public void saveimg(Image image , String name ,String p, File file)
 {        
       if (p.indexOf(".png") != -1) {
          filePath = file.getPath();
            System.out.println(filePath);
           
            String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
            String host = Session.getIp();
            String user = "slim";
            String pass = "07471917";
    
            String uploadPath = "/img/" +name+".png";
            
            ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
            System.out.println("Upload URL: " + ftpUrl);

            try {
                URL url = new URL(ftpUrl);
                URLConnection conn = url.openConnection();
                OutputStream outputStream = conn.getOutputStream();
                FileInputStream inputStream = new FileInputStream(filePath);

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();

                System.out.println("File uploaded");
                  imgg=  "/img/" + name + ".png";
               
            } catch (IOException ex) {
                ex.printStackTrace();
            }
       }else{
       
           filePath = file.getPath();
            System.out.println(filePath);
           
            String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
            String host = Session.getIp();
            String user = "slim";
            String pass = "07471917";
    
            String uploadPath = "/img/" +name+".jpeg";
            
            ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
            System.out.println("Upload URL: " + ftpUrl);

            try {
                URL url = new URL(ftpUrl);
                URLConnection conn = url.openConnection();
                OutputStream outputStream = conn.getOutputStream();
                FileInputStream inputStream = new FileInputStream(filePath);

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();

                System.out.println("File uploaded");
                  imgg=  "/img/" + name + ".jpeg";
           
            } catch (IOException ex) {
                ex.printStackTrace();
            }
       }
 
 }

}
