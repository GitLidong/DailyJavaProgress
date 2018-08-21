package com.lidong.spring.pratice3;

import org.springframework.beans.factory.FactoryBean;

public class DaoFactoryBean implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		// 当前工厂需要创建的对象实例
		return new UserDaoIf() {
			public boolean login() {
				System.out.println("使用webservice调用其他服务器方法");
				return true;
			}
		};
	}

	@Override
	public Class getObjectType() {
		// 描述当前工厂创建的实例类型
		return UserDaoIf.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
