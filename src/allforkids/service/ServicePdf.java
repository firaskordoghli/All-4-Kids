/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.service;

import com.adobe.acrobat.Viewer;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JPanel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author slim
 */
public class ServicePdf extends JPanel {

    private Viewer viewer;
   
    public ServicePdf(String pdf) throws Exception {
        this.setLayout(new BorderLayout());
        viewer = new Viewer();
        URL url = new URL(pdf);

        
     
        String tDir = System.getProperty("java.io.tmpdir");
        String path = tDir + "tmp" + ".pdf";
         File  file = new File(path);
        file.deleteOnExit();
        FileUtils.copyURLToFile(url, file);
        System.out.println(path);
        /**/
        File f = new File(path);
        FileInputStream filee = new FileInputStream(f);
        System.out.println(f.getPath());
        viewer.setDocumentInputStream(filee);
        this.add(viewer, BorderLayout.CENTER);
        viewer.activate();
    }

}
