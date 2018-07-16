package com.lidong.threaddemo.bank;

public class TransferRunnable implements Runnable{

	private Bank bank;
	private int from;
	private double max;
	private int delay = 10;
	
	public TransferRunnable(Bank bank, int from, double max) {
		// TODO Auto-generated constructor stub
		this.bank = bank;
		this.from = from;
		this.max = max;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			while(true) {
				
				int to =  (int) (bank.getSize() * Math.random());
				double amount = max * Math.random();
				bank.transfer(from, to, amount);
				Thread.sleep((long) (delay*Math.random()));
			}
		} catch (InterruptedException e) {
			
		}
	
		
	}

}
