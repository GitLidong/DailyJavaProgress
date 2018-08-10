package com.lidong.threaddemo.synchronizer;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DelayQueue<DelayTask> queue = new DelayQueue<>();
		queue.add(new DelayTask("1", 1000L, TimeUnit.MILLISECONDS));
		queue.add(new DelayTask("2", 2000L, TimeUnit.MILLISECONDS));
		queue.add(new DelayTask("3", 3000L, TimeUnit.MILLISECONDS));

		System.out.println("queue put done");

		while (!queue.isEmpty()) {
			try {
				DelayTask task = queue.take();
				System.out
						.println(task.name + ":" + System.currentTimeMillis());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class DelayTask implements Delayed {

	public String name;
	public Long delayTime;
	public TimeUnit delayTimeUnit;
	public Long executeTime;// ms

	public DelayTask(String name, long delayTime, TimeUnit delayTimeUnit) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.delayTime = delayTime;
		this.delayTimeUnit = delayTimeUnit;
		this.executeTime = System.currentTimeMillis()
				+ delayTimeUnit.toMillis(delayTime);
	}

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		if (getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
			return 1;
		} else if (this.getDelay(TimeUnit.MILLISECONDS) < o
				.getDelay(TimeUnit.MILLISECONDS)) {
			return -1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return unit.convert(executeTime - System.currentTimeMillis(),
				TimeUnit.MILLISECONDS);
	}

}
