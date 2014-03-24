package se.itello.example.startup;

import se.itello.example.registration.Registrator;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author Dejan
 */
public class Startup {

    public static void main(String[] args) throws IOException {
        Registrator dfr = new Registrator();
        
        Path path1 = Paths.get("resources/Exempelfil_betalningsservice.txt");
        Path path2 = Paths.get("resources/Exempelfil_inbetalningstjansten.txt");
        Path path3 = Paths.get("resources/Exempelfil_felfiltyp.txt");
        
        try{
            System.out.println("\nStartup registers "+path1.toRealPath(LinkOption.NOFOLLOW_LINKS));
            dfr.registerDataFile(path1);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println("\nStartup registers "+path2.toRealPath(LinkOption.NOFOLLOW_LINKS));
            dfr.registerDataFile(path2);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println("\nStartup registers "+path3.toRealPath(LinkOption.NOFOLLOW_LINKS));
            dfr.registerDataFile(path3);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
