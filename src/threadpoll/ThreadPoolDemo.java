package threadpoll;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 如果并发的线程数量很多，并且每个线程都是执行一个时间很短的任务就结束了，这样频繁创建线程就会大大降低系统的效率，因为频繁创建线程和销毁线程需要时间。
 * 
 * corePoolSize：核心池的大小。
 * 		在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务。
 * 		除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可以看出，是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。
 * 		默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
 * 		当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
 * 
 * maximumPoolSize：线程池最大线程数，
 * 		这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；
 * 
 * keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。
 * 		默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，直到线程池中的线程数不大于corePoolSize
 * 		即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。
 * 		但是如果调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0；
 * 
 * unit：参数keepAliveTime的时间单位.
 * 		有7种取值，在TimeUnit类中有7种静态属性
 * 		TimeUnit.DAYS;               //天
 *		TimeUnit.HOURS;             //小时
 *		TimeUnit.MINUTES;           //分钟
 *		TimeUnit.SECONDS;           //秒
 *		TimeUnit.MILLISECONDS;      //毫秒
 *		TimeUnit.MICROSECONDS;      //微秒
 *		TimeUnit.NANOSECONDS;       //纳秒
 *
 * workQueue:一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响，一般来说，这里的阻塞队列有以下几种选择：
 * 		ArrayBlockingQueue
 * 		LinkedBlockingQueue
 * 		SynchronousQueue
 * 
 * 		ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。线程池的排队策略与BlockingQueue有关。
 * 		
 * handler：表示当拒绝处理任务时的策略,有以下四种取值：
 *		ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。 
 *		ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 
 *		ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 *		ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务 
 */


/*
 * ThreadPoolExecutor、AbstractExecutorService、ExecutorService和Executor几个之间的关系了。
 * 
　　Executor是一个顶层接口，在它里面只声明了一个方法execute(Runnable)，返回值为void，参数为Runnable类型，从字面意思可以理解，就是用来执行传进去的任务的；

　　然后ExecutorService接口继承了Executor接口，并声明了一些方法：submit、invokeAll、invokeAny以及shutDown等；

　　抽象类AbstractExecutorService实现了ExecutorService接口，基本实现了ExecutorService中声明的所有方法；

　　然后ThreadPoolExecutor继承了类AbstractExecutorService。

	在ThreadPoolExecutor类中有几个非常重要的方法：
		execute(): execute()方法实际上是Executor中声明的方法，在ThreadPoolExecutor进行了具体的实现，
					这个方法是ThreadPoolExecutor的核心方法，通过这个方法可以向线程池提交一个任务，交由线程池去执行。
					
		submit():方法是在ExecutorService中声明的方法，在AbstractExecutorService就已经有了具体的实现，在ThreadPoolExecutor中并没有对其进行重写，
				这个方法也是用来向线程池提交任务的，但是它和execute()方法不同，它能够返回任务执行的结果，
				去看submit()方法的实现，会发现它实际上还是调用的execute()方法，只不过它利用了Future来获取任务执行结果
				
		shutdown():关闭线程池的。
		shutdownNow():关闭线程池的。

 */

/*
 * 不过在java doc中，并不提倡我们直接使用ThreadPoolExecutor，
 * 而是使用Executors类中提供的几个静态方法来创建线程池：
 * 
 * 	Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
 *	Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
 *	Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池
 * 
 */

/*
 * 如何合理配置线程池大小，仅供参考。
 * 
 * 　　一般需要根据任务的类型来配置线程池大小：
 * 
 * 　　如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1
 * 
 * 　　如果是IO密集型任务，参考值可以设置为2*NCPU
 * 
 */

public class ThreadPoolDemo {
	
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

/*
 *线程池实现原理
 *
 *1.线程池状态
 *	在ThreadPoolExecutor中定义了一个volatile变量，另外定义了几个static final变量表示线程池的各个状态：
 *		volatile int runState;	表示当前线程池的状态，它是一个volatile变量用来保证线程之间的可见性；
 *
 *		static final int RUNNING    = 0;   当创建线程池后，初始时，线程池处于RUNNING状态；
 *
 *		static final int SHUTDOWN   = 1;	如果调用了shutdown()方法，则线程池处于SHUTDOWN状态，此时线程池不能够接受新的任务，它会等待所有任务执行完毕；
 *
 *		static final int STOP       = 2;	如果调用了shutdownNow()方法，则线程池处于STOP状态，此时线程池不能接受新的任务，并且会去尝试终止正在执行的任务；
 *
 *		static final int TERMINATED = 3;	当线程池处于SHUTDOWN或STOP状态，并且所有工作线程已经销毁，任务缓存队列已经清空或执行结束后，线程池被设置为TERMINATED状态。
 *
 *2.任务的执行
 *	private final BlockingQueue<Runnable> workQueue;              //任务缓存队列，用来存放等待执行的任务
 *	private final ReentrantLock mainLock = new ReentrantLock();   //线程池的主要状态锁，对线程池状态（比如线程池大小
                                                              //、runState等）的改变都要使用这个锁
 *	private final HashSet<Worker> workers = new HashSet<Worker>();  //用来存放工作集
 
 *	private volatile long  keepAliveTime;    //线程存货时间   
 *	private volatile boolean allowCoreThreadTimeOut;   //是否允许为核心线程设置存活时间
 *	private volatile int   corePoolSize;     //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
 *	private volatile int   maximumPoolSize;   //线程池最大能容忍的线程数
 
 *	private volatile int   poolSize;       //线程池中当前的线程数
 
 *	private volatile RejectedExecutionHandler handler; //任务拒绝策略
 
 *	private volatile ThreadFactory threadFactory;   //线程工厂，用来创建线程
 
 *	private int largestPoolSize;   //用来记录线程池中曾经出现过的最大线程数
 
 *	private long completedTaskCount;   //用来记录已经执行完毕的任务个数
	
 * 
 * 3.线程池中的线程初始化
 * 默认情况下，创建线程池之后，线程池中是没有线程的，需要提交任务之后才会创建线程。
 *
 *　　在实际中如果需要线程池创建之后立即创建线程，可以通过以下两个方法办到：
 *
 *		prestartCoreThread()：初始化一个核心线程；
 *		prestartAllCoreThreads()：初始化所有核心线程
 *
 *
 * 4.任务缓存队列及排队策略　
 * 	在前面我们多次提到了任务缓存队列，即workQueue，它用来存放等待执行的任务。
 *
 *　　workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：
 *
 *　　1）ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；
 *
 *　　2）LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；
 *
 *　　3）synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
 *
 *5.任务拒绝策略
 *　　当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略，通常有以下四种策略：
 *		ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 *		ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
 *		ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 *		ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
 *
 *6.线程池的关闭
 *　ThreadPoolExecutor提供了两个方法，用于线程池的关闭，分别是shutdown()和shutdownNow()，其中：
 *		shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
 *		shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
 *
 *7.线程池容量的动态调整
 *　	ThreadPoolExecutor提供了动态调整线程池容量大小的方法：setCorePoolSize()和setMaximumPoolSize()，
 *		setCorePoolSize：设置核心池大小
 *		setMaximumPoolSize：设置线程池最大能创建的线程数目大小
 *　		当上述参数从小变大时，ThreadPoolExecutor进行线程赋值，还可能立即创建新的线程来执行任务。
 */