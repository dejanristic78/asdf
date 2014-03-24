package se.itello.example.startup;

import se.itello.example.registration.DataFileRegistrator;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Dejan
 */
public class Startup {

    public static void main(String[] args) throws IOException {
        DataFileRegistrator dfr = new DataFileRegistrator();
        
        List<Path> toRegister = new ArrayList();
        
        toRegister.add( Paths.get("src/Exempelfil_betalningsservice.txt") );
        toRegister.add( Paths.get("src/Exempelfil_inbetalningstjansten.txt") );
        toRegister.add( Paths.get("src/Exempelfil_felformat.txt") );
        
        
        for (Path path : toRegister) {
            try{
                System.out.println( "\nStartup.main() Try to register: "+path.getFileName() );
                dfr.register(path);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            
        }
        
    }
    
}
