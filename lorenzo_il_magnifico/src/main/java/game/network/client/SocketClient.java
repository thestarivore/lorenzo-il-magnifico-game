package game.network.client;

import views.GameView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by starivore on 6/3/17.
 */
public class SocketClient implements ClientInterface, Runnable{
    private String ip;
    private int port;
    private GameView gameView;

    private static SocketClient ourInstance = null;

    /**
     * Get Istance of the Client, creat a new one if none is present
     * @param ip ip address of the server
     * @param port port address where the server is leastening
     * @return the instance of the client socket
     */
    public static SocketClient getInstance(String ip, int port) {
        if(ourInstance == null)
            ourInstance = new SocketClient(ip, port);

        return ourInstance;
    }

    /**
     * Socket Client Constructor
     * @param ip ip address of the server
     * @param port port address where the server is leastening
     */
    public SocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * Start Socket Client communication manager
     * @throws IOException
     */
    public void startClient() throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");
        Scanner socketIn = new Scanner(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System.in);
        try {
            while (true) {
                /*String inputLine = stdin.nextLine();
                socketOut.println(inputLine);
                socketOut.flush();*/

                String socketLine = socketIn.nextLine();
                System.out.println(socketLine);
                if(socketLine.equals("WELCOME_CMD")) { //TODO: make a map for the protocol commands
                    gameView.printWelcomeMessage();
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Connection closed");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }

    /**
     * Thread Execution method
     */
    public void run() {
        try {
            // Start Delay that ensures that the server is up(in case of not manual boot)
            Thread.sleep(100);
            // Start Client
            startClient();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
}