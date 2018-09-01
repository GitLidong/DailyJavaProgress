package com.lidong.threaddemo.producer_consumer.ByLockConditionModel;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.lidong.threaddemo.producer_consumer.publicInte.AbstractConsumer;
import com.lidong.threaddemo.producer_consumer.publicInte.AbstractProducer;
import com.lidong.threaddemo.producer_consumer.publicInte.Model;
import com.lidong.threaddemo.producer_consumer.publicInte.Task;

public class LockConditionModel implements Model {

	private final Lock BUFFER_LOCK = new ReentrantLock();
	private final Condition BUFFER_COND = BUFFER_LOCK.newCondition();
	private final Queue<Task> buffer = new LinkedList<>();
	private final int cap;
	private final AtomicInteger increTaskNo = new AtomicInteger(0);

	public LockConditionModel(int cap) {
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
			BUFFER_LOCK.lockInterruptibly();
			try {
				while (buffer.size() == 0) {
					BUFFER_COND.await();
				}

				Task task = buffer.poll();
				assert task != null;
				Thread.sleep(500 + (long) (Math.random() * 500));
				System.out.println("consume: " + task.no);
				BUFFER_COND.signalAll();

			} finally {
				// TODO: handle finally clause
				BUFFER_LOCK.unlock();
			}
		}

	}

	class ProduceImpl extends AbstractProducer {

		@Override
		public void produce() throws InterruptedException {
			// TODO Auto-generated method stub
			BUFFER_LOCK.lockInterruptibly();
			try {
				while (buffer.size() == cap) {
					BUFFER_COND.await();
				}

				Task task = new Task(increTaskNo.getAndIncrement());
				buffer.offer(task);
				System.out.println("produce: " + task.no);
				BUFFER_COND.signalAll();

			} finally {
				// TODO: handle finally clause
				BUFFER_LOCK.unlock();
			}

		}

	}

	public static void main(String[] args) {
		Model mode = new LockConditionModel(3);
		for (int i = 0; i < 2; i++) {
			new Thread(mode.newRunnableConsumer()).start();
		}
		for (int i = 0; i < 5; i++) {
			new Thread(mode.newRunnableProducer()).start();
		}
	}

}
