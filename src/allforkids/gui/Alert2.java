/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author slim
 */
public class Alert2 {

    public static void afficherSuccses(String titre, String message) {
          javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setTitle(titre);
            alert.setHeaderText(message);
            alert.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(4));
            delay.setOnFinished(event -> alert.close());
            delay.play();

    }
     public static void afficherWARNING(String titre, String message) {
           javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitle(titre);
            alert.setHeaderText(message);
            alert.showAndWait();
            

    }
}
