package com.lidong.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * @author LiDong
 * 
 *         用Java的套接字编程实现一个多线程的回显（echo）服务器
 *
 */

public class EchoServer {

	private static final int ECHO_SERVER_PORT = 6789;

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(ECHO_SERVER_PORT)) {
			int i = 1;
			System.out.println("服务器已经启动...");
			while (true) {
				Socket client = server.accept();
				System.out.println("链接：  " + i);
				new Thread(new ClientHandler(client)).start();
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ClientHandler implements Runnable {

	private Socket client;

	public ClientHandler(Socket client) {
		// TODO Auto-generated constructor stub
		this.client = client;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try (InputStream inStream = client.getInputStream(); OutputStream outStream = client.getOutputStream()) {

			Scanner scanner = new Scanner(inStream, "UTF-8");
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
			pw.println("Hello! Enter BYE to exit");
			boolean done = false;
			while (!done && scanner.hasNextLine()) {
				String line = scanner.nextLine();
				pw.println("Echo: " + line);
				if (line.trim().equals("BYE")) {
					done = true;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
