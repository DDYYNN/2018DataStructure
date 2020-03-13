package www.bst.com;

public class BinarySearchTree<T extends Comparable<T>>{
	protected TreeNode<T> root;
	
	public BinarySearchTree(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	// NEEDED?
	public TreeNode<T> getRoot(){
		return root;
	}
	
	public int getHeight(TreeNode<T> t){
		if(t == null) return 0;
		else return 1 + Math.max(getHeight(t.getLeft()), getHeight(t.getRight()));
	}
	
	public void deleteAll(){
		root = null;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	public void insert(T newItem){
		root = insertItem(root, newItem);
	}
	
	protected TreeNode<T> insertItem(TreeNode<T> tNode, T newItem){
		if (tNode == null) { // insert after a leaf  (or into an empty tree)
			tNode = new TreeNode<T>(newItem);
		} else if (newItem.compareTo(tNode.getItem()) < 0) { // branch left
			tNode.setLeft(insertItem(tNode.getLeft(), newItem));
		} else { // branch right
			tNode.setRight(insertItem(tNode.getRight(), newItem));
		}
		return tNode; 
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// NEEDED?
	public TreeNode<T> retrieve(T searchKey) {
		return retrieveItem(root, searchKey);
	}
	
	// NEEDED?
	protected TreeNode<T> retrieveItem (TreeNode<T> tNode, T searchKey) {
		if (tNode == null) return null;  // not exist!
		else {
			if (searchKey.compareTo(tNode.getItem()) == 0) return tNode;
			else if (searchKey.compareTo(tNode.getItem()) < 0) 
				return retrieveItem(tNode.getLeft(), searchKey);
			else
				return retrieveItem(tNode.getRight(), searchKey);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////
	public void delete(T searchKey) throws Exception{
		root = deleteItem(root, searchKey);
	}
	
	protected TreeNode<T> deleteItem(TreeNode<T> tNode, T searchKey) throws Exception {
        
        if (tNode == null) { throw new Exception("Item Not Found!"); } // item not found!
        else{
        	if (searchKey.compareTo(tNode.getItem()) == 0){ // item found!
        		tNode = deleteNode(tNode);
			} else if (searchKey.compareTo(tNode.getItem()) < 0){
				tNode.setLeft(deleteItem(tNode.getLeft( ), searchKey));
			} else {
				tNode.setRight(deleteItem(tNode.getRight( ), searchKey) );
			}
        }
        return tNode; // tNode: parent에 매달리는 노드
	}
	
	private TreeNode<T> deleteNode(TreeNode<T> tNode) {
        // Three cases
        //    1. tNode is a leaf
        //    2. tNode has only one child
        //    3. tNode has two children

        if ((tNode.getLeft( ) == null)  && (tNode.getRight( ) == null)){ // case 1
        	return null; 
        }
        else if (tNode.getLeft( ) == null) {  // case 2 (only right child)
        	return tNode.getRight( );
        }
        else if (tNode.getRight( ) == null) {  // case 2 (only left child)
        	return tNode.getLeft( );
        }
        else {  // case 3 – two children
        	tNode.setItem(minNode(tNode).getItem());
        	tNode.setRight(deleteMin(tNode.getRight()));
        	return tNode; // tNode survived
        }
	}
	
	private TreeNode<T> deleteMin(TreeNode<T> tNode){
		if (tNode.getLeft( ) == null) { // found min		
			return tNode.getRight( ); // right child moves to min’s place
		} else { // branch left, then backtrack
			tNode.setLeft(deleteMin(tNode.getLeft()));
			return tNode;
		}
	}
	
	private TreeNode<T> minNode(TreeNode<T> tNode){
		TreeNode<T> curr = tNode.getRight();
		while(curr.getLeft() != null)
			curr = curr.getLeft();
		return curr;
	}
	
	// NEEDED?
	public void printTree(TreeNode<T> start, int h, int arm){
		if(isEmpty()){
			System.err.println("EMPTY");
			return;
		}
		if(start != null){
			printTree(start.getRight(), h+1, 1);
			for(int i = 1; i <= h; i++){
				System.out.print("\t");
			}
			switch(arm){
			case 1 :
				System.out.print("/");
				break;
			case -1:
				System.out.print("\\");
				break;
			}
			System.out.println(start.getItem());
			printTree(start.getLeft(), h+1, -1);
		}
	}

	// NEEDED?
	public void printInOrder(TreeNode<T> start){
		if(isEmpty()){
			System.err.println("EMPTY");
			return;
		}
		if(start != null){
			printInOrder(start.getLeft());
			System.out.print(start.getItem() + " ");
			printInOrder(start.getRight());
		}
	}
}
