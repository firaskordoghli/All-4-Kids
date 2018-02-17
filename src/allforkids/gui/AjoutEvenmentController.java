/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.service.ServiceEvenement;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class AjoutEvenmentController implements Initializable {

    @FXML
    private TextField tflieu;
    @FXML
    private TextField tfNom;
    @FXML
    private DatePicker tfDate;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfnb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveEvenment(ActionEvent event) {
         ServiceEvenement eService = new ServiceEvenement();
        System.out.println(tfNom.getText());

        LocalDate d = tfDate.getValue() ;
    Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
       // Evenement e =new Evenement(tfNom.getText()
      //          ,tflieu.getText(),date,Integer.parseInt(tftype.getText())
       //     ,Integer.parseInt(tfnb.getText()),false,6);
      //  eService.insrerEvenement(e);
        tfNom.clear();
        tflieu.clear();
     
    }
    
}
