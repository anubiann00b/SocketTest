package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    
    public static int portNumber = 9999;
    
    public static void main(String[] args) {
        Socket socket = null;
        
        PrintStream out = null;
        BufferedReader in = null;
        
        PrintStream consoleOut = null;
        BufferedReader consoleIn = null;
        
        String consoleInputLine = "";
        String lineFromServer = "";
        
        try {
            socket = new Socket("127.0.0.1",portNumber);
            
            out = new PrintStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            consoleOut = new PrintStream(System.out);
            consoleIn = new BufferedReader(new InputStreamReader(System.in));
            
            while (!consoleInputLine.equals("q")) {
                consoleInputLine = consoleIn.readLine();
                consoleOut.println("Sending: " + consoleInputLine);
                out.println(consoleInputLine);
                
                lineFromServer = in.readLine();
                consoleOut.println("Recieving: " + lineFromServer);
            }
            
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}