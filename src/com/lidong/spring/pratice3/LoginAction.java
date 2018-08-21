package com.lidong.spring.pratice3;

public class LoginAction {
	private UserDaoIf dao;

	public void setDao(UserDaoIf dao) {
		this.dao = dao;
	}

	public String execute() throws Exception {
		dao.login();
		return "success";
	}

}
