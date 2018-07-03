package set_demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class MapDemo {
	
	public static void main(String[] args) {
		Map<String, String> info = new HashMap<>();
		info.put("name", "lidong");
		info.put("age", "18");
		
		Collection<String> values = info.values();
		System.out.println(values);
	}

}
