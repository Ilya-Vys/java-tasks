package com.example.lec10.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    private final Map<Socket, String> clients = new ConcurrentHashMap<>();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    private Server() throws IOException {
        ServerSocket server = new ServerSocket(8000);
        System.out.println("Server is running!");
        while (true) {
            Socket client = server.accept();
            threadPool.submit(new ClientListener(client));

        }
    }

    public static void main(String[] args) throws IOException {
        new Server();

    }

    private class ClientListener implements Runnable {
        private final Socket client;
        private final DataInputStream in;
        private DataOutputStream out;
        private String name;

        ClientListener(Socket client) throws IOException {
            this.client = client;
            this.in = new DataInputStream(this.client.getInputStream());
            run();
        }

        public void run() {
            try {
                while (true) {
                    String msgFromClient = in.readUTF();
                    if(!clients.containsKey(client)){
                        clients.put(client, msgFromClient);
                        broadcastToAll(msgFromClient + " join chat");
                    }else {
                        name = clients.get(client);
                        handleMessage(msgFromClient);
                    }
                }

            } catch (IOException e) {
                String duplicate = name;
                String leave = String.format("%s left chat", duplicate);
                try {
                    clients.remove(client);
                    broadcastToAll(leave);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }

        private void handleMessage(String message) throws IOException {
            if (checkIsMessagePrivate(message)){
                System.out.println(String.format("private message from %s %s",name, message));
                handlePrivateMessage(message);
            }else {
                broadcastToAll(name + ": " + message);
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
                sendMessage(socket.get(), String.format("Message from %s %s", name, msg));
            }
        }

        private void sendMessage(Socket socket, String message) throws IOException {
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(message);
            out.flush();
        }
    }

}
