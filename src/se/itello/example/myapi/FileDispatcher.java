package se.itello.example.myapi;

import java.nio.file.Path;
import se.itello.example.payments.PaymentReceiver;


public class FileDispatcher {
    private static final String INBETALNINGSTJANSTEN = "_inbetalningstjansten.txt";
    private static final String BETALNINGSSERVICE = "_betalningsservice.txt";
    
    private final PaymentReceiver paymentReceiver;
    //...
    //...
    private FileHandler fileHandler;

                                                        //...
    public FileDispatcher(PaymentReceiver paymentReceiver) {
        this.paymentReceiver = paymentReceiver;
    }
    
    public void dispatchFile(Path path) {
        String ext = getExtension(path);
        switch(ext) {
            case INBETALNINGSTJANSTEN:
                //fileHandler = new InbetalingstjanstenFileHandler(path);
                break;
            case BETALNINGSSERVICE:
                fileHandler = new BetalningsserviceFileHandler(paymentReceiver);
                fileHandler.sendDataToReciever(path);
                
                break;
        }
        
    }
    
    private String getExtension(Path path) {
        String fileName = path.getFileName().toString();
        int iA = fileName.lastIndexOf("_");
        int iB = fileName.length();
        return fileName.substring(iA, iB);
    }
    
}
