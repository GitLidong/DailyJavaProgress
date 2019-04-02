package com.lidong.socketdemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServerSocket {


    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {

        ExecutorService pool = Executors.newFixedThreadPool(3);
        try {
            ServerSocket serverSocket = new ServerSocket(10086);
            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new VisitTask(socket));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class VisitTask implements Runnable {

    Socket server;

    public VisitTask(Socket server) {
        this.server = server;
    }

    @Override
    public void run() {
        System.out.println("new Visitor comes , welcome");
        try {
            OutputStream outputStream = server.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            pw.write("Thank you for you visit");
            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
