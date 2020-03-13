package www.linkedlist.com;

public class ListReferenceBased implements ListInterface {
	private Node head;
	private int numItems;
	
	//constructor
	public ListReferenceBased(){
		numItems = 0;
		head = new Node(null);
		head.setNext(null);
	}
	
	//operations
	public boolean isEmpty(){
		return numItems == 0;
	}
	public int size(){
		return numItems;
	}
	private Node find(int index){
		//return reference of i-th node
		Node curr = head;
		for(int i = 1; i <= index; i++){		// because of dummy head, it should run n times.
			curr = curr.getNext();
		}
		return curr;
	}
	public Object get(int index) throws InvalidIndexException{
		if(index < 1 || index > numItems)
			throw new InvalidIndexException("Invalid Index!");
		return find(index).getItem();
	}
	public void remove(int index) throws InvalidIndexException{
		if(index < 1 || index > numItems)
			throw new InvalidIndexException("Invalid Index!");
		else{
			Node prev = find(index-1);
			Node curr = prev.getNext();
			prev.setNext(curr.getNext());
			numItems--;
		}
	}
	public void add(int index, Object item) throws InvalidIndexException{
		if(index < 1 || index > numItems + 1)
			throw new InvalidIndexException("Invalid Index!");
		else{
			Node prev = find(index-1);
			Node newNode = new Node(item, prev.getNext());
			prev.setNext(newNode);
			numItems++;
		}
	}
	public void removeAll(){
		 numItems = 0;
		 head = new Node(null);
		 head.setNext(null);
	}
	public void printList(){
		System.out.println("\n<< List >>");
		for(Node curr = head; curr != null; curr = curr.getNext()){
			if(curr.getItem() == null) continue;
			System.out.println(curr.getItem());
		}
		System.out.println("<< end of List >>\n");
	}
}
