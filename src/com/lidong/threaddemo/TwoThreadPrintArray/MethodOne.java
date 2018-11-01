package com.lidong.threaddemo.TwoThreadPrintArray;

public class MethodOne implements Tasks{

	class ThreadToGo {
		int value = 1;
	}

	private final ThreadToGo threadToGo = new ThreadToGo();

	@Override
	public Runnable newThreadOne() {
		final String[] inputArr = Helper.buildNoArr(52);
		return new Runnable() {
			private String[] arr = inputArr;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					for (int i = 0; i < arr.length; i += 2) {
						synchronized (threadToGo) {
							while (threadToGo.value == 2) {
								threadToGo.wait();
							}
							Helper.print(arr[i], arr[i + 1]);
							threadToGo.value = 2;
							threadToGo.notifyAll();
						}
					}
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
		};
	}

	@Override
	public Runnable newThreadTwo() {
		final String[] inputArr = Helper.buildCharArr(26);
		return new Runnable() {
			private String[] arr = inputArr;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					for (int i = 0; i < arr.length; i++) {
						synchronized (threadToGo) {
							while (threadToGo.value == 1) {
								threadToGo.wait();
							}
							Helper.print(arr[i]);
							threadToGo.value = 1;
							threadToGo.notifyAll();
						}
					}
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
		};
	}

	public static void main(String[] args) {
		MethodOne methodOne = new MethodOne();
		Helper.instance.run(methodOne.newThreadOne());
		Helper.instance.run(methodOne.newThreadTwo());
		Helper.instance.shutdown();
	}

}
