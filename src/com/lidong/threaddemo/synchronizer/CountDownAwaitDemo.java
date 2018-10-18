package com.lidong.threaddemo.synchronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownAwaitDemo {

	public static void main(String[] args) {
		VideoConference conference = new VideoConference(10);
		Thread waiting = new Thread(conference);
		waiting.start();

		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 1; i <= 10; i++) {
			Participant p = new Participant(conference, "Participant " + i);
			pool.submit(p);
		}
		pool.shutdown();
	}

}

class VideoConference implements Runnable {

	private final CountDownLatch controller;

	public VideoConference(int number) {
		// TODO Auto-generated constructor stub
		controller = new CountDownLatch(number);
	}

	public synchronized void arrive(String name) {
		System.out.printf("%s has arrived.\n", name);
		controller.countDown();
		System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			controller.await();
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class SimlockBlob {
		private String Version;
		private String Configuration;
		private String MaxAttempts;
		private String TestIMSI;
		private String IMSI;
		private String Customization;
		private String AutolockStates;
		private String Categories;
		private String Network;
		private String NetworkSub;
		private String Service;
		private String Corporate;

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Version:" + "\nConfiguration:" + "\nMax Attempts:" + "\nTestIMSI:" + "\nIMSI:" + "\nCustomization:"
					+ "\nAutolock states:" + "\nCategories:" + "\nNetwork:" + "\nNetworkSub:" + "\nService:"
					+ "\nCorporate:";
		}
	}																																											
}

class Participant implements Runnable {

	private VideoConference conference;
	String name;

	public Participant(VideoConference conference, String name) {
		// TODO Auto-generated constructor stub
		this.conference = conference;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Long duration = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		conference.arrive(name);
	}
}