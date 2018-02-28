/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Movie;
import allforkids.entites.Session;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

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
    @FXML
    private ProgressBar progresbar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        try {
            url = new URL("ftp://slim:07471917@" + Session.getIp() + MovieController.url);
      
        String tDir = System.getProperty("java.io.tmpdir");
        String path = tDir + "tmp" + ".mp4";
         File  file = new File(path);
        file.deleteOnExit();
        FileUtils.copyURLToFile(url, file);
     File f = new File(path);
      play.setText("play");
      Media m = new Media(f.toURI().toString());
       mp = new MediaPlayer(m);
       System.out.println(mp.getMedia().getSource().toString());
       mv.setMediaPlayer(mp); 
       
       progresbar.setMaxWidth(400);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ShowMovieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShowMovieController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
     
       
       
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
