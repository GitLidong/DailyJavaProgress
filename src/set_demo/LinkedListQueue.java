package set_demo;

public class LinkedListQueue<E> implements Queue<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public LinkedListQueue() {
		// TODO Auto-generated constructor stub
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
		if (head == null && tail == null) {
			//means LinkedListQueue is empty
			head = new Node<>(element,null);
			tail = head;
		} else {
			Node<E> temp = new Node<>(element,null);
			tail.setNext(temp);
			tail = temp;
		}
		size ++;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		if (head == null && tail == null) {
			return null;
		} else if (head == null) {
			tail = null;
			return null;
		} else {
			Node<E> temp = head;
			E tempE;
			head = head.getNext();
			tempE = temp.getData();
			temp = null;
			return tempE;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		Node<E> temp = head;
		while (temp != null) {
			System.out.println("now: "+temp+" "+temp.getData()+"  "+temp.getNext());
			temp = temp.getNext();
		}
	}

}

class Node<E> {
	private E data;
	private Node<E> next;
	
	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	Node (E data, Node<E> next) {
		this.data = data;
		this.next = next;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	public E getData() {
		return data;
	}
	
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	public Node<E> getNext() {
		return next;
	}
}