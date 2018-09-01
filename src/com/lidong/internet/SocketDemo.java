package com.lidong.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EchoServer();
	}

	private static void socket1() {

		try (Socket socket = new Socket("time-a.nist.gov", 13);
				Scanner scanner = new Scanner(socket.getInputStream(), "UTF-8")) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(line);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void EchoServer() {

		try (ServerSocket s = new ServerSocket(8189)) {

			int i = 1;
			while (true) {
				Socket incoming = s.accept();
				System.out.println("Spawning " + i);
				Runnable r = new ThreadEchoHandler(incoming);
				Thread thread = new Thread(r);
				thread.start();
				i++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
