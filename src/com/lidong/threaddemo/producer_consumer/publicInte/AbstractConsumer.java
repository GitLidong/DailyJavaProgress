package com.lidong.threaddemo.producer_consumer.publicInte;

public abstract class AbstractConsumer implements Runnable {

	protected abstract void consume() throws InterruptedException;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				consume();
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
				break;
			}
		}
	}

}
