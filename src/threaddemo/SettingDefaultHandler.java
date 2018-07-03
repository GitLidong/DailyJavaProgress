package threaddemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author lidong
 *
 * 在Thread类中设置同一个静态域，并将这个处理器设置为默认未捕获异常处理器
 *
 */

public class SettingDefaultHandler {

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
	
	
}
