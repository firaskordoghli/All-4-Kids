/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Sujet;
import allforkids.service.ServiceSujet;
import com.sun.prism.impl.Disposer.Record;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author casa-net
 */
public class Rayen extends Application {

    private Button btn;

    @Override
    public void start(Stage stage) throws IOException {
       
        /* VBox root = new VBox();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();

        // Parent root =FXMLLoader.load(getClass().getResource("ListeSujet.fxml"));
        
        TableView<Sujet> tab = new TableView<>();
        TableColumn<Sujet, String> titre = new TableColumn<>("titre");
        TableColumn<Sujet, String> tag = new TableColumn<>("tag");
        TableColumn<Sujet, String> avis = new TableColumn<>("avis");
        TableColumn<Button, Button> good1 = new TableColumn<>("good");
        /* titre.setCellFactory(new PropertyValueFactory<>("titre"));
        tag.setCellFactory(new PropertyValueFactory<>("tag"));
        avis.setCellFactory(new PropertyValueFactory<>("avis"));
        
        titre.setCellValueFactory(new PropertyValueFactory<>("title"));
        tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
       good1.setCellValueFactory(new PropertyValueFactory<>("sds"));
        
        tab.getColumns().addAll(titre, tag, avis);

        //Scene scence = new Scene(root);
        root.getChildren().add(tab);
        ServiceSujet s = new ServiceSujet();
        ObservableList<Label> a = FXCollections.observableArrayList();
        List<Sujet> e = new ArrayList<>();
        e = s.selectSujet();
           //Insert Button
        TableColumn col_action = new TableColumn<>("Action");
        tab.getColumns().add(col_action);
        
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

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
        tab.getItems().addAll(e);
        
    }

    public Rayen() {
    }

    public Rayen(Button btn) {
        this.btn = btn;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
*/
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
       
       
     
               
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
