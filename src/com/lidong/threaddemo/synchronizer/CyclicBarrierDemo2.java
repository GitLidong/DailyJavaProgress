package com.lidong.threaddemo.synchronizer;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo2 {

	public static void main(String[] args) {
		final int N = 10;
		ExecutorService pool = Executors.newCachedThreadPool();
		boolean flag = false;
		CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
		// 设置屏障点,主要为了执行这个方法
		System.out.println("集合队伍! ");
		for (int i = 0; i < N; i++) {
			pool.execute(new Soldier(cyclic, "士兵" + i));
		}
		pool.shutdown();
	}
}

class Soldier implements Runnable {
	private String soldier;
	private final CyclicBarrier cyclic;

	public Soldier(CyclicBarrier cyclic, String soldier) {
		this.soldier = soldier;
		this.cyclic = cyclic;
	}

	@Override
	public void run() {
		try {
			// 等待所有士兵到齐
			System.out.println(soldier + ": 报道! ");
			cyclic.await();
			doWork();
			// 等待所有士兵完成工作
			cyclic.await();
		} catch (InterruptedException e) {// 在等待过程中,线程被中断
			e.printStackTrace();
		} catch (BrokenBarrierException e) {// 表示当前CyclicBarrier已经损坏.系统无法等到所有线程到齐了.
			e.printStackTrace();
		}
	}

	void doWork() {
		try {
			Thread.sleep(Math.abs(new Random().nextInt() % 10000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(soldier + ":任务完成");
	}

}

class BarrierRun implements Runnable {
	boolean flag;
	int N;

	public BarrierRun(boolean flag, int N) {
		this.flag = flag;
		this.N = N;
	}

	@Override
	public void run() {
		if (flag) {
			System.out.println("司令:[士兵" + N + "个,任务完成!]");
		} else {
			System.out.println("司令:[士兵" + N + "个,集合完毕!]");
			flag = true;
		}
	}
}