/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import allforkids.entites.Session;
import allforkids.entites.User;
import allforkids.service.ServiceUser;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */

public class HomeController implements Initializable {
    
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane holderPane;

    /**
     * Initializes the controller class.
     */
        
  @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }

        });
        
        ServiceUser su = new ServiceUser();
        User u = su.GetUserById(Session.getIdThisUser());
        
        try {
            VBox sidePane = FXMLLoader.load(getClass().getResource("Drawer.fxml"));
            
            AnchorPane acceuil = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
//            AnchorPane transportPane = FXMLLoader.load(getClass().getResource("CovoiturageView.fxml"));
            AnchorPane evenmentPane = FXMLLoader.load(getClass().getResource("Evenement.fxml"));
            AnchorPane etablissementPane = FXMLLoader.load(getClass().getResource("Etablismentint.fxml"));
            AnchorPane profilPane = FXMLLoader.load(getClass().getResource("Profil.fxml"));
          //  AnchorPane AdminService = FXMLLoader.load(getClass().getResource("ServiceAdmin.fxml"));
         //   AnchorPane AdminReclamation = FXMLLoader.load(getClass().getResource("AdminReclamation.fxml"));
         //   AnchorPane ConsulterService = FXMLLoader.load(getClass().getResource("ConsulterService.fxml"));
        //    AnchorPane MesReclamation = FXMLLoader.load(getClass().getResource("MesReclamations.fxml"));
             AnchorPane Adminevenment = FXMLLoader.load(getClass().getResource("Adminevenment.fxml"));

            setNode(acceuil);
            drawer.setSidePane(sidePane);
            
            for (Node node : sidePane.getChildren()){
                
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
                            
                            case "service":
                                drawer.close();
                              //  if(u.getRole()==0) {setNode(AdminService);}
                              //  else{setNode(ConsulterService);}
                                break;
                            case "reclamationMenu":
                                drawer.close();
                               //  if(u.getRole()==0) {setNode(AdminReclamation);}
                               // else{setNode(MesReclamation);}
                               
                                break;
                            case "acceuilMenu":
                                drawer.close();
                                setNode(acceuil);
                                break;
                            case "transportMenu":
                                drawer.close();                               
                            //    setNode(transportPane);
                                break;
                            case "etablissmentMenu":
                                drawer.close();
                               setNode(etablissementPane);
                                break;
                            case "divertissementMeni":
                                drawer.close();  
                                 if(u.getRole()==0) {setNode(evenmentPane);}
                                 else{ setNode(evenmentPane);}
                                break;
                                case "profilMenu":
                                drawer.close();                                
                                setNode(profilPane);
                                break;
                        }
                    });
                }
                
            }
                
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    
    
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }
    
    
}

