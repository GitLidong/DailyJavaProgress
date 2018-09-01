package com.lidong.threaddemo.producer_consumer.publicInte;

public abstract class AbstractConsumer implements Consumer, Runnable {

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
