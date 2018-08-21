package com.lidong.spring.pratice1;

public class UserManagerImpl implements UserManager{

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void save(String username, String password) {
		// TODO Auto-generated method stub
		this.userDao.save(username, password);
	}

}
