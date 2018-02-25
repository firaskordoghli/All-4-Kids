/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import com.adobe.acrobat.Viewer;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JPanel;

/**
 *
 * @author slim
 */
public class ServicePdf extends JPanel{
    private Viewer viewer ;
  
    public ServicePdf(String pdf) throws Exception {
        this.setLayout(new BorderLayout());
        viewer = new Viewer();
          URL url = new URL(pdf);
           System.out.println("Upload URL: " + pdf);
            URLConnection conn = url.openConnection();
           OutputStream outputStream = conn.getOutputStream();
        FileInputStream file = new FileInputStream(pdf);
        viewer.setDocumentInputStream(file);
        this.add(viewer, BorderLayout.CENTER);
        viewer.activate();
    }
    
     
}
