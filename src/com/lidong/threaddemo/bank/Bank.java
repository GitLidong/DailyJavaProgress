package com.lidong.threaddemo.bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

	private final double [] accounts;
	private Lock lockBank;
	private Condition suffiCondition;
	
	public Bank(int n, double initBalance) {
		// TODO Auto-generated constructor stub
		accounts = new double[n];
		for (int i = 0; i < n; i++) {
			accounts[i] = initBalance;
		}
		lockBank = new ReentrantLock();
		suffiCondition = lockBank.newCondition();
	}
	
	public void transfer(int from, int to, double amount) {
		lockBank.lock();
		try {
			while (accounts[from] < amount) {
				suffiCondition.await();
			}
			
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf("%10.2f from %d to %d ", amount,from,to);
			accounts[to] += amount;
			System.out.printf("Total Balance %10.2f %n",getToal());
			suffiCondition.signalAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lockBank.unlock();
		}
	}
	
	public synchronized void transfer2222(int from, int to, double amount) throws InterruptedException {

		while (accounts[from] < amount) {
			wait();
		}
		
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf("%10.2f from %d to %d ", amount,from,to);
		accounts[to] += amount;
		System.out.printf("Total Balance %10.2f %n",getToal());
		
		notifyAll();
	}
	
	public double getToal() {
		
		lockBank.lock();
		try {
			double total = 0;
		    for (double temp : accounts) {
		    	total += temp;
		    }
		    return total;
		} finally {
			lockBank.unlock();
		}

	}
	
	public synchronized double getToal2222() {
		double total = 0;
	    for (double temp : accounts) {
	    	total += temp;
	    }
	    return total;
	}
	
	public int getSize() {
		return accounts.length;
	}
	
}
