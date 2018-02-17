/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.service.ServiceEvenement;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author slim
 */

public class ConsulteEvenmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
     
     private ListView<String> list;
     private Evenement event;
     private ServiceEvenement s = new ServiceEvenement();
    @FXML
    private ListView<Evenement> lisEvents;
    @FXML
    private ImageView eventIMG;
    @FXML
    private AnchorPane detail;
    @FXML
    private Label titre;
    @FXML
    private Label date;
    @FXML
    private Label nbp;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Evenement> a = FXCollections.observableArrayList() ;
           a.addAll(s.selectEvenement());
           lisEvents.setItems(a);
     
       lisEvents.setCellFactory(lv -> new ListCell<Evenement>() {
                @Override
                protected void updateItem(Evenement c, boolean empty) {
                    super.updateItem(c, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {

                     
                            setText("Evenement :  " + c.getNom()+ "                                                   Lieu : "+c.getLieu());
                            
                            setStyle("-fx-background-color: #B7D7F2");
                        
                    }
                }
            });
        
        
        
    //TODo
}
      

    @FXML
    private void afficherImage(MouseEvent me) throws IOException {
       
           
                 
             event= lisEvents.getSelectionModel().getSelectedItem();
            nbp.setText("Nombre de particpent :"+event.getNbr_participation()+"     ");
              titre.setText(event.getNom());
              date.setText(event.getDate().toString());
                 String url=s.getImgByid(event.getId_evenement());
          
                Image image =new Image(new FileInputStream(url));
           
         eventIMG.setImage(image);
             
           
         

    }

    
}
