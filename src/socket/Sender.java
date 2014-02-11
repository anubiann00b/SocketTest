package socket;

import java.io.PrintStream;

public class Sender {
    
    public static void sendLine(PrintStream out, String line) {
        out.println(line);
        System.out.println("Sent: " + line);
    }
}