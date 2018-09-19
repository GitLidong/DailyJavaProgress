package com.lidong.ZALUAN;

public class StringDemo {

	public static void main(String[] args) {
		StringDemo demo = new StringDemo();
		String data = "Hello String";
		demo.reserveString(data);
		System.out.println(data);

		TestString demoTest = demo.new TestString(data);
		demo.reserveString(demoTest.toString());
		System.out.println(demoTest.toString());

	}

	private void reserveString(String data) {
		StringBuilder builder = new StringBuilder(data);
		builder.reverse();
		data = builder.toString();
		System.out.println(data);
	}

	class TestString {
		String data;

		public TestString(String data) {
			// TODO Auto-generated constructor stub
			this.data = data;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[TestString.data: " + data + "]";
		}

	}

}
