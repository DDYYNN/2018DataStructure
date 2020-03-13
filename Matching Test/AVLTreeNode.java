import java.util.LinkedList;

/**
 * AVL 트리의 generic node.
 * 각 노드에는 substring과, substring의 index들이 들어있는 {@code LinkedList}가 저장된다.
 *
 * @param <T> <b>{@code extends Comparable}</b> Item of node ({@code String} substring)
 * @param <P> Pair that goes into the node's {@code LinkedList} ({@link IndexPair} pair)
 */
public class AVLTreeNode<T extends Comparable<T>, P>{
	private T item;
	private LinkedList<P> nodeList;			// LinkedList containing index pairs
	private AVLTreeNode<T,P> leftChild;
	private AVLTreeNode<T,P> rightChild;
	private int height;
	
	public AVLTreeNode(T newItem, P pair,
			AVLTreeNode<T,P> leftChild, AVLTreeNode<T,P> rightChild){
		item = newItem;
		nodeList = new LinkedList<>();
		addPair(pair);
		this.leftChild = leftChild;		this.rightChild = rightChild;
		height = 1;
	}
	
	public AVLTreeNode(T newItem, P pair){
		this(newItem, pair, null, null);
	}
	
	public T getItem(){
		return item;
	}
	
	public void setItem(T newItem){
		item = newItem;
	}
	
	public void addPair(P pair){
		nodeList.add(pair);
	}
	
	public LinkedList<P> getList(){
		return nodeList;
	}
	
	public AVLTreeNode<T,P> getLeft(){
		return leftChild;
	}
	
	public AVLTreeNode<T,P> getRight(){
		return rightChild;
	}
	
	public void setLeft(AVLTreeNode<T,P> left){
		leftChild = left;
	}
	
	public void setRight(AVLTreeNode<T,P> right){
		rightChild = right;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int h){
		height = h;
	}

}
