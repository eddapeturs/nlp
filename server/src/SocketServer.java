import java.net.*;
import java.io.*;


public class SocketServer {

    public static final int PORT_NUMBER = 6666;

    protected Socket socket;



    private SocketServer(Socket socket) {
        this.socket = socket;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        run();
    }

    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String request;
            while ((request = br.readLine()) != null) {
                System.out.println("Message received:" + request);
                request += '\n';
                out.write(request.getBytes());
            }

        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("SocketServer Example");
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {

                new SocketServer(server.accept());
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private String BuildResponseString(String response, String taggedString, String parsedString){

        response = "\"string\":\"" +  response + "\"";
        taggedString = ", \"tagg\":\"" +  taggedString + "\"";
        parsedString = ", \"parse\":\"" +  parsedString + "\"";

        return "{ " + response + taggedString + parsedString + " }";
    }
}



