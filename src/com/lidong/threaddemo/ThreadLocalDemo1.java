package com.lidong.threaddemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo1 {

	private static A a = new A();
	private static final ThreadLocal<A> THREAD_LOCAL = new ThreadLocal<A>() {
		protected A initialValue() {
			return a;
		};
	};

	private static final ThreadLocal<A> THREAD_LOCAL_222 = new ThreadLocal<A>() {
		protected A initialValue() {
			return new A();
		};
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			pool.execute(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					THREAD_LOCAL.get().setNumber(
							THREAD_LOCAL.get().getNumber() + 5);
					System.out.println(Thread.currentThread().getName() + " : "
							+ THREAD_LOCAL.get().getNumber());
				}
			});
		}
		// 运行结果
		// pool-1-thread-1 : 5
		// pool-1-thread-2 : 10
		// pool-1-thread-4 : 15
		// pool-1-thread-3 : 20
		// pool-1-thread-5 : 25
		/**
		 * 很显然，在这里，并没有通过ThreadLocal达到线程隔离的机制，可是ThreadLocal不是保证线程安全的么？这是什么鬼？
		 * 虽然，ThreadLocal让访问某个变量的线程都拥有自己的局部变量
		 * ，但是如果这个局部变量都指向同一个对象呢？这个时候ThreadLocal就失效了。
		 * 仔细观察下图中的代码，你会发现，threadLocal在初始化时返回的都是同一个对象a！
		 */

		for (int i = 5; i < 10; i++) {
			pool.execute(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					THREAD_LOCAL_222.get().setNumber(
							THREAD_LOCAL_222.get().getNumber() + 5);
					System.out.println(Thread.currentThread().getName() + " : "
							+ THREAD_LOCAL_222.get().getNumber());
				}
			});
		}

		// 运行结果
		// pool-1-thread-5 : 5
		// pool-1-thread-6 : 5
		// pool-1-thread-7 : 5
		// pool-1-thread-8 : 5
		// pool-1-thread-9 : 5

		pool.shutdown();
	}
}

class A {
	int number = 0;

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
