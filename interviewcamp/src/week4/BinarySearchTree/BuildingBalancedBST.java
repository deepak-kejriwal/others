package week4.BinarySearchTree;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class BuildingBalancedBST {
	public static TreeNode getTree(int[] a, int start, int end) {
		if (start > end || oob(a, start) || oob(a, end))
			return null;
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(a[mid]);
		root.setLeft(getTree(a, start, mid - 1));
		root.setRight(getTree(a, mid + 1, end));
		return root;
	}

	/*
	 * Helper code. Implement only if the interviewer wants you to.
	 */
	private static boolean oob(int[] a, int index) {
		return index < 0 || index >= a.length;
	}
}
