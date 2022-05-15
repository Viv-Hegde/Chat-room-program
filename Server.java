import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }

    public void startServer(){
        try {
            while (!serverSocket.isClosed()) {
                
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                ClientHandler ch= new ClientHandler(socket);

                Thread thread = new Thread(ch);
                thread.start();
            }
        } catch (Exception e) {
            closeServerSocket();
        }
    }

    public void closeServerSocket(){
        try {
            if(serverSocket!=null){
                serverSocket.close();            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        ServerSocket s = new ServerSocket(1234);
        Server myServer = new Server(s);
        myServer.startServer();
    }
}
