package com.lidong.threaddemo.TwoThreadPrintArray;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MethodTwo implements Tasks {

	class ThreadToGo {
		int value = 1;
	}

	private final ThreadToGo threadToGo = new ThreadToGo();

	private Lock lock = new ReentrantLock(true);
	private Condition condition = lock.newCondition();

	@Override
	public Runnable newThreadOne() {
		final String[] inputArr = Helper.buildNoArr(52);
		return new Runnable() {
			private String[] arr = inputArr;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < arr.length; i += 2) {
					try {
						lock.lock();
						while (threadToGo.value == 2) {
							condition.await();
						}
						Helper.print(arr[i], arr[i + 1]);
						threadToGo.value = 2;
						condition.signalAll();
					} catch (InterruptedException e) {
						// TODO: handle exception
					} finally {
						lock.unlock();
					}
				}

			}
		};

	}

	@Override
	public Runnable newThreadTwo() {
		final String[] inputArr = Helper.buildCharArr(26);
		return new Runnable() {
			private String[] arr = inputArr;

			@Override
			public void run() {
				for (int i = 0; i < arr.length; i++) {
					synchronized (threadToGo) {
						try {
							lock.lock();
							while (threadToGo.value == 1) {
								condition.wait();
							}
							Helper.print(arr[i]);
							threadToGo.value = 1;
							condition.signalAll();
						} catch (Exception e) {
							// TODO: handle exception
						} finally {
							lock.unlock();
						}
					}
				}
			}
		};
	}

	public static void main(String[] args) {
		MethodTwo methodTwo = new MethodTwo();
		Helper.instance.run(methodTwo.newThreadOne());
		Helper.instance.run(methodTwo.newThreadTwo());
		Helper.instance.shutdown();
	}

}
