package com.lidongA.spring.spring_aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeTuanZhan implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		// TODO Auto-generated method stub
		System.out.print(this.getClass().getName() + " -- ");
		Luna luna = new Luna();
		luna.setHeroName("露娜");
		luna.setType("战士/法师");
		luna.setDescription("月光女神");
		System.out.println(luna.toString());
		luna.say("等我集合打团！");
	}

}
