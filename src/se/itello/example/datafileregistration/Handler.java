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
        public String getDataFrom(String line) {
            return line.substring(beginIndex, endIndex);
        }
    }
    protected static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
    protected static Charset charset;
    
    
    protected Handler() {
        charset = DEFAULT_CHARSET;
    }
    public void dispatchFileData(Path path) {
        parseLines( readFile(path) );
        registerData();
    }
    protected List<String> readFile(Path path) {
        return FileReader.textFileToList(path, charset);
    }
    abstract protected void parseLines(List<String> lines);
    
    abstract protected void registerData();
}
