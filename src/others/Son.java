package others;

public class Son extends Father{

	private String name;
	
	public Son(String name) {
		//super();
		// TODO Auto-generated constructor stub
		this.name = name;
		System.out.println("Son Construct");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
