package com.example.lec10.client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;


public class Client3 {
    private final Socket socket;
    private final DataOutputStream outToServer;
    private final DataInputStream din;
    private final Thread thread;

    Client3() throws IOException {
        this.socket = new Socket("127.0.0.1", 8000);
        this.outToServer = new DataOutputStream(socket.getOutputStream());
        this.din = new DataInputStream(socket.getInputStream());
        this.thread = new Thread(() -> {
            while (!socket.isClosed()) {
                try {
                    System.out.println(din.readUTF());
                } catch (IOException e) {
                    System.out.println("You left chat");
                }

            }
        });
        thread.start();
        startChatting();
    }

    private void startChatting(){
        String message = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Enter you name: ");
            String name = reader.readLine();
            outToServer.writeUTF(name);
            while (!message.equalsIgnoreCase("quit")) {
                message = reader.readLine();
                outToServer.writeUTF(message);
            }
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String[] arg) throws IOException {
        new Client3();
    }

}
