package com.lidong.threaddemo.producer_consumer.ByWaitNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import com.lidong.threaddemo.producer_consumer.publicInte.AbstractConsumer;
import com.lidong.threaddemo.producer_consumer.publicInte.AbstractProducer;
import com.lidong.threaddemo.producer_consumer.publicInte.Model;
import com.lidong.threaddemo.producer_consumer.publicInte.Task;

public class WaitNotifyModel implements Model {

	private final Object BUFFER_LOCK = new Object();
	private final Queue<Task> buffer = new LinkedList<>();
	private final int cap;
	private final AtomicInteger increTaskNo = new AtomicInteger(0);

	public WaitNotifyModel(int cap) {
		// TODO Auto-generated constructor stub
		this.cap = cap;
	}

	@Override
	public Runnable newRunnableConsumer() {
		// TODO Auto-generated method stub
		return new ConsumerImpl();
	}

	@Override
	public Runnable newRunnableProducer() {
		// TODO Auto-generated method stub
		return new ProduceImpl();
	}

	class ConsumerImpl extends AbstractConsumer {

		@Override
		public void consume() throws InterruptedException {
			// TODO Auto-generated method stub
			synchronized (BUFFER_LOCK) {
				while (buffer.size() == 0) {
					BUFFER_LOCK.wait();
				}

				Task task = buffer.poll();
				assert task != null;
				Thread.sleep(500 + (long) Math.random() * 500);
				System.out.println("consume: " + task.no);
				BUFFER_LOCK.notifyAll();
			}
		}

	}

	class ProduceImpl extends AbstractProducer {

		@Override
		public void produce() throws InterruptedException {
			// TODO Auto-generated method stub
			Thread.sleep((long) Math.random() * 1000);
			synchronized (BUFFER_LOCK) {
				while (buffer.size() == cap) {
					BUFFER_LOCK.wait();
				}

				Task task = new Task(increTaskNo.getAndIncrement());
				buffer.offer(task);
				System.out.println("produce: " + task.no);
				BUFFER_LOCK.notifyAll();
			}
		}

	}

	public static void main(String[] args) {
		Model mode = new WaitNotifyModel(3);
		for (int i = 0; i < 2; i++) {
			new Thread(mode.newRunnableConsumer()).start();
		}
		for (int i = 0; i < 5; i++) {
			new Thread(mode.newRunnableProducer()).start();
		}
	}

}
