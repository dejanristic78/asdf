package se.itello.example.datafileregistration;

import java.nio.file.Path;
import se.itello.example.payments.PaymentReceiver;


public class DataFileRegistrator {
    private static final String INBETALNINGSTJANSTEN = "_inbetalningstjansten.txt";
    private static final String BETALNINGSSERVICE = "_betalningsservice.txt";
    
    private Handler handler;

                                                        //...
     
    public void register(Path path) {
        String ext = getExtension(path);
        switch(ext) {
            case INBETALNINGSTJANSTEN:
                //handler = new InbetalingstjanstenFileHandler(path);
                break;
            case BETALNINGSSERVICE:
                handler = new BetalningsserviceHandler();
                break;
        }
        handler.dispatchFileData(path);
        
    }
    
    private String getExtension(Path path) {
        //Sanity check
        String fileName = path.getFileName().toString();
        int extIndex = fileName.lastIndexOf("_");
        return fileName.substring(extIndex);
    }
    
}
