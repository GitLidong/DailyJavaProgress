package com.lidong.threaddemo.producer_consumer.publicInte;

public abstract class AbstractProducer implements Runnable {

	protected abstract void produce() throws InterruptedException;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				produce();
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
				break;
			}
		}
	}

}
