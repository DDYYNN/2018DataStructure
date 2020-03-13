package www.linkedlist.com;

public class Node {
	private Object item;
	private Node next;
	public Node(Object newItem){
		item = newItem;
		next = null;
	}
	public Node(Object newItem, Node nextNode){
		item = newItem;
		next = nextNode;
	}
	
	public Object getItem(){
		return item;
	}
	public void setItem(Object newItem){
		item = newItem;
	}
	public Node getNext(){
		return next;
	}
	public void setNext(Node nextNode){
		next = nextNode;
	}
}
