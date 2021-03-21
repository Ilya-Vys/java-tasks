package com.example.lec10.client;

import java.io.*;
import java.net.Socket;


public class Client3 {
    private final Socket socket;
    private final DataOutputStream dataOutput;
    private final DataInputStream dataInput;


    private Client3() throws IOException {
        this.socket = new Socket("127.0.0.1", 8000);
        this.dataOutput = new DataOutputStream(socket.getOutputStream());
        this.dataInput = new DataInputStream(socket.getInputStream());
        listenServer();
    }

    private void listenServer() {
        new Thread(() -> {
            while (!socket.isClosed()) {
                try {
                    System.out.println(dataInput.readUTF());
                } catch (IOException e) {
                    System.out.println("You left chat");
                }
            }
        }).start();
    }

    public static void main(String[] arg) throws IOException {
        new Client3().startChatting();
    }

    private void startChatting() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            assignName(reader);
            sendMessage(reader);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void assignName(BufferedReader reader) throws IOException {
        System.out.println("To send private message write: to name: message");
        System.out.print("Enter you name: ");
        dataOutput.writeUTF(reader.readLine());
    }

    private void sendMessage(BufferedReader reader) throws IOException {
        String message = "";
        while (!message.equalsIgnoreCase("quit")) {
            message = reader.readLine();
            dataOutput.writeUTF(message);
        }
        socket.close();
    }
}
