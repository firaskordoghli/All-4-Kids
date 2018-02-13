/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.Tests;

import allforkids.entites.User;
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
        
       //s.insrerUser(u);
        //s.updateUser(u1, 4);
        s.deleteUser(8);
       /* for (User a : s.selectUser()) {
            System.out.println(a.toString());
        }*/
               
    }
    
}
