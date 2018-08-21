package com.lidong.spring.pratice2;


public class LoginAction {

	public void init() {
		System.out.println("loginActoin初始化了");
	}

	/*
	 * 3 方法注入：方法的返回值为要注入的对象类型
	 */
	public UserDao etoak() {
		System.out.println("LoginAction etoak");
		return null;
	}

	public String execute() throws Exception {
		etoak().login();
		/*
		 * ud.login(); System.out.println("name---->"+name);
		 */
		return "success";
	}

}
