package se.itello.example.datafileregistration;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;


public abstract class Handler {
    protected static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
    protected static Charset charset;
    
    protected List<String> lines;
    
    public Handler() {
        charset = DEFAULT_CHARSET;
    }
    
    
    public void processAndRegisterFileData(Path path) {
    }
}
