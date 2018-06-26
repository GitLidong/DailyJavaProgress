package set_demo;

public class SetDemoTest {
	
	public static void main(String[] args) {
		
		LinkedListQueue<String> test = new LinkedListQueue<>();
		test.add("test1");
		test.add("test2");
		test.add("test3");
		test.add("test4");
		
		System.out.println(test.size());
		test.showInfo();
		
		System.out.println(test.remove());
		test.showInfo();
	}

}
