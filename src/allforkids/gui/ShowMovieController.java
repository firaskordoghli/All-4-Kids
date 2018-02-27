/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author slim
 */
public class ShowMovieController implements Initializable {

    @FXML
    private JFXButton play;
    @FXML
    private MediaView mv;
  private  MediaPlayer mp ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     File f = new File("C:\\Users\\slim\\Desktop\\33.mp4");

      Media m = new Media(f.toURI().toString());
       mp = new MediaPlayer(m);
       System.out.println(mp.getMedia().getSource().toString());
       mv.setMediaPlayer(mp); 
   
        

      

       
        
       
 
    }    

    @FXML
    private void playMovie(ActionEvent event) {
       if(play.getText().equals("play")){ 
           mp.play();
           play.setText("stop");
       }else{
        mp.pause();
           play.setText("play");
       
       }
    }
    
}
