/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.Tests;

import allforkids.entites.Evenement;
import allforkids.entites.User;
import allforkids.service.ServiceEvenement;
import allforkids.service.ServiceUser;
import java.util.Date;

/**
 *
 * @author slim
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* User u = new User( "dgh", "dhd", "dfg", "fvdg", new Date(), "sgsg", 1, "22");
            User u1 = new User( "0747", "hamdi", "slim", "sl.@e.net", new Date(), "dde", 1,"");*/
        ServiceUser s = new ServiceUser();
         Evenement e = new Evenement("ss","ss",new Date(5, 5, 5),1,5,false,6);
         ServiceEvenement es = new ServiceEvenement();
         es.insrerEvenement(e);
       //s.insrerUser(u);
        //s.updateUser(u1, 4);
        //s.deleteUser(8);
       /* for (User a : s.selectUser()) {
            System.out.println(a.toString());
        }*/
               
    }
    
}
