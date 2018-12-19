package com.lidong.io_demo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TextFile {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		readFromStandedIO();
	}

	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					fileName).getAbsoluteFile()));
			try {
				String temp;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
					sb.append("\n");
				}
			} finally {
				br.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static void wrtieToFile(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw); // 向文本输出流打印对象的格式化表示形式
		//pw.write(XXX);
		//pw.println(XXX);
		//pw.append(XXX);
	}

	public static void write(String fileName, String text) {
		try {
			PrintWriter pw = new PrintWriter(
					new File(fileName).getAbsoluteFile());

			try {
				pw.write(text);
			} finally {
				pw.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 读取二进制文件
	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				bFile));
		try {
			byte[] data = new byte[bis.available()];
			bis.read(data);
			return data;
		} finally {
			bis.close();
		}
	}

	// 从标准输入中读取
	public static void readFromStandedIO() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = br.readLine()) != null && s.length() != 0) {
			System.out.println(s);
		}
	}

	// 从输出到标准输出
	public static void writeToStandedIO(String text) {
		PrintWriter pw = new PrintWriter(System.out, true);
		pw.println(text);
	}
}
