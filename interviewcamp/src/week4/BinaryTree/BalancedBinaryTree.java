package week4.BinaryTree;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class BalancedBinaryTree {
	public static boolean isBalanced(TreeNode node) {
		return isBalancedHelper(node) != -1;
	}

	public static int isBalancedHelper(TreeNode node) {
		if (node == null)
			// Note: When we calculated the height of the tree, we returned -1 for
			// null nodes because we wanted height 0 for leaf nodes. In this case
			// either -1 or 0 works because we don't care about the exact number.
			// We only care about the difference between heights.
			return 0;
		int leftHeight = isBalancedHelper(node.getLeft());
		int rightHeight = isBalancedHelper(node.getRight());
		if (leftHeight == -1 || rightHeight == -1)
			return -1;
		if (Math.abs(leftHeight - rightHeight) > 1)
			return -1;
		return 1 + Math.max(leftHeight, rightHeight);
	}
}
