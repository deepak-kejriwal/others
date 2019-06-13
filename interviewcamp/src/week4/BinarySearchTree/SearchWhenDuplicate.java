package week4.BinarySearchTree;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class SearchWhenDuplicate {
	public static TreeNode findFirstOccurence(TreeNode root, int target) {
		TreeNode current = root;
		TreeNode result = null;
		while (current != null) {
			if (current.getValue() > target) {
				current = current.getLeft();
			} else if (current.getValue() < target) {
				current = current.getRight();
			} else {
				result = current;
				current = current.getLeft();
			}
		}
		return result;
	}
}
