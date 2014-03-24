package se.itello.example.registration;

import se.itello.example.registration.internal.FileHandler;
import se.itello.example.registration.internal.InbetalningstjanstenPaymentFileHandler;
import se.itello.example.registration.internal.BetalningsservicePaymentFileHandler;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import se.itello.example.registration.exceptions.FileTypeNotSupportedException;


public class Registrator {
    private enum FileFormat{
        BETALNINGSSERVICE("Betalningsservice"),
        INBETALNINGSTJANSTEN("Inbetalningstjänsten");
        
        private final String nameAsString;
        private FileFormat(String nameAsString) {
            this.nameAsString = nameAsString;
        }
        @Override
        public String toString() {
            return nameAsString;
        }
    }
    
    private final Map<String, FileFormat> extensionToFormat;
    private final Map<FileFormat, FileHandler> formatToHandler;
    
    public Registrator() {
        extensionToFormat = new HashMap<>();
        extensionToFormat.put("_betalningsservice.txt",                 FileFormat.BETALNINGSSERVICE);
        extensionToFormat.put("_inbetalningstjansten.txt",              FileFormat.INBETALNINGSTJANSTEN);
        
        formatToHandler = new HashMap<>();
        formatToHandler.put(FileFormat.BETALNINGSSERVICE,          new BetalningsservicePaymentFileHandler());
        formatToHandler.put(FileFormat.INBETALNINGSTJANSTEN,       new InbetalningstjanstenPaymentFileHandler());
    }
    public void registerDataFile(Path path) throws FileTypeNotSupportedException{
        FileFormat format = findFormat(path);
                    
        if(formatToHandler.containsKey(format)) {
            formatToHandler.get(format).dispatchFileData(path);
        }
        else {
            throw new FileTypeNotSupportedException("File format [" + format + 
                    "] is not supported. Supported file formats are : " +
                    formatToHandler.keySet()
            );
        }
    }
    private FileFormat findFormat(Path path) {
        String fileName = path.getFileName().toString();
        int extensionIndex = fileName.lastIndexOf("_");
        String extension = fileName.substring(extensionIndex);
        return extensionToFormat.get(extension);
    }
    
}
