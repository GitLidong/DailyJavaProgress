package com.lidong.threaddemo.bingfafa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import com.lidong.threaddemo.bingfafa.Pair.PairValuesNotEqualsException;

public class CriticalSection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PairManager pman1 = new PairManager1();
		PairManager pman2 = new PairManager2();

		testApproaches(pman1, pman2);
	}

	static void testApproaches(PairManager pman1, PairManager pman2) {
		ExecutorService pool = Executors.newCachedThreadPool();
		PairManipulattor pm1 = new PairManipulattor(pman1), pm2 = new PairManipulattor(
				pman2);
		PairChecker pcheck1 = new PairChecker(pman1), pCheck2 = new PairChecker(
				pman1);
		pool.execute(pm1);
		pool.execute(pm2);
		pool.execute(pcheck1);
		pool.execute(pCheck2);

		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("pm1: " + pm1 + " \npm2: " + pm2);
		System.exit(0);
	}

}

class Pair {
	private int x, y;

	public Pair(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}

	public Pair() {
		this(0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "X: " + x + " ,Y: " + y;
	}

	public class PairValuesNotEqualsException extends RuntimeException {
		public PairValuesNotEqualsException() {
			// TODO Auto-generated constructor stub
			super("pair values not equals: " + Pair.this);
		}
	}

	public void checkState() {
		if (x != y) {
			throw new PairValuesNotEqualsException();
		}
	}
}

abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();
	private List<Pair> storage = Collections
			.synchronizedList(new ArrayList<Pair>());

	public synchronized Pair getPair() {
		// make a copy to keep original safe
		return new Pair(p.getX(), p.getY());
	}

	protected void store(Pair p) {
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

	public abstract void increment();
}

class PairManager1 extends PairManager {
	@Override
	public synchronized void increment() {
		// TODO Auto-generated method stub
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}

class PairManager2 extends PairManager {
	@Override
	public void increment() {
		// TODO Auto-generated method stub
		Pair temp;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}

class ExplicitPairManager1 extends PairManager {

	private ReentrantLock lock = new ReentrantLock();

	@Override
	public void increment() {
		// TODO Auto-generated method stub
		Pair temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		} finally {
			// TODO: handle exception
			lock.unlock();
		}
		store(temp);
	}
}

class ExplicitPairManager2 extends PairManager {

	private ReentrantLock lock = new ReentrantLock();

	@Override
	public synchronized void increment() {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			// TODO: handle exception
			lock.unlock();
		}
	}
}

class PairManipulattor implements Runnable {

	private PairManager pm;

	public PairManipulattor(PairManager pm) {
		// TODO Auto-generated constructor stub
		this.pm = pm;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			pm.increment();
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Pair: " + pm.getClass() + " checkCounter = "
				+ pm.checkCounter.get();
	}
}

class PairChecker implements Runnable {
	private PairManager pm;

	public PairChecker(PairManager pm) {
		// TODO Auto-generated constructor stub
		this.pm = pm;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();
		}
	}
}