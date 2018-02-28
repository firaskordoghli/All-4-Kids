/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Session;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class CodeController implements Initializable {
public static int codeMail;

    public static int getCodeMail() {
        return codeMail;
    }

    public static void setCodeMail(int codeMail) {
        CodeController.codeMail = codeMail;
    }

    @FXML
    private JFXTextField code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ValiderCode(ActionEvent event) throws IOException {
       
        if (CodeController.getCodeMail()==Integer.parseInt(code.getText()) ){
            
         
            Parent root = FXMLLoader.load(getClass().getResource("ChangerMdp.fxml"));
         
        Scene scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        
        app_stage.setScene(scene);
        
        app_stage.show();
        
        
        }
                
                
    }}
    

