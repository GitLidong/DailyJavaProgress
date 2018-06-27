package set_demo;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetDemo {
	
	public static void main(String[] args) {
		
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("AAA", 333));
		parts.add(new Item("BBB", 111));
		parts.add(new Item("CCC", 222));
		System.out.println(parts);
		
		
		SortedSet<Item> sortByDescription = new TreeSet<>(new Comparator<Item>() {

			@Override
			public int compare(Item arg0, Item arg1) {
				// TODO Auto-generated method stub
				String descA = arg0.getDescription();
				String descB = arg1.getDescription();
				return descA.compareTo(descB);
			} 
			
		});
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}

}

class Item implements Comparable<Item> {

	private String description;
	private int partNumber;
	
	public Item(String description, int partNumber) {
		// TODO Auto-generated constructor stub
		this.description = description;
		this.partNumber = partNumber;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[description: "+description+", partNumber: "+partNumber+"]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		
		Item other = (Item)obj;
		return this.description.equals(other.getDescription())
				&& this.partNumber == other.partNumber;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 13 * description.hashCode() + 17 * partNumber;
	}

	@Override
	public int compareTo(Item arg0) {
		// TODO Auto-generated method stub
		return partNumber - arg0.partNumber;
	}
	
}
