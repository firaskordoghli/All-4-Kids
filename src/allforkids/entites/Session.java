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
         
         private static String ip = "127.0.0.1";
         private static int IdThisSujet=0;
          public static String addImage = "file:///C:/Users/slim/Desktop/";

    public static String getIp() {
        return ip;
    }
         
         
    public static int getIdThisUser() {
        return IdThisUser;
    }

    public static void setIdThisUser(int IdThisUser) {
        Session.IdThisUser = IdThisUser;
    }

    public static int getIdThisSujet() {
        return IdThisSujet;
    }

    public static void setIdThisSujet(int IdThisSujet) {
        Session.IdThisSujet = IdThisSujet;
    }

   
    
}
