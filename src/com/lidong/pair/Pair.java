package com.lidong.pair;

public class Pair<T> {
	
	private T min;
	private T max;
	
	public Pair() {
		// TODO Auto-generated constructor stub
	}
	
	public Pair(T min, T max) {
		// TODO Auto-generated constructor stub
	    this.min = min;
	    this.max = max;
	}
	
	public void setFirst(T min) {
		this.min = min;
	}
	
	public T getFirst() {
		return min;
	}
	
	public void setSecond(T max) {
		this.max = max;
	}

	public T getSecond() {
		return max;
	}
	
}
