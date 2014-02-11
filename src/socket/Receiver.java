package socket;

import java.io.BufferedReader;
import java.io.IOException;

public class Receiver {
    
    public static String getLine(BufferedReader in) throws IOException {
        String line = in.readLine();
        System.out.println("Recieved: " + line);
        return line;
    }
}