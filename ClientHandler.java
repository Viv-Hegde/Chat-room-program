import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    
    public static ArrayList<ClientHandler> ch = new ArrayList<>();
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String clientUsername;

    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = reader.readLine();
            ch.add(this);
            broadcastMessage("Server: "+clientUsername+" has entered the chat!");

        } catch (IOException e) {
            closeEverything(socket,reader,writer);
        }
    }

    @Override
    public void run(){
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = reader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket,reader,writer);
                break;
            }
            
        }
    }

    public void broadcastMessage(String msg){
        for (ClientHandler c : ch) {
            try {
                if(!c.clientUsername.equals(clientUsername)){
                    c.writer.write(msg);
                    c.writer.newLine();
                    c.writer.flush();
                }
            } catch (IOException e) {
                closeEverything(socket,reader,writer);
            }
        }
    }

    public void removeClientHandler(){
        ch.remove(this);
        broadcastMessage("SERVER: "+clientUsername+" has left the chat!");
    }

    public void closeEverything(Socket s, BufferedReader r, BufferedWriter w){
        removeClientHandler();

        try {
            if(r != null){
                r.close();
            }
            if(w != null){
                w.close();
            }
            if(s != null){
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
