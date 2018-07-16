package com.lidong.bingfa;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexEvenGernerator extends IntGenerator{

	private int currentEventValue = 0;
	private Lock lock = new ReentrantLock();
	
	@Override
	public int next() {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			++currentEventValue;
			Thread.yield();
			++currentEventValue;
			return currentEventValue;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		EventChecker.test(new MutexEvenGernerator());
	}
	
}
