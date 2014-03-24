package se.itello.example.registration.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public abstract class FileHandler {
    protected class DataPostSection {
        private final int beginIndex;
        private final int endIndex;
        public DataPostSection(int startPosition, int endPosition) {
            this.beginIndex = startPosition-1;
            this.endIndex = endPosition;
        }
        public String getDataFrom(String post) {
            return post.substring(beginIndex, endIndex);
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
        List<String> dataPosts = readFile(path);
        parse(dataPosts);
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
    
    abstract protected void parse(List<String> lines);
    
    abstract protected void registerData();
    
    
}
