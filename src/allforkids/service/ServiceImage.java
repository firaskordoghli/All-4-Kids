/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import allforkids.entites.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import javafx.scene.image.Image;

/**
 *
 * @author slim
 */
public class ServiceImage {
      public  static String filePath ;
      private static final int BUFFER_SIZE = 4096;
      private static String imgg="" ;

    public static String getImgg() {
        return imgg;
    }

    public static void setImgg(String imgg) {
        ServiceImage.imgg = imgg;
    }
      
         public static void saveimg(Image image, String name, String p, File file) {
        if (p.indexOf(".png") != -1) {
            filePath = file.getPath();
            System.out.println(filePath);

            String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
            String host = Session.getIp();
            String user = "slim";
            String pass = "07471917";

            String uploadPath = "/img/" + name + ".png";

            ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
            System.out.println("Upload URL: " + ftpUrl);

            try {
                URL url = new URL(ftpUrl);
                URLConnection conn = url.openConnection();
                OutputStream outputStream = conn.getOutputStream();
                FileInputStream inputStream = new FileInputStream(filePath);

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();

                System.out.println("File uploaded");
                imgg = "/img/" + name + ".png";

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {

            filePath = file.getPath();
            System.out.println(filePath);

            String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
            String host = Session.getIp();
            String user = "slim";
            String pass = "07471917";

            String uploadPath = "/img/" + name + ".jpeg";

            ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
            System.out.println("Upload URL: " + ftpUrl);

            try {
                URL url = new URL(ftpUrl);
                URLConnection conn = url.openConnection();
                OutputStream outputStream = conn.getOutputStream();
                FileInputStream inputStream = new FileInputStream(filePath);

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();

                System.out.println("File uploaded");
                imgg = "/img/" + name + ".jpeg";

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
