package com.example.lec10.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class Server {

    static Map<Socket, String> clients = new HashMap<>();



    Server() throws IOException {
        ServerSocket server = new ServerSocket(8000);
        while (true) {
            Socket client = server.accept();
            new AcceptClient(client);
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }

    static class AcceptClient extends Thread {
        Socket clientSocket;
        DataInputStream in;
       // DataOutputStream out;

        AcceptClient(Socket client) throws IOException {
            clientSocket = client;
            in = new DataInputStream(clientSocket.getInputStream());
          //  out = new DataOutputStream(clientSocket.getOutputStream());
            start();
        }



        private void broadcastToAll(String message) throws IOException {
            System.out.println(message);
            DataOutputStream out;
            for (Map.Entry<Socket, String> entry : clients.entrySet()) {
                if(!entry.getKey().equals(clientSocket)){
                    out = new DataOutputStream(entry.getKey().getOutputStream());
                    out.writeUTF(message);
                    out.flush();
                }
            }
        }

        public void run() {

            try {
                String name = "";
                while (true) {
                    String msgFromClient = in.readUTF();
                    if(!clients.containsKey(clientSocket)){
                        clients.put(clientSocket, msgFromClient);
                        name = clients.get(clientSocket);
                        String greeting = name + " join chat!";
                        broadcastToAll(greeting);
                    }else {
                        broadcastToAll(name + ": " + msgFromClient);
                    }
                }

            } catch (IOException e) {
                String leave = String.format("%s left chat", clients.get(clientSocket));
                try {
                    clients.remove(clientSocket);
                    broadcastToAll(leave);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }

    }

}
