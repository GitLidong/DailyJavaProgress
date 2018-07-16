package com.lidong.synchronizer;

/*
 * 
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceUser implements Runnable {

	private ResourceManage resourceManage;
	private int userId;

	public ResourceUser(ResourceManage resourceManage, int userId) {
		this.resourceManage = resourceManage;
		this.userId = userId;
	}

	public void run() {
		System.out.print("userId:" + userId + "准备使用资源...\n");
		resourceManage.useResource(userId);
		System.out.print("userId:" + userId + "使用资源完毕...\n");
	}

	public static void main(String[] args) {
		ResourceManage resourceManage = new ResourceManage();
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			pool.submit(new ResourceUser(resourceManage, i));
		}
		pool.shutdown();
	}

}

class ResourceManage {
	private final Semaphore semaphore;
	private boolean resourceArray[];
	private final ReentrantLock lock;

	public ResourceManage() {
		this.resourceArray = new boolean[10];// 存放厕所状态
		this.semaphore = new Semaphore(10, true);// 控制10个共享资源的使用，使用先进先出的公平模式进行共享;公平模式的信号量，先来的先获得信号量
		this.lock = new ReentrantLock(true);// 公平模式的锁，先来的先选
		for (int i = 0; i < 10; i++) {
			resourceArray[i] = true;// 初始化为资源可用的情况
		}
	}

	private int getResourceId() {
		int id = -1;
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {
				if (resourceArray[i]) {
					resourceArray[i] = false;
					id = i;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return id;
	}

	public void useResource(int userId) {
		try {
			semaphore.acquire();
			int id = getResourceId();
			System.out.print("userId: " + userId + "  is  using resource id: " + id + "\n");
			Thread.sleep(100);
			resourceArray[id] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
