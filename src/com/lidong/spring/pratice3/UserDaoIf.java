package com.lidong.spring.pratice3;

/**
 * 以接口的方式为LoginAction提供登录检查服务 只需要知道LoginAction要实现登录操作需要一个login()方法
 * 不需要知道login()方法如何实现 用到的时候只需要远程调用其他服务器上的实现方法
 * 
 * @author LiDong
 *
 */

public interface UserDaoIf {

	public boolean login();

}
