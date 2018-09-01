package com.lidong.threaddemo.producer_consumer.ByLockConditionModelBetter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.lidong.threaddemo.producer_consumer.publicInte.AbstractConsumer;
import com.lidong.threaddemo.producer_consumer.publicInte.AbstractProducer;
import com.lidong.threaddemo.producer_consumer.publicInte.Model;
import com.lidong.threaddemo.producer_consumer.publicInte.Task;

public class ByLockConditionModelBetter implements Model {

	private final Lock CONSUME_LOCK = new ReentrantLock();
	private final Condition NOT_EMPTY = CONSUME_LOCK.newCondition();
	private final Lock PRODUCE_LOCK = new ReentrantLock();
	private final Condition NOT_FULL = PRODUCE_LOCK.newCondition();
	private final Buffer<Task> buffer = new Buffer<>();
	private AtomicInteger bufLen = new AtomicInteger(0);
	private final int cap;
	private final AtomicInteger increTaskNo = new AtomicInteger(0);

	public ByLockConditionModelBetter(int cap) {
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
		return new ProducerImpl();
	}

	private class ConsumerImpl extends AbstractConsumer {
		@Override
		public void consume() throws InterruptedException {
			int newBufSize = -1;
			Task task;
			CONSUME_LOCK.lockInterruptibly();
			try {
				while (bufLen.get() == 0) {
					System.out.println("buffer is empty...");
					NOT_EMPTY.await();
				}
				task = buffer.poll();
				newBufSize = bufLen.decrementAndGet();
				if (newBufSize > 0) {
					NOT_EMPTY.signalAll();
				}
			} finally {
				CONSUME_LOCK.unlock();
			}
			if (newBufSize < cap) {
				PRODUCE_LOCK.lockInterruptibly();
				try {
					NOT_FULL.signalAll();
				} finally {
					PRODUCE_LOCK.unlock();
				}
			}

			assert task != null;
			Thread.sleep(500 + (long) (Math.random() * 500));
			System.out.println("consume: " + task.no);
		}
	}

	private class ProducerImpl extends AbstractProducer {
		@Override
		public void produce() throws InterruptedException {
			Thread.sleep((long) (Math.random() * 1000));
			int newBufSize = -1;
			PRODUCE_LOCK.lockInterruptibly();
			try {
				while (bufLen.get() == cap) {
					System.out.println("buffer is full...");
					NOT_FULL.await();
				}
				Task task = new Task(increTaskNo.getAndIncrement());
				buffer.offer(task);
				newBufSize = bufLen.incrementAndGet();
				System.out.println("produce: " + task.no);
				if (newBufSize < cap) {
					NOT_FULL.signalAll();
				}
			} finally {
				PRODUCE_LOCK.unlock();
			}
			if (newBufSize > 0) {
				CONSUME_LOCK.lockInterruptibly();
				try {
					NOT_EMPTY.signalAll();
				} finally {
					CONSUME_LOCK.unlock();
				}
			}
		}
	}

	private static class Buffer<E> {
		private Node head;
		private Node tail;

		Buffer() {
			// dummy node
			head = tail = new Node(null);
		}

		public void offer(E e) {
			tail.next = new Node(e);
			tail = tail.next;
		}

		public E poll() {
			head = head.next;
			E e = head.item;
			head.item = null;
			return e;
		}

		private class Node {
			E item;
			Node next;

			Node(E item) {
				this.item = item;
			}
		}
	}

	public static void main(String[] args) {
		Model mode = new ByLockConditionModelBetter(3);
		for (int i = 0; i < 2; i++) {
			new Thread(mode.newRunnableConsumer()).start();
		}
		for (int i = 0; i < 5; i++) {
			new Thread(mode.newRunnableProducer()).start();
		}
	}

}
