package se.itello.example.registration.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

//Superklass till handlers av div. filtyper
public abstract class FileHandler {
    
    //Hjälpklass som används för att skilja ut data ur textrad.
    protected class DataRowSection {
        private final int beginIndex;
        private final int endIndex;
        
        //Definerar en datapost i textrad med samma start och slutposition
        //som anges i beskrivning av filformat.
        public DataRowSection(int startPosition, int endPosition) {
            this.beginIndex = startPosition-1;
            this.endIndex = endPosition;
        }
        public String getSectionFrom(String dataRow) {
            return dataRow.substring(beginIndex, endIndex);
        }
    }
    private static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
    private Charset charset;
    
    
    public FileHandler() {
        charset = DEFAULT_CHARSET;
    }
    
    protected void setCharset(Charset charset) {
        this.charset = charset;
    }
    
    public void dispatchFileData(Path path) {
        List<String> dataRows = readFile(path);
        parse(dataRows);
        registerData();
    }
    
    private List<String> readFile(Path path) {
        List<String> result = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line;
            while( ( line = reader.readLine() ) != null ) {
                result.add(line);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    //Avsedd att implementeras av subklass. Skall hämta relevant data ur textrader.
    abstract protected void parse(List<String> dataRows) ;
    
    //Avsedd att implementeras av subklass. Skall skicka data till lämplig receiver.
    abstract protected void registerData();
    
    
}
