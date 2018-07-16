package com.lidong.set_demo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

	public static void main(String[] args) {
		
		PriorityQueue<GregorianCalendar> pq = new PriorityQueue<>();
		pq.add(new GregorianCalendar(1906,Calendar.DECEMBER,9));
		pq.add(new GregorianCalendar(1815,Calendar.DECEMBER,10));
		pq.add(new GregorianCalendar(1903,Calendar.DECEMBER,3));
		pq.add(new GregorianCalendar(1910,Calendar.JUNE,22));
		
		for(GregorianCalendar temp : pq) {
			System.out.println(temp.get(Calendar.YEAR));
		}
		System.out.println("--------------------------------");
		while( !pq.isEmpty()) {
			System.out.println(pq.remove().get(Calendar.YEAR));
		}
	}
	
}
