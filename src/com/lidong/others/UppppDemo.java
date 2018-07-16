package com.lidong.others;

public class UppppDemo {

	private static String name;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UppppDemo demo = new UppppDemo();
		demo.test1();

		demo.test2();

		demo.test3();

		name = "test4";
		demo.test4();
	}


	/**
	 * == 比较的是两个对象的引用 当我们声明一个 Interger c = 100
	 * 时候，此时会进行自动的装箱操作，就是把基本数据类型转换成Integer对象，调用的是Integer的valueOf方法 Integer把
	 * [-128，128) 缓存下来，官方解释是因为小的数字使用更加频繁，为了优化性能，把这些数字缓存下来。 所以当 Integer对象值在
	 * [-128,128)时候，引用的是同一对象。
	 * 
	 */
	private void test1() {
		Integer a = 1000, b = 1000;
		Integer c = 100, d = 100;
		System.out.println(a == b); // 引用的是不同对象
		System.out.println(c == d); // 引用的是用一对象。
		System.out.println("------------------");

	}

	/**
	 * a == b 首先值为1000，肯定跟缓存没关系， a 是 new 出来的对象，当int 和 Integer比较时候，Java会把 Integer自动拆箱
	 * 也就是把Integer转化为 int 类型
	 * 
	 * c == d 两人都是new 出来的对象，不调用valueOf ，和缓存没关系。
	 */
	private void test2() {
		Integer a = new Integer(1000);
		int b = 1000;

		Integer c = new Integer(10);
		Integer d = new Integer(10);

		System.out.println(a == b);
		System.out.println(c == d);
		System.out.println("------------------");
	}

	/**
	 * 一些基本类型的变量和对象的引用、还有函数调用的现场都是存在栈中的； new 出来的对象、 构造器创建的对象 都是存在堆中的；
	 * 方法区：用于存储已被JVM加载的类信息，常量，静态变量，JIT编译器编译后的代码等数据 （方法区和堆都是被各线程共享的内存区域）
	 * 程序中的字面量如直接书写的 100 "hello" 都是在静态存储区的。
	 */
	private void test3() {
		String s1 = "abc"; // abc被存储在常量池中
		String s2 = "abc"; // Java底层会优先在常量池中查找是否存在 abc，如果存在则直接指向这个值，不存在则添加。
		String s3 = new String("abc");
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println("------------------");
	}

	private void test4() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(name);
			}
		}).start();
	}
}
