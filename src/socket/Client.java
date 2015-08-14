package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    
    public static int portNumber = 9999;
    public static String ipaddress = "0.0.0.0";
    
    public static Socket socket;
    
    public static void min(String[] args) {
        PrintStream out;
        BufferedReader in;
        
        BufferedReader consoleIn;
        
        String consoleInputLine = "";
        String lineFromServer = "";
        
        try {
            System.out.println("Attempting to connect to server.");
            
            socket = new Socket(ipaddress,portNumber);
            
            System.out.println("Connected to Server.");
            
            out = new PrintStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            consoleIn = new BufferedReader(new InputStreamReader(System.in));
            
            while (!consoleInputLine.equals("q")) {
                consoleInputLine = consoleIn.readLine();
                Sender.sendLine(out,consoleInputLine);
                
                lineFromServer = Receiver.getLine(in);
            }
            
            System.out.println("Closing Connections.");
            
            out.close();
            in.close();
            socket.close();
            
            System.out.println("Successful Run!");
            
        } catch (IOException e) {
            switch (e.getMessage()) {
                case "Connection refused: connect":
                    System.out.println("Fatal: No open socket at " + ipaddress + " port " + portNumber + ".");
                    break;
                case "Connection timed out: connect":
                    System.out.println("Connection timed out: " + ipaddress + " port " + portNumber + ".");
                    break;
                default:
                    System.out.println("Fatal Error: " + e);
                    break;
            }
            System.out.println("Fatal: Aborting.");
        }
    }
}