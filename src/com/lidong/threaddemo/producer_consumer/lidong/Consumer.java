package com.lidong.threaddemo.producer_consumer.lidong;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable{

    private final Vector sharedQueue;
    private final int SIZE;
	
    public Consumer(Vector sharedQueue, int size) {  
        this.sharedQueue = sharedQueue;  
        this.SIZE = size;  
    }  
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true) {
            try {  
                System.out.println("Consumer: " + consume());  
                Thread.sleep(50);  
            } catch (InterruptedException ex) {  
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);  
            }  
		}
	}
	
	private int consume() throws InterruptedException {
		
		while(sharedQueue.isEmpty()) {
			synchronized (sharedQueue) {
                System.out.println("Queue is empty " + Thread.currentThread().getName()  
                        + " is waiting , size: " + sharedQueue.size());
                
                sharedQueue.wait();
			}
		}

		synchronized (sharedQueue) {
			sharedQueue.notifyAll();
			System.out.println("sharedQueue remove: ");
			return (int) sharedQueue.remove(0);
		}
	}
	
}
