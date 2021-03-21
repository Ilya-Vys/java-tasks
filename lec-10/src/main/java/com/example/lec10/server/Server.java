package com.example.lec10.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class Server {

    private static final Map<Socket, String> clients = new HashMap<>();

    private Server() throws IOException {
        ServerSocket server = new ServerSocket(8000);
        while (true) {
            Socket client = server.accept();
            new ClientListener(client);
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }

    static class ClientListener extends Thread {
        Socket client;
        DataInputStream in;
        DataOutputStream out;

        ClientListener(Socket client) throws IOException {
            this.client = client;
            in = new DataInputStream(this.client.getInputStream());
            start();
        }

        public void run() {
            try {

                while (true) {
                    String msgFromClient = in.readUTF();
                    if (checkIsMessagePrivate(msgFromClient)){
                        System.out.println(String.format("private message from %s %s",clients.get(client), msgFromClient));
                        handlePrivateMessage(msgFromClient);
                        return;
                    }
                    if(!clients.containsKey(client)){
                        clients.put(client, msgFromClient);
                        broadcastToAll(msgFromClient + " join chat");
                    }else {
                        broadcastToAll(clients.get(client) + ": " + msgFromClient);
                    }
                }

            } catch (IOException e) {
                String leave = String.format("%s left chat", clients.get(client));
                try {
                    clients.remove(client);
                    broadcastToAll(leave);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }

        private boolean checkIsMessagePrivate(String message){
            return message.matches("to (?i)[\\p{L}']+:.*");
        }

        private void broadcastToAll(String message) throws IOException {
            System.out.println(message);
            clients.entrySet().stream()
                    .filter(socketStringEntry -> !socketStringEntry.getKey().equals(client))
                    .forEach(e -> {
                        try {
                            sendMessage(e.getKey(), message);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
        }

        private void handlePrivateMessage(String message) throws IOException {
            String[] strings = message.split(":");
            String recipient = strings[0].substring(3);
            strings[0] = "";
            String msg = String.join(":", strings);
            Optional<Socket> socket = clients.entrySet().stream()
                    .filter(entry -> Objects.equals(entry.getValue(), recipient))
                    .map(Map.Entry::getKey)
                    .findAny();
            if(socket.isEmpty()){
                sendMessage(client, "Wrong name " + recipient);
            }else {
                sendMessage(socket.get(), String.format("Message from %s %s", clients.get(client), msg));
            }
        }

        private void sendMessage(Socket socket, String message) throws IOException {
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(message);
            out.flush();
        }
    }

}
