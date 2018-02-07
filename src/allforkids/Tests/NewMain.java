/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.Tests;

import allforkids.entites.User;
import allforkids.service.ServiceUser;

/**
 *
 * @author slim
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          User u = new User( "dgh", "dhd", "dfg", "fvdg", "sg", "sgsg", 1);
        ServiceUser s = new ServiceUser();
        s.insrerUser(u);
    }
    
}
