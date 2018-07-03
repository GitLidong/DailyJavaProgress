package producer_consumer_demo;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable{

    private final Vector sharedQueue;
    private final int SIZE;
	
    public Producer(Vector sharedQueue, int size) {  
        this.sharedQueue = sharedQueue;  
        this.SIZE = size;  
    }  
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (int i = 0; i< 7; i++) {
            System.out.println("Produced:" + i);
            try {
				produce(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);  
			}
		}
		
	}
	
	private void produce(int i) throws InterruptedException {
        
		//wait if queue is full
		while(sharedQueue.size() == SIZE) {
			synchronized (sharedQueue) {
				System.out.println("Queue is full " + Thread.currentThread().getName()  
                        + " is waiting , size: " + sharedQueue.size());  
				sharedQueue.wait();
			}
		}

        //producing element and notify consumers
		synchronized (sharedQueue) {
			System.out.println("sharedQueue add: " + i);
			sharedQueue.add(i);
			sharedQueue.notifyAll();
		}
		
	}

}
