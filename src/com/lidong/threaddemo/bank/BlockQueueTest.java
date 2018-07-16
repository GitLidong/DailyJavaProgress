package com.lidong.threaddemo.bank;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter base dic: ");
		String dic = scanner.nextLine();
		System.out.print("enter key words: ");
		String key = scanner.nextLine();
		
		int queue_size = 10;
		int search_thread = 100;
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<>(queue_size);
		
	}
	
}

class FileEnumertionTask implements Runnable {
	
	private BlockingQueue<File> queue;
	private File target;
	
	public FileEnumertionTask(BlockingQueue<File> queue, File target) {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void enumerate(File dic) {
		for (File file : dic.listFiles()) {
			
		}
	}
}

class SearchTask implements Runnable {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

