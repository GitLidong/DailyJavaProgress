package threaddemo;

import java.util.concurrent.CountDownLatch;

/*
 * 假设worker的工作可以分为两个阶段，work2 只需要等待work0和work1完成他们各自工作的第一个阶段之后就可以开始自己的工作了，
 * 而不是场景1中的必须等待work0和work1把他们的工作全部完成之后才能开始。
 * 
 * 试想下，在这种情况下，join是没办法实现这个场景的，而CountDownLatch却可以，因为它持有一个计数器，只要计数器为0，那么主线程就可以结束阻塞往下执行。
 * 我们可以在worker0和worker1完成第一阶段工作之后就把计数器减1即可，这样worker0和worker1在完成第一阶段工作之后，worker2就可以开始工作了。
 */
public class ThreadWith2Steps {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根

		CountDownLatch countDownLatch = new CountDownLatch(2);
		Worker2Steps worker0 = new Worker2Steps("worker0", (long) (Math.random()*2000+3000), countDownLatch);
		Worker2Steps worker1 = new Worker2Steps("worker1", (long) (Math.random()*2000+3000), countDownLatch);
		Worker2Steps worker2 = new Worker2Steps("worker2", (long) (Math.random()*2000+3000), countDownLatch);
		
		worker0.start();
		worker1.start();	
		countDownLatch.await();
		
		System.out.println("准备工作就绪");
		worker2.start();
		
	}
	
	
}
