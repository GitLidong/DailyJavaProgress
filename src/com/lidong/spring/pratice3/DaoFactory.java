package com.lidong.spring.pratice3;

public class DaoFactory {
	private static UserDaoIf dao;
	static {
		dao = new UserDaoIf() {
			public boolean login() {
				// 不能实例化具体对象，但能调用远程的东西
				System.out.println("使用webservice技术远程调用其他服务器中的具体实现");
				return true;
			}
		};
	}

	public static UserDaoIf getDao() {
		return dao;
	}
}
