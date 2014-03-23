package se.itello.example.datafileregistration;

import java.nio.file.Path;
import se.itello.example.datafileregistration.exceptions.FileTypeNotSupportedException;
import se.itello.example.payments.PaymentReceiver;


public class DataFileRegistrator {
    public static final String INBETALNINGSTJANSTEN = "_inbetalningstjansten.txt";
    public static final String BETALNINGSSERVICE = "_betalningsservice.txt";
    
    private Handler handler;

     
    public void register(Path path) throws FileTypeNotSupportedException{
        String ext = getExtension(path);
        switch(ext) {
            case INBETALNINGSTJANSTEN:
                handler = new InbetalningstjanstenHandler();
                break;
            case BETALNINGSSERVICE:
                handler = new BetalningsserviceHandler();
                break;
            default:
                throw new FileTypeNotSupportedException(
                        "File format " + ext + "is not supported."
                );
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
