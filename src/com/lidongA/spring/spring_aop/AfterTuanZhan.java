package com.lidongA.spring.spring_aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterTuanZhan implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.print(this.getClass().getName() + " -- ");
		Luna luna = new Luna();
		luna.setHeroName("露娜");
		luna.setType("战士/法师");
		luna.setDescription("月光女神");
		System.out.println(luna.toString());
		luna.say("进攻敌方防御塔！");

	}

}
