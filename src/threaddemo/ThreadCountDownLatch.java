package threaddemo;

import java.util.concurrent.CountDownLatch;

/*
 * 假设一条流水线上有三个工作者：worker0，worker1，worker2。
 * 有一个任务的完成需要他们三者协作完成，worker2 可以开始这个任务的前提是 worker0 和 worker1 完成了他们的工作，
 * 而worker0和worker1是可以并行他们各自的工作的
 */

/*
 * 创建了一个计数器为2的 CountDownLatch ，让Worker持有这个CountDownLatch 实例，当完成自己的工作后，调用countDownLatch.countDown() 方法将计数器减1。
 * countDownLatch.await() 方法会一直阻塞直到计数器为0，主线程才会继续往下执行。
 * 
 */
public class ThreadCountDownLatch {
	
	public static void main(String[] args) {
		
		CountDownLatch countDownLatch = new CountDownLatch(2);
		
		WorkerWithCountDownLatch worker0 = new WorkerWithCountDownLatch("Worker0", (long)(Math.random()*2000+3000),countDownLatch);
		WorkerWithCountDownLatch worker1 = new WorkerWithCountDownLatch("Worker1", (long)(Math.random()*2000+3000),countDownLatch);
		WorkerWithCountDownLatch worker2 = new WorkerWithCountDownLatch("Worker2", (long)(Math.random()*2000+3000),countDownLatch);
		
		worker0.start();
		worker1.start();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("准备工作就绪");  
		worker2.start();
		
	}
}
