package www.linkedlist.com;

public interface ListInterface {
	public boolean isEmpty();
	public int size();
	public void add(int index, Object item) throws InvalidIndexException;
	public void remove(int index) throws InvalidIndexException;
	public Object get(int index) throws InvalidIndexException;
	public void removeAll();
	public void printList();
}
