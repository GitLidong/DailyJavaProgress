package threadpoll_demo;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class MatchCounter implements Callable<Integer>{

	private File dic;
	private String keyWords;
	private ExecutorService pool;
	private int count;
	
	public MatchCounter(File dic, String keyWords, ExecutorService pool) {
		// TODO Auto-generated constructor stub
		this.dic = dic;
		this.keyWords = keyWords;
		this.pool = pool;
	}
	
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		count = 0;
		
		return null;
	}

}
