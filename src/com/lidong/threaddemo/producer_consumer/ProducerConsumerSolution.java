package com.lidong.threaddemo.producer_consumer;

import java.util.Vector;

public class ProducerConsumerSolution {

	public static void main(String[] args) {
		
		Vector sharedQueue = new Vector();
		int size = 4;
		
		Producer producer = new Producer(sharedQueue, size);
		Consumer consumer = new Consumer(sharedQueue, size);
		
		Thread pThread = new Thread(producer,"Producer");
		Thread cThread = new Thread(consumer,"Consumer");
		
		pThread.start();
		cThread.start();
	}
	
}
