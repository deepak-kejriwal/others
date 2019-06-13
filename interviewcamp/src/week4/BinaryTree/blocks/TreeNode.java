package week4.BinaryTree.blocks;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	public int value;
	boolean isVisited = false;

	public TreeNode() {
		super();
	}

	public TreeNode(int rootValue) {
		this.value=rootValue;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;

	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
}
