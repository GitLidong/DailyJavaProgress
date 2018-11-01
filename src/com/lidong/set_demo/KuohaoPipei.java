package com.lidong.set_demo;

public class KuohaoPipei {

	public static void main(String[] args) {
		System.out.println(pipei("111".toCharArray()));
	}
	
	private static boolean pipei(char [] datas) {
		
		int top = datas.length -1;
		int down = 0;
		while(down < top) {
			System.out.println(datas[down]+" , "+datas[top]);
			if(datas[down] != datas[top]) {
				return false;
			}
			top--;
			down++;
		}
		
		return true;
		
	}
	
}
