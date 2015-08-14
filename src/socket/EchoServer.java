package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    
    public static ServerSocket echoServer;
    
    public static void main(String args[]) {
        try {
            System.out.println("Opening Socket.");
            
            echoServer = new ServerSocket(9999);
            
            System.out.println("Waiting for Incoming Connections...");
            
            while (true) {
                new Thread(new ClientHandlerTask(echoServer.accept())).start();
            }
        } catch (IOException e) {
            System.out.println("Fatal Error: " + e);
            System.out.println("Fatal: Aborting.");
        }
    }

    static class ClientHandlerTask implements Runnable {
        
        private final Socket clientSocket;
        
        private ClientHandlerTask(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                String line = "";
                
                BufferedReader in;
                PrintStream out;
                
                System.out.println("Found Client: " + clientSocket.getInetAddress());
                
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintStream(clientSocket.getOutputStream());
                
                while (!line.equals("q")) {
                    line = Receiver.getLine(in);
                    Sender.sendLine(out,line);
                }
                
                System.out.println("Closing Connections.");
                
                out.close();
                in.close();
                clientSocket.close();
                
                System.out.println("Sucessful Run!");
            } catch (IOException e) {
                System.out.println("Error in thread: " + e);
            }
        }
    }
}
