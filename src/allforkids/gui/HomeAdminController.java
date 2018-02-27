/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Sujet;
import allforkids.entites.User;
import allforkids.service.ServiceUser;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class HomeAdminController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> pernom;
    @FXML
    private TableColumn<?, ?> mail;
    @FXML
    private TableColumn<?, ?> photo;
    @FXML
    private JFXButton addAmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<User> a = FXCollections.observableArrayList();
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pernom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        photo.setCellValueFactory(new PropertyValueFactory<>("picture"));

        ServiceUser s = new ServiceUser();
        a.addAll(s.GetUserByRole(2));
        try {
            table.setItems(a);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        // TODO
    }    

    @FXML
    private void addAmin(ActionEvent event) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("CrationCompteAdmin.fxml"));
                  Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
        
        
        
        
    }
    
}
