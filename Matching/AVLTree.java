import java.util.LinkedList;

/**
 * {@link AVLTreeNode}를 노드로 갖는 AVL 트리.
 *
 * @param <T>
 *            {@code String}
 * @param <P>
 *            {@link IndexPair}
 * 
 *            <p>
 *            <li>modification from <b>Lecture Note.</b>
 *            <li>modification from <b><i>Data Abstraction & Problem Solving
 *            with JAVA: Walls and Mirrors</i>, J.J.Prichard, 3rd ed.</b>
 * 
 *
 */
public class AVLTree<T extends Comparable<T>, P> {
	private AVLTreeNode<T, P> root;

	public AVLTree() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void resetAll() {
		root = null;
	}

	private int getHeight(AVLTreeNode<T, P> tNode) {
		if (tNode == null)
			return 0;
		else
			return tNode.getHeight();
	}

	private void updateHeight(AVLTreeNode<T, P> tNode) {
		// node가 insert될 때 마다 각 node의 height 정보를 update해야 한다.
		tNode.setHeight(1 + Math.max(getHeight(tNode.getLeft()), getHeight(tNode.getRight())));
	}

	//////////////////////////////////////////////////////////////////////////////
	/* INSERTION */

	public void insert(T newItem, P pair) {
		root = insertItem(root, newItem, pair);
	}

	private AVLTreeNode<T, P> insertItem(AVLTreeNode<T, P> tNode, T newItem, P pair) {
		if (tNode == null) { // insert after a leaf (or into an empty tree)
			tNode = new AVLTreeNode<T, P>(newItem, pair);
		} else {
			int comp = newItem.compareTo(tNode.getItem());
			if (comp < 0) // branch left
				tNode.setLeft(insertItem(tNode.getLeft(), newItem, pair));
			else if (comp > 0) // branch right
				tNode.setRight(insertItem(tNode.getRight(), newItem, pair));
			else
				tNode.addPair(pair); // if the same item, just link the new pair
		}

		updateHeight(tNode); // insertItem이 recursive하게 일어나면서
		// 필요한 모든 노드들의 height 정보가 update된다.

		return balance(tNode, newItem); // height balance에 맞게 rotate하여 균형 유지.
	}

	/**
	 * {@code tNode}가 어느 불균형 case에 있는지를 판단한다. 왼쪽으로 치우치면("L case") 2, 오른쪽으로
	 * 치우치면("R case") -2를 반환하며, 나머지(-1,0,1)의 값의 경우 균형이 맞음을 의미한다.
	 * 
	 * @return (height of {@code tNode}'s left subtree) - (height of
	 *         {@code tNode}'s right subtree) = -2, -1, 0, 1, 2
	 * 
	 */
	private int balanceFactor(AVLTreeNode<T, P> tNode) {
		if (tNode == null)
			return 0;
		return getHeight(tNode.getLeft()) - getHeight(tNode.getRight());
	}

	private AVLTreeNode<T, P> balance(AVLTreeNode<T, P> tNode, T newItem) {
		int bf = balanceFactor(tNode);
		if (bf < -1) { // R case
			if (balanceFactor(tNode.getRight()) > 0) { // RL case
				tNode.setRight(R_Rotate(tNode.getRight()));
			}
			tNode = L_Rotate(tNode);
		} else if (bf > 1) { // L case
			if (balanceFactor(tNode.getLeft()) < 0) {
				tNode.setLeft(L_Rotate(tNode.getLeft())); // LR case
			}
			tNode = R_Rotate(tNode);
		}
		return tNode;
	}

	private AVLTreeNode<T, P> L_Rotate(AVLTreeNode<T, P> tNode) {
		// tNode의 parent를 기준으로 왼쪽으로 회전.
		AVLTreeNode<T, P> R = tNode.getRight();
		AVLTreeNode<T, P> RL = R.getLeft();

		R.setLeft(tNode);
		tNode.setRight(RL);
		updateHeight(tNode);
		updateHeight(R);

		return R;
	}

	private AVLTreeNode<T, P> R_Rotate(AVLTreeNode<T, P> tNode) {
		// tNode의 parent를 기준으로 오른쪽으로 회전.
		AVLTreeNode<T, P> L = tNode.getLeft();
		AVLTreeNode<T, P> LR = L.getRight();

		L.setRight(tNode);
		tNode.setLeft(LR);
		updateHeight(tNode);
		updateHeight(L);

		return L;
	}

	//////////////////////////////////////////////////////////////////////////////
	/* PRINT */

	public void printAVLTree() {
		String prnt = printPreOrder(root);
		System.out.println(prnt.substring(1)); // 맨 앞의 공백 제거
	}

	private String printPreOrder(AVLTreeNode<T, P> start) {
		// preorder traversal으로 전체 노드의 item을 출력한다.
		if (start == null)
			return "";
		return " " + start.getItem().toString() + printPreOrder(start.getLeft()) + printPreOrder(start.getRight());
	}

	//////////////////////////////////////////////////////////////////////////////
	/* SEARCH */

	public LinkedList<P> searchList(T searchKey) {
		// searchKey를 가진 node의 index list를 반환한다.
		AVLTreeNode<T, P> found = searchNode(root, searchKey);
		if (found == null)
			return null; // if not found, return null
		return found.getList();
	}

	private AVLTreeNode<T, P> searchNode(AVLTreeNode<T, P> curr, T searchKey) {
		if (curr == null)
			return null; // if not found, return null
		else {
			int keyComp = searchKey.compareTo(curr.getItem());
			if (keyComp == 0)
				return curr;
			else if (keyComp < 0)
				return searchNode(curr.getLeft(), searchKey);
			else
				return searchNode(curr.getRight(), searchKey);
		}
	}
}