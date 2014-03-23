package se.itello.example.datafileregistration;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;


public abstract class Handler {
    protected class DataPostSection {
        private final int beginIndex;
        private final int endIndex;
        public DataPostSection(int startPosition, int endPosition) {
            this.beginIndex = startPosition-1;
            this.endIndex = endPosition;
        }
        public String getData(String line) {
            return line.substring(beginIndex, endIndex);
        }
    }
    protected static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
    protected static Charset charset;
    
    protected List<String> lines;
    
    protected Handler() {
        charset = DEFAULT_CHARSET;
    }
    public void dispatchFileData(Path path) {
        lines = FileReader.textFileToList(path, charset);
        parseLines();
        registerData();
    }
    protected void parseLines() {
    }
    public void registerData() {
    }
}
