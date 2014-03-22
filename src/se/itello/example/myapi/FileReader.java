package se.itello.example.myapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class FileReader {
    
    public static List<String> textFileToList(Path path, Charset charset) {
        List<String> result = new ArrayList();
        try(BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line = null;
            while( ( line = reader.readLine() ) != null ) {
                System.out.println(line);
                result.add(line);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
}
