package server;

import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public CarWareHouse cwh;
    private ServerSocket serverSocket;
    public HashMap<String, String> userMap; //This is to store name and password

    Server() {
        cwh=new CarWareHouse();
        cwh.load();
        userMap = new HashMap<>();
        userMap.put("a", "a");
        userMap.put("b", "b");
        userMap.put("c", "c");
        userMap.put("d", "d");
        userMap.put("e", "e");
        try {
            serverSocket = new ServerSocket(33333); //this is soul
            while (true) {
                //We have to provide service to a lot of clients
                Socket clientSocket = serverSocket.accept(); //this is body
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Hello from server");
            System.out.println("Server starts:" + e);
        }
    }
    public void serve(Socket clientSocket) throws IOException {

            NetworkUtil networkUtil = new NetworkUtil(clientSocket);
            new ReadThreadServer(userMap, networkUtil);
    }

    public static void main(String[] args) {
        new Server();
    }
}
