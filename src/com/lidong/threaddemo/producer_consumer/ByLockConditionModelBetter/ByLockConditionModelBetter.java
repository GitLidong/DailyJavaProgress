package com.lidong.threaddemo.producer_consumer.ByLockConditionModelBetter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.lidong.threaddemo.producer_consumer.publicInte.AbstractConsumer;
import com.lidong.threaddemo.producer_consumer.publicInte.AbstractProducer;
import com.lidong.threaddemo.producer_consumer.publicInte.Model;
import com.lidong.threaddemo.producer_consumer.publicInte.Task;

/**
 * 
 * @author Dong
 *
 *         WaitNotifyModel 和 LockConditionModel 效率明显低于实现一，并发瓶颈很明显， 因为在锁
 *         BUFFER_LOCK 看来，任何消费者线程与生产者线程都是一样的。
 *         换句话说，同一时刻，最多只允许有一个线程（生产者或消费者，二选一）操作缓冲区 buffer。
 *
 *         而实际上，如果缓冲区是一个队列的话， “生产者将产品入队”与“消费者将产品出队”两个操作之间没有同步关系，
 *         可以在队首出队的同时，在队尾入队。理想性能可提升至两倍。
 *
 *
 *         那么思路就简单了：需要两个锁 CONSUME_LOCK与PRODUCE_LOCK， CONSUME_LOCK控制消费者线程并发出队，
 *         PRODUCE_LOCK控制生产者线程并发入队； 相应需要两个条件变量NOT_EMPTY与NOT_FULL，
 *         NOT_EMPTY负责控制消费者线程的状态（阻塞、运行）， NOT_FULL负责控制生产者线程的状态（阻塞、运行）。
 *         以此让优化消费者与消费者（或生产者与生产者）之间是串行的；消费者与生产者之间是并行的。
 */

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
