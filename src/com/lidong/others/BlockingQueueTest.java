package com.lidong.others;

/*
 * 设置1个线程用来往阻塞队列中不断的添加文件
 * 设置10个线程用来不断取出阻塞队列中的文件，并在该文件中查找相应的关键字并输出。
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base dic: ");
		String dic = in.nextLine();
		System.out.print("Enter keyWord: ");
		String key = in.nextLine();

		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THREADS = 10;

		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
		FileEnumQueueTask enumerator = new FileEnumQueueTask(queue, new File(dic));
		new Thread(enumerator).start();// 一个线程用来往阻塞队列中添加文件
		for (int i = 1; i <= SEARCH_THREADS; i++) {
			new Thread(new SearchTask(queue, key)).start();// 10个线程用于搜索阻塞队列中文件中待查找的字符
		}
	}
}

class FileEnumQueueTask implements Runnable {
	private BlockingQueue<File> queue;
	private File startingDic;
	public static File DUMMY = new File("");

	public FileEnumQueueTask(BlockingQueue<File> queue, File startingDic) {
		this.queue = queue;
		this.startingDic = startingDic;
	}

	@Override
	public void run() {
		try {
			enumerate(startingDic);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
		}
	}

	public void enumerate(File dic) throws InterruptedException {
		File[] files = dic.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				enumerate(file);
			} else {
				queue.put(file);
			}
		}
	}
}

class SearchTask implements Runnable {
	private BlockingQueue<File> queue;
	private String keyWords;

	public SearchTask(BlockingQueue<File> queue, String keyWords) {
		this.queue = queue;
		this.keyWords = keyWords;
	}

	public void run() {
		try {
			boolean done = false;
			while (!done) {
				File file = queue.take();
				if (file == FileEnumQueueTask.DUMMY) {
					queue.put(file);
					done = true;
				} else {
					search(file);
				}
			}
			System.out.println("Current thread: " + Thread.currentThread().getName() + " search over!!!!!!!!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
		}
	}

	public void search(File file) throws IOException {
		Scanner in = new Scanner(new FileInputStream(file));
		int lineNumber = 0;
		while (in.hasNextLine()) {
			lineNumber++;
			String line = in.nextLine();
			if (line.contains(keyWords)) {
				System.out.println(
						"Current thread: " + Thread.currentThread().getName() + ", search keywords: " + keyWords
								+ ", in file: " + file.getAbsolutePath() + ", line: " + lineNumber + ", line: " + line);
			}
		}
		in.close();
	}

}