//import java.util.LinkedList;
//
///**
// * {@link AVLTree}를 hashing하여 저장하는 hash table.
// * 
// * 	@param <T> : {@code String}
// *  @param <P> : {@link IndexPair}
// */
//public class HashTable<T extends Comparable<T>, P>{
//	private final int MAX_TABLE = 100;		// table의 capacity
//	private AVLTree<T,P>[] items;			// AVLTree를 저장하는 배열을 가진다.
//	private int size;
//	
//	
//	@SuppressWarnings("unchecked")
//	public HashTable(){
//		items = new AVLTree[MAX_TABLE];
//		size = 0;
//	}
//	
//	public boolean isEmpty(){
//		return size == 0;
//	}
//	
//	public int size(){
//		return size;
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void resetAll(){
//		items = new AVLTree[MAX_TABLE];
//		size = 0;
//	}
//	
//////////////////////////////////////////////////////////////////////
//	/*		HASHING		*/
//	
//	private int hashValue(T item){
//		Hashing h = new Hashing();
//		String string = "";
//		if(item.getClass() == string.getClass()){
//			return h.hashValue((String) item);
//		}
//		return 0;
//	}
//	
//	class Hashing {
//		
//		public int hashValue(String item){
//			int value = 0;
//			for(int i = 0; i < item.length(); i++){
//				value += item.charAt(i);
//			}
//			return value % MAX_TABLE;
//		}
//		
//		// 다른 type의 item에 대한 hashValue 함수를 여기에 overload 할 수 있다.
//	}
//	
//////////////////////////////////////////////////////////////////////
//	/*		UTILITIES		*/
//	
//	/**
//	 * {@code item}을 hashing한 값({@code hashCode})에 따라 hash table에 {@code item}을 저장한다.
//	 * hash table은 {@link AVLTree}를 담는 배열이므로, 실제로는 
//	 * {@code String item}과 {@link IndexPair} {@code idx}를 입력받아
//	 * {@code AVLTree}에 insert하는 역할을 한다.
//	 */
//	public void put(T item, P idx){
//		int hashCode = hashValue(item);				// hashing item
//		if(items[hashCode] == null){				// if slot is empty
//			AVLTree<T,P> avl = new AVLTree<>();		// make a new AVL tree
//			avl.insert(item, idx);
//			items[hashCode] = avl;
//		}
//		else{
//			items[hashCode].insert(item, idx);
//		}
//		
//		size++;
//	}
//	
//	/**
//	 * searchKey를 갖는 AVLTree를 찾아 그 index list를 반환한다. 
//	 * @param searchKey
//	 * @return {@code LinkedList<P> list} : Index list of treenode with {@code searchKey}
//	 */
//	public LinkedList<P> searchList(T searchKey){
//		AVLTree<T,P> avl = items[hashValue(searchKey)];
//		if(avl == null){			// if not found, return null
//			return null;
//		}
//		return avl.searchList(searchKey);
//	}
//	
//	/**
//	 * Hash table의 {@code slot}번째 위치에 있는 AVLTree의 모든 item을 print한다.
//	 * @param slot
//	 */
//	public void printSlotItems(int slot){
//		AVLTree<T, P> avl = items[slot];
//		if(avl == null)						// if slot is empty
//			System.out.println("EMPTY");
//		else
//			avl.printAVLTree();
//	}
//	
//}

import java.util.LinkedList;

/**
 * {@link AVLTree}를 hashing하여 저장하는 hash table.
 * 
 */
public class HashTable{
	private final int MAX_TABLE = 100;				// table의 capacity
	private AVLTree<String, IndexPair>[] items;		// AVLTree를 저장하는 배열을 가진다.
	private int size;
	
	
	@SuppressWarnings("unchecked")
	public HashTable(){
		items = new AVLTree[MAX_TABLE];
		size = 0;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	@SuppressWarnings("unchecked")
	public void resetAll(){
		items = new AVLTree[MAX_TABLE];
		size = 0;
	}
	
////////////////////////////////////////////////////////////////////
	/*		HASHING		*/
	
	private int hashValue(String item){
		int value = 0;
		for(int i = 0; i < item.length(); i++){
			value += item.charAt(i);
		}
		return value % MAX_TABLE;
	}
	
////////////////////////////////////////////////////////////////////
	/*		UTILITIES		*/
	
	/**
	 * {@code item}을 hashing한 값({@code hashCode})에 따라 hash table에 {@code item}을 저장한다.
	 * hash table은 {@link AVLTree}를 담는 배열이므로, 실제로는 
	 * {@code String item}과 {@link IndexPair} {@code idx}를 입력받아
	 * {@code AVLTree}에 insert하는 역할을 한다.
	 */
	public void put(String item, IndexPair idx){
		int hashCode = hashValue(item);				// hashing item
		if(items[hashCode] == null){				// if slot is empty,
			AVLTree<String, IndexPair> avl = new AVLTree<>();	// make a new AVL tree
			avl.insert(item, idx);
			items[hashCode] = avl;
		}
		else{
			items[hashCode].insert(item, idx);
		}
		
		size++;
	}
	
	/**
	 * searchKey를 갖는 AVLTree를 찾아 그 index list를 반환한다. 
	 * @param searchKey
	 * @return {@code LinkedList<P> list} : Index list of treenode with {@code searchKey}
	 */
	public LinkedList<IndexPair> searchList(String searchKey){
		AVLTree<String, IndexPair> avl = items[hashValue(searchKey)];
		if(avl == null){			// if not found, return null
			return null;
		}
		return avl.searchList(searchKey);
	}
	
	/**
	 * Hash table의 {@code slot}번째 위치에 있는 AVLTree의 모든 item을 print한다.
	 * @param slot
	 */
	public void printSlotItems(int slot){
		AVLTree<String, IndexPair> avl = items[slot];
		if(avl == null)						// if slot is empty
			System.out.println("EMPTY");
		else
			avl.printAVLTree();
	}
	
}
