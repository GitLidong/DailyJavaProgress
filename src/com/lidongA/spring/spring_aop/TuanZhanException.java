package com.lidongA.spring.spring_aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class TuanZhanException implements ThrowsAdvice {

	// 该方法会在露娜团战出现异常后自动执行
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		System.out.print(this.getClass().getName() + " -- ");
		Luna luna = new Luna();
		luna.run();
	}

}
