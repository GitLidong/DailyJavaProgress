package com.lidong.io_demo.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringReader;

public class BufferedInputFile {

	public static String fileName = "src/com/lidong/io_demo/io/BufferedInputFile.java";
	public static String aimFileName = "resource/BufferedWriter.txt";
	public static String aimFileName2 = "resource/DataOutputStream.txt";
	public static String aimFileName3 = "resource/RandomAccessFile.txt";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// System.out.println(readFromFile(fileName));
		// readFromRAM();

		// writeToFile();
		// dataOutputInputStream();
		useRandomAccessFile();
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 *             缓冲输入文件：如果要打开文件作为字符输入，可以用FileInputReader。 为了提高速度，可以对这个文件实行缓冲，用
	 *             BufferedReader readLine()方法
	 */
	public static String readFromFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String temp;
		StringBuilder sb = new StringBuilder();
		while ((temp = br.readLine()) != null) {
			sb.append(temp + "\n");
		}
		br.close();
		return sb.toString();
	}

	/**
	 * 
	 * @throws IOException
	 *             StringReader 从内存输入
	 */

	public static void readFromRAM() throws IOException {
		StringReader sr = new StringReader(readFromFile(fileName));
		int c;
		while ((c = sr.read()) != -1) {
			System.out.println((char) c);
		}
	}

	/**
	 * 
	 * @throws IOException
	 * 
	 *             利用 BufferedWriter 向目标写入内容 它被装饰成 PrintWriter
	 */

	public static void writeToFile() throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(
				readFromFile(fileName)));
		PrintWriter pw = new PrintWriter(aimFileName);
		int lineCount = 1;
		String temp;
		while ((temp = br.readLine()) != null) {
			pw.println(lineCount++ + " : " + temp);
		}

		pw.close();

		System.out.println(readFromFile(aimFileName));
	}

	public static void dataOutputInputStream() throws IOException {
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream(aimFileName2)));
		dos.writeDouble(3.1415926);
		dos.writeUTF("That was PI");
		dos.close();

		DataInputStream dis = new DataInputStream(new BufferedInputStream(
				new FileInputStream(aimFileName2)));
		System.out.println(dis.readDouble());
		System.out.println(dis.readUTF());
	}

	public static void useRandomAccessFile() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(aimFileName3, "rw");
		for (int i = 0; i < 7; i++) {
			raf.writeDouble(i * 3.14);
		}
		raf.writeUTF("End of the file");

		raf.close();

	    raf = new RandomAccessFile(aimFileName3, "r");
		for (int i = 0; i < 7; i++) {
			System.out.println("value : " + raf.readDouble());
		}
		System.out.println(raf.readUTF());
		raf.close();
		
		raf = new RandomAccessFile(aimFileName3, "rw");
		raf.seek(5*8);
		raf.writeDouble(47.00001);
		raf.close();
		
	    raf = new RandomAccessFile(aimFileName3, "r");
		for (int i = 0; i < 7; i++) {
			System.out.println("value : " + raf.readDouble());
		}
		System.out.println(raf.readUTF());
		raf.close();
	}
}
