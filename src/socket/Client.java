package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    
    public static int portNumber = 9999;
    public static String ipaddress = "0.0.0.0";
    
    public static void main(String[] args) {
        Socket socket;
        
        PrintStream out;
        BufferedReader in;
        
        BufferedReader consoleIn;
        
        String consoleInputLine = "";
        String lineFromServer = "";
        
        try {
            socket = new Socket(ipaddress,portNumber);
            
            out = new PrintStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            consoleIn = new BufferedReader(new InputStreamReader(System.in));
            
            while (!consoleInputLine.equals("q")) {
                consoleInputLine = consoleIn.readLine();
                Sender.sendLine(out,consoleInputLine);
                
                lineFromServer = Receiver.getLine(in);
            }
            
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            if (e.getMessage().equals("Connection refused: connect"))
                System.out.println("No open socket at " + ipaddress + " port " + portNumber + ".");
            else
                System.out.println("Error: " + e);
        }
    }
}