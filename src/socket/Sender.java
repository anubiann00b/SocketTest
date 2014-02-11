package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Sender {
    
    public static int portNumber = 7077;
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",portNumber);
            
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));   
            
            String line = "";
            while (!line.equals("q")) {
                line = consoleIn.readLine();
                out.println("Outputting: " + line);
            }
            
            out.close();
            consoleIn.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}