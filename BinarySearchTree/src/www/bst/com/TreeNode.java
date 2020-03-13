package www.bst.com;

public class TreeNode<T extends Comparable<T>>{
	protected T item;
	protected TreeNode<T> leftChild;
	protected TreeNode<T> rightChild;
	
	public TreeNode(T newItem, TreeNode<T> leftChild, TreeNode<T> rightChild){
		item = newItem;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public TreeNode(T newItem){
		this(newItem, null, null);
	}
	
	public T getItem(){
		return item;
	}
	
	public void setItem(T newItem){
		item = newItem;
	}
	
	public TreeNode<T> getLeft(){
		return leftChild;
	}
	
	public TreeNode<T> getRight(){
		return rightChild;
	}
	
	public void setLeft(TreeNode<T> left){
		leftChild = left;
	}
	
	public void setRight(TreeNode<T> right){
		rightChild = right;
	}
	
}

