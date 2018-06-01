package threadpoll;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
	
	
	public static void main(String[] args) {
		int num = 10;
		CountDownLatch doneSignal = new CountDownLatch(num);
		ExecutorService poolCache = Executors.newCachedThreadPool();
		ExecutorService poolFixed = Executors.newFixedThreadPool(num);
		ExecutorService poolSchedule = Executors.newScheduledThreadPool(num);
		ExecutorService poolSingle = Executors.newSingleThreadExecutor();
		for (int i = 0 ; i< num; i++) {
			WorkRunnable temp = new WorkRunnable(doneSignal, i);
			//poolCache.execute(temp);
			poolSingle.execute(temp);
		}
		
		try {
			doneSignal.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("所有任务执行完毕");
	}
	
	
	static class WorkRunnable implements Runnable {
		
		private CountDownLatch doneSignal;
		private int i;
		
		public WorkRunnable(CountDownLatch doneSignal,int i) {
			// TODO Auto-generated constructor stub
			this.doneSignal = doneSignal;
			this.i = i;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			doWork(i);
			
			doneSignal.countDown();
			System.out.println("countDownLatch.getCount()="+doneSignal.getCount());
			
		}
		
		private void doWork(int i) {
			System.out.println("这是第"+(i+1)+"个任务");
		}
	}

}
