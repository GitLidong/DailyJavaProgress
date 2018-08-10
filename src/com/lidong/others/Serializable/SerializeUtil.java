package com.lidong.others.Serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeUtil {

	// 传输保存的文件位置
	private static String file_name = "resource/obj.bin";

	// 序列化
	public static void writeObj(Serializable s) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file_name));
			oos.writeObject(s);
			System.out.println("序列化成功");
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 反序列化
	public static Object readObj() {

		Object obj = null;
		try {

			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(file_name));
			obj = input.readObject();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Salay salay = new Salay(2000.0, 1200.0);
		// 创建持久化对象
		Person per1 = new Person("AAAAA", 20, salay);

		// 进行序列化
		SerializeUtil.writeObj(per1);

		// 反序列化读取数据
		Person per2 = (Person) SerializeUtil.readObj();

		// 测试
		System.out.println("person.name->" + per2.getName());
		System.out.println("person.base->" + per2.getSalay().getBase());
		System.out.println("person.bounse->" + per2.getSalay().getBounse());
	}

}
