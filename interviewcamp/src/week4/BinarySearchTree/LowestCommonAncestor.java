package week4.BinarySearchTree;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class LowestCommonAncestor {
	public static TreeNode findLCA(TreeNode root, TreeNode a, TreeNode b) {
		TreeNode current = root;
		while (current != null) {
			if (current.getValue() < a.getValue() && current.getValue() < b.getValue())
				current = current.getRight();
			else if (current.getValue() > a.getValue() && current.getValue() > b.getValue())
				current = current.getLeft();
			else
				return current;
		}
		return null;
	}
}