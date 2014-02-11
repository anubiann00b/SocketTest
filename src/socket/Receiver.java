package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Receiver {
    
    public static int portNumber = 7077;
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",portNumber);
            
            PrintStream consoleOut = new PrintStream(System.out);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));   
            
            String line = "";
            while (!line.equals("q")) {
                line = in.readLine();
                consoleOut.println("Recived input: " + line);
            }
            
            consoleOut.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}