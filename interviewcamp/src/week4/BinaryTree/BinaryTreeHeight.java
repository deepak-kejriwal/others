package week4.BinaryTree;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class BinaryTreeHeight {
	// Top to bottom approach
	public static int getHeight(TreeNode root) {
		int[] result = { -1 };
		height(root, -1, result);
		return result[0];
	}

	public static void height(TreeNode n, int prevDepth, int[] maxDepth) {
		if (n == null)
			return;
		int currDepth = prevDepth + 1;
		if (currDepth > maxDepth[0])
			maxDepth[0] = currDepth;
		height(n.getLeft(), currDepth, maxDepth);
		height(n.getRight(), currDepth, maxDepth);
	}

	// Bottom to top approach
	public static int height(TreeNode n) {
		if (n == null)
			return -1;
		return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
	}
}
