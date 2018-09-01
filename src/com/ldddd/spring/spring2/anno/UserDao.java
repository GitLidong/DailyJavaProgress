package com.ldddd.spring.spring2.anno;

import org.springframework.stereotype.Component;

@Component(value = "userdao")
public class UserDao {

	public void add() {
		System.out.println("dao.........");
	}

}
