package com.lidong.io_demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author LiDong
 *
 *         写一个方法，输入一个文件名和一个字符串，统计这个字符串在这个文件中出现的次数。
 */

public class WordsCountDemo {

	public static void main(String[] args) throws IOException {
		System.out.println(wordsCount("resource/hello.txt", "to"));

		listFile("/mnt/work2/LiDongPoj/JavaSpace/DailyJavaProgress");
	}

	public static int wordsCount(String fileName, String aim) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line;
		StringBuilder sb = new StringBuilder();
		int count = 0;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		int begin;

		while ((begin = sb.indexOf(aim)) != -1) {
			sb = sb.delete(begin, begin + aim.length());
			count++;
		}

		return count;
	}

	public static void listFile(String dic) {
		fileCount(new File(dic), 0);
	}

	public static void fileCount(File f, int level) {

		if (f.isDirectory()) {
			for (File temp : f.listFiles()) {
				fileCount(temp, level + 1);
			}
		} else {
			for (int i = 0; i < level - 1; i++) {
				System.out.print("\t");
			}
			System.out.println(f.getAbsolutePath());
		}
	}

}
