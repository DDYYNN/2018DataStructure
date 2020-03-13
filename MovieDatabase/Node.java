public class Node<T> {
    private T item;
    private Node<T> next;

    public Node(T obj) {
        this.item = obj;
        this.next = null;
    }
    
    public Node(T obj, Node<T> next) {
    	this.item = obj;
    	this.next = next;
    }
    
    public final T getItem() {
    	return item;
    }
    
    public final void setItem(T item) {
    	this.item = item;
    }
    
    public final void setNext(Node<T> next) {
    	this.next = next;
    }
    
    public Node<T> getNext() {
    	return this.next;
    }
    
    public final void insertNext(T obj) {
		Node<T> newNode =  new Node<T>(obj);	// Make a new node,
		newNode.setNext(this.next);				// connect it to the next
		this.setNext(newNode);					// and connect it to the previous node.
    }
    
    public final void removeNext() {
		Node<T> next = this.getNext();			// Extract the next node,
		this.setNext(next.getNext());			// make the current node jump over it,
		next.setNext(null);						// and make it refer to nothing.
    }
}