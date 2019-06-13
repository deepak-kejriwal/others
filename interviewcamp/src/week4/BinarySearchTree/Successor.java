package week4.BinarySearchTree;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class Successor {
	public static TreeNode findSuccessor(TreeNode n, TreeNode root) {
		if (n.getRight() != null) {
			TreeNode current = n.getRight();
			while (current.getLeft() != null)
				current = current.getLeft();
			return current;
		} else {
			TreeNode current = root, successor = null;
			while (current != null) {
				if (current.getValue() > n.getValue()) {
					successor = current;
					current = current.getLeft();
				} else if (current.getValue() < n.getValue()) {
					current = current.getRight();
				} else if (current == n) {
					break;
				}
			}
			return successor;
		}
	}
}
