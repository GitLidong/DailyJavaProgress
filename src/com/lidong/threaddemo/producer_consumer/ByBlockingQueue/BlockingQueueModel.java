package com.lidong.threaddemo.producer_consumer.ByBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.lidong.threaddemo.producer_consumer.publicInte.AbstractConsumer;
import com.lidong.threaddemo.producer_consumer.publicInte.AbstractProducer;
import com.lidong.threaddemo.producer_consumer.publicInte.Model;
import com.lidong.threaddemo.producer_consumer.publicInte.Task;

public class BlockingQueueModel implements Model {

	private final BlockingQueue<Task> queue;
	private final AtomicInteger increTaskNo = new AtomicInteger(0);

	public BlockingQueueModel(int cap) {
		// TODO Auto-generated constructor stub
		this.queue = new LinkedBlockingQueue<>(cap);
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
			Task task = queue.take();
			// 固定时间范围的消费，模拟相对稳定的服务器处理过程
			Thread.sleep(500 + (long) Math.random() * 500);
			System.out.println("consume: " + task.no);
		}

	}

	class ProduceImpl extends AbstractProducer {

		@Override
		public void produce() throws InterruptedException {
			// TODO Auto-generated method stub
			// 不定期生产，模拟随机的用户请求
			Thread.sleep((long) Math.random() * 1000);
			Task task = new Task(increTaskNo.getAndIncrement());
			queue.put(task);
			System.out.println("produce: " + task.no);
		}

	}

	public static void main(String[] args) {
		Model mode = new BlockingQueueModel(3);
		for (int i = 0; i < 2; i++) {
			new Thread(mode.newRunnableConsumer()).start();
		}
		for (int i = 0; i < 5; i++) {
			new Thread(mode.newRunnableProducer()).start();
		}
	}

}
