/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.itello.example.startup;

import se.itello.example.datafileregistration.DataFileRegistrator;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import se.itello.example.payments.PaymentReceiver;
import se.itello.example.payments.PaymentReceiverDummy;

/**
 *
 * @author Dejan
 */
public class Startup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/Exempelfil_betalningsservice.txt");
        System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));
        
        DataFileRegistrator dfr = new DataFileRegistrator();
        dfr.register(path);
    }
    
}
