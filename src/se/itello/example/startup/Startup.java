package se.itello.example.startup;

import se.itello.example.datafileregistration.DataFileRegistrator;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import se.itello.example.datafileregistration.BetalningsserviceHandler;
import se.itello.example.datafileregistration.Handler;

import se.itello.example.payments.PaymentReceiver;
import se.itello.example.payments.PaymentReceiverDummy;

/**
 *
 * @author Dejan
 */
public class Startup {

    public static void main(String[] args) throws IOException {
        DataFileRegistrator dfr = new DataFileRegistrator();

        Path path1 = Paths.get("src/Exempelfil_betalningsservice.txt");
        Path path2 = Paths.get("src/Exempelfil_inbetalningstjansten.txt");
        
        try{
            System.out.println("\nStartup registers "+path1.toRealPath(LinkOption.NOFOLLOW_LINKS));
            dfr.register(path1);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println("\nStartup registers "+path2.toRealPath(LinkOption.NOFOLLOW_LINKS));
            dfr.register(path2);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
