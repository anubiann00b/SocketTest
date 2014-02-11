package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String args[]) {
        ServerSocket echoServer = null;
        String line = "";
        BufferedReader in;
        PrintStream out;
        Socket clientSocket = null;

        try {
            echoServer = new ServerSocket(9999);

            clientSocket = echoServer.accept();
            
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintStream(clientSocket.getOutputStream());
            
            while (!line.equals("q")) {
                line = Receiver.getLine(in);
                Sender.sendLine(out,line);
            }
            
            out.close();
            in.close();
            echoServer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
