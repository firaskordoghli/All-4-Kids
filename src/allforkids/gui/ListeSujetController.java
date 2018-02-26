/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Evenement;
import allforkids.entites.Sujet;
import allforkids.service.ServiceSujet;
import com.jfoenix.controls.JFXListView;
import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author casa-net
 */
public class ListeSujetController implements Initializable {

    @FXML
    private TableColumn<Sujet, String> titre;
    @FXML
    private TableColumn<Sujet, String> description;
    @FXML
    private TableColumn<Sujet, String> tag;
    @FXML
    private TableView<Sujet> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*   ServiceSujet s = new ServiceSujet();
         ObservableList<Label> a = FXCollections.observableArrayList();
        List<Sujet> e = new ArrayList<>();
        e = s.selectSujet();
        for (Sujet ev : e) {
                Label l = new Label("titre :        "+ev.getTitle()+"         tag :     "+ev.getTag());
                listeSujet.getItems().add(l);
         */
        ObservableList<Sujet> a = FXCollections.observableArrayList();
        titre.setCellValueFactory(new PropertyValueFactory<>("title"));
        tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        //avis.setCellValueFactory(new PropertyValueFactory<>("good"));
        TableColumn col_action = new TableColumn<>("good");
        table.getColumns().add(col_action);

        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }

        });
        
        TableColumn col_action2 = new TableColumn<>("bad");
        table.getColumns().add(col_action2);

        col_action2.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action2.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }

        });
        
        ServiceSujet s = new ServiceSujet();
        a.addAll(s.selectSujet());
        try {
            table.setItems(a);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    // TODO

    private void com(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("SujetCommenter.fxml"));
        Scene scene = new Scene(root);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);

        app_stage.show();
    }

    @FXML
    private void commantaire(MouseEvent event) throws IOException {
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SujetCommenter.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
