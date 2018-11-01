package com.lidong.threaddemo.TwoThreadPrintArray;

public class MethodThree implements Tasks {
	class ThreadToGo {
		int value = 1;
	}

	private volatile ThreadToGo threadToGo = new ThreadToGo();

	@Override
	public Runnable newThreadOne() {
		// TODO Auto-generated method stub
		final String[] inputArr = Helper.buildNoArr(52);
		return new Runnable() {
			private String[] arr = inputArr;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < arr.length; i++) {
					while (threadToGo.value == 2) {
					}
					Helper.print(arr[i], arr[i + 1]);
					threadToGo.value = 2;
				}
			}
		};

	}

	@Override
	public Runnable newThreadTwo() {
		final String[] inputArr = Helper.buildCharArr(26);
		return new Runnable() {
			private String[] arr = inputArr;

			public void run() {
				for (int i = 0; i < arr.length; i++) {
					while (threadToGo.value == 1) {
					}
					Helper.print(arr[i]);
					threadToGo.value = 1;
				}
			}
		};
	}

	public static void main(String[] args) {
		MethodThree methodThree = new MethodThree();
		Helper.instance.run(methodThree.newThreadOne());
		Helper.instance.run(methodThree.newThreadTwo());
		Helper.instance.shutdown();
	}

}
