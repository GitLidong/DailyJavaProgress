package com.lidong.threaddemo;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		ExecutorService exec = Executors.newCachedThreadPool();
//		ArrayList<Future<String>> results = new ArrayList<>();
//		for (int i = 0; i < 10; i++) {
//			results.add(exec.submit(new TalkWithResult(i)));
//		}
//		exec.shutdown();
//		for(Future<String> fsFuture : results) {
//			System.out.println(fsFuture.get());
//		}
		
		ExecutorService exec1 = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			exec1.execute(new FibnaoicTask1(i));
		}
	}
	
}

class TalkWithResult implements Callable<String> {

	private int id;
	
	public TalkWithResult(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Now sleep: "+id); 
		Thread.sleep(1000);
		return "result of TalkWithResult is: "+id;
	}
	
}

class FibnaoicTask1 implements Runnable {

	private int i;
	private String result;
	
	public FibnaoicTask1(int i) {
		// TODO Auto-generated constructor stub
		this.i = i;
		result = "";
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int n=1;n<=i;n++) {
			result = fic(n)+" ";
		}
		getResult();
	}
	
	public String getResult() {
		System.out.print(result);
		return result;
	}
	
	private int fic(int i) {
		if(i==1) return 1;
		else if (i==2) return 1;
		else return fic(i-2)+fic(i-1);
	}
	
}