package se.itello.example.datafileregistration;

import java.nio.file.Path;
import se.itello.example.payments.PaymentReceiver;


public class DataFileRegistrator {
    private static final String INBETALNINGSTJANSTEN = "_inbetalningstjansten.txt";
    private static final String BETALNINGSSERVICE = "_betalningsservice.txt";
    
    private final PaymentReceiver paymentReceiver;
    //...
    //...
    private Handler handler;

                                                        //...
    public DataFileRegistrator(PaymentReceiver paymentReceiver) {
        this.paymentReceiver = paymentReceiver;
    }
     
    public void register(Path path) {
        String ext = getExtension(path);
        switch(ext) {
            case INBETALNINGSTJANSTEN:
                //fileHandler = new InbetalingstjanstenFileHandler(path);
                break;
            case BETALNINGSSERVICE:
                handler = new BetalningsserviceHandler(paymentReceiver);
                handler.processAndRegisterFileData(path);
                
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
