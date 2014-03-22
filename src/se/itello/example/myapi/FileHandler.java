package se.itello.example.myapi;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;


public abstract class FileHandler {
    protected static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
    protected static Charset charset;
    
    protected List<String> lines;

    public FileHandler() {
    }
    
    
    public void sendDataToReciever(Path path) {
        lines = FileReader.textFileToList(path, charset);
        parseLines();
        sendToReciever();
    }
    private void parseLines() {}
    
    private void sendToReciever() {}
}
