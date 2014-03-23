package se.itello.example.datafileregistration;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import se.itello.example.datafileregistration.exceptions.FileTypeNotSupportedException;
import se.itello.example.payments.PaymentReceiver;


public class DataFileRegistrator {
    private static final String INBETALNINGSTJANSTEN = "_inbetalningstjansten.txt";
    private static final String BETALNINGSSERVICE = "_betalningsservice.txt";
    
    private Map<String, Handler> handlers;
    
    public DataFileRegistrator() {
        handlers = new HashMap<>();
        handlers.put(INBETALNINGSTJANSTEN, new InbetalningstjanstenHandler());
        handlers.put(BETALNINGSSERVICE, new BetalningsserviceHandler());
    }
    public void register(Path path) throws FileTypeNotSupportedException{
        String ext = getExtension(path);
                    
        if(handlers.containsKey(ext)) {
            handlers.get(ext).dispatchFileData(path);
        }
        else {
            throw new FileTypeNotSupportedException("File type [" + ext + 
                    "] is not supported. Supported file types are : " +
                    handlers.keySet()
            );
        }
    }
    private String getExtension(Path path) {
        String fileName = path.getFileName().toString();
        int extIndex = fileName.lastIndexOf("_");
        return fileName.substring(extIndex);
    }
    
}
