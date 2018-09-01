package com.lidong.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadEchoHandler implements Runnable {

	private Socket incoming;

	public ThreadEchoHandler(Socket inComing) {
		// TODO Auto-generated constructor stub
		this.incoming = inComing;
	}

	@Override
	public void run() {

		try (InputStream inStream = incoming.getInputStream(); OutputStream outStream = incoming.getOutputStream();) {
			Scanner in = new Scanner(inStream, "UTF-8");
			PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
			out.println("Hello ! Enter BYE to EXIT.");
			boolean done = false;
			while (!done && in.hasNextLine()) {
				String line = in.nextLine();
				out.println("Echo: " + line);
				if (line.trim().equals("BYE")) {
					done = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
