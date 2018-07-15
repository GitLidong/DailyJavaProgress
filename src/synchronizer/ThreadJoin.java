package synchronizer;

/*
 * 假设一条流水线上有三个工作者：worker0，worker1，worker2。
 * 有一个任务的完成需要他们三者协作完成，worker2 可以开始这个任务的前提是 worker0 和 worker1 完成了他们的工作，
 * 而worker0和worker1是可以并行他们各自的工作的
 */

/*
 * 当在当前线程中调用某个线程 thread 的 join() 方法时，当前线程就会阻塞，直到thread 执行完成，当前线程才可以继续往下执行。
 * 补充下：join的工作原理是，不停检查thread是否存活，如果存活则让当前线程永远wait，直到thread线程终止，线程的this.notifyAll 就会被调用。
 */

public class ThreadJoin {

	public static void main(String[] args) {
		Worker worker0 = new Worker("Worker0", (long) (Math.random() * 2000 + 3000));
		Worker worker1 = new Worker("Worker1", (long) (Math.random() * 2000 + 3000));
		Worker worker2 = new Worker("Worker2", (long) (Math.random() * 2000 + 3000));

		worker0.start();
		worker1.start();

		try {
			worker0.join();
			worker1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("准备工作就绪");
		worker2.start();

	}
}

class Worker extends Thread {
	// 工作者名
	private String name;
	// 工作时间
	private long time;

	public Worker(String name, long time) {
		this.name = name;
		this.time = time;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			System.out.println(name + "开始工作");
			Thread.sleep(time);
			System.out.println(name + "工作完成，耗费时间=" + time);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
