package com.lidong.threaddemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 
 * @author lidong
 * 
 * 由于线程的本质特性，使得你不能不会从线程逃逸的异常。一旦异常他逃出任务的run方法，他就会向外传播到控制台。
 * 除非采用特殊的步骤捕获这种错误的异常。
 * 
 */

public class CaputerUncaughtException {
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread());
	}

}

class ExceptionThread implements Runnable {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread t = Thread.currentThread();
		System.out.println("run() by "+t);
		System.out.println("eh = "+t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	
	@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		System.out.println("caught "+arg1);
	}
}

class HandlerThreadFactory implements ThreadFactory {
	
	@Override
	public Thread newThread(Runnable arg0) {
		// TODO Auto-generated method stub
		System.out.println(this+ " creating new Thread");
		Thread t = new Thread(arg0);
		System.out.println("created "+t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh = "+t.getUncaughtExceptionHandler());
		return t;
	}
}
