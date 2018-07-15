package synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 * 
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 
 * CyclicBarrier还提供一个更高级的构造函数CyclicBarrier(int parties, Runnable barrierAction)，
 * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
 * 
 * 
 * CyclicBarrier和CountDownLatch的区别:
 * 1,CountDownLatch的计数器只能使用一次。而CyclicBarrier的计数器可以使用reset() 方法重置。
 * 	所以CyclicBarrier能处理更为复杂的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。
 * 2,CyclicBarrier还提供其他有用的方法，
 * 比如getNumberWaiting方法可以获得CyclicBarrier阻塞的线程数量。
 * isBroken方法用来知道阻塞的线程是否被中断。
 */

/*
 * CyclicBarrier与CountDownLatch比较

　　1）CountDownLatch:一个线程(或者多个)，等待另外N个线程完成某个事情之后才能执行；
	 CyclicBarrier:N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。

　　2）CountDownLatch:一次性的；CyclicBarrier:可以重复使用。

　　3）CountDownLatch基于AQS；CyclicBarrier基于锁和Condition。本质上都是依赖于volatile和CAS实现的。
 */


/*
 * 假若有若干个线程都要进行写数据操作，并且只有所有线程都完成写数据操作之后，
 * 这些线程才能继续做后面的事情，此时就可以利用CyclicBarrier了：
 * 
 */

 /*
  * 当前线程等待直到所有线程都调用了该屏障的await()方法
  * 如果当前线程不是将到达的最后一个线程，将会被阻塞。解除阻塞的情况有以下几种
  *  1）最后一个线程调用await()
  *  2）当前线程被中断
  *  3）其他正在该CyclicBarrier上等待的线程被中断
  *  4）其他正在该CyclicBarrier上等待的线程超时
  *  5）其他某个线程调用该CyclicBarrier的reset()方法
  * 如果当前线程在进入此方法时已经设置了该线程的中断状态或者在等待时被中断，将抛出InterruptedException，并且清除当前线程的已中断状态。
  * 如果在线程处于等待状态时barrier被reset()或者在调用await()时 barrier 被损坏，将抛出 BrokenBarrierException 异常。
  * 如果任何线程在等待时被中断，则其他所有等待线程都将抛出 BrokenBarrierException 异常，并将 barrier 置于损坏状态。 
  * 如果当前线程是最后一个将要到达的线程，并且构造方法中提供了一个非空的屏障操作（barrierAction），那么在允许其他线程继续运行之前，
  * 当前线程将运行该操作。如果在执行屏障操作过程中发生异常，则该异常将传播到当前线程中，并将 barrier 置于损坏状态。
  *
  * 返回值为当前线程的索引，0表示当前线程是最后一个到达的线程
  */

/*
 * 　对于失败的同步尝试，CyclicBarrier 使用了一种要么全部要么全不 (all-or-none) 的破坏模式：
 * 如果因为中断、失败或者超时等原因，导致线程过早地离开了屏障点，
 * 那么在该屏障点等待的其他所有线程也将通过 BrokenBarrierException（如果它们几乎同时被中断，则用 InterruptedException）
 * 以反常的方式离开。
 */

public class CyclicBarrierDemo {
	public static void main(String[] args) {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N);
		for (int i = 0; i < N; i++)
			new Writer(barrier).start();
	}
}

class Writer extends Thread {
	private CyclicBarrier cyclicBarrier;

	public Writer(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
		try {
			Thread.sleep(5000); // 以睡眠来模拟写入数据操作
			System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("所有线程写入完毕，继续处理其他任务...");
	}
}