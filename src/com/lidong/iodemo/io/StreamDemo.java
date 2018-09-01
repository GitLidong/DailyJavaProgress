package com.lidong.iodemo.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StreamDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(Paths.get("resource/BufferedInputFile.txt")));

		List<String> words = Arrays.asList(contents.split("\\PL+"));

		long count = 0;
		for (String temp : words) {
			if (temp.length() > 12) {
				count++;
			}
		}

		System.out.println(count);

		count = words.stream().filter(a -> a.length() > 12).count();
		System.out.println(count);

		count = words.parallelStream().filter(a -> a.length() > 12).count();
		System.out.println(count);

	}

}
