/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.gui;

import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 *
 * @author casa-net
 */
public class ButtonCell extends TableCell<Disposer.Record, Boolean> {

    public ButtonCell() {
    }
    final Button cellButton = new Button("delete");
    
        
      

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
                ActionEvent event=null;
                
             /*  ListeSujetController ls = new ListeSujetController();
               ls.delete(event);*/
            }
        }
        
        
    }
    

