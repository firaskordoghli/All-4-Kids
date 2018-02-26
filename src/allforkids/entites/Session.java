/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.entites;

/**
 *
 * @author casa-net
 */
public class Session {
         private static int IdThisUser=0;

    public static int getIdThisUser() {
        return IdThisUser;
    }

    public static void setIdThisUser(int IdThisUser) {
        Session.IdThisUser = IdThisUser;
    }
    
}
