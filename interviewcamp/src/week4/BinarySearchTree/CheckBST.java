package week4.BinarySearchTree;

import week4.BinarySearchTree.blocks.MinMaxPair;
import week4.BinaryTree.blocks.TreeNode;

/**
* 
* @author Deepak Kejriwal
*
*/
public class CheckBST {
	public static MinMaxPair isBST(TreeNode root) {
		if (root == null)
		// leaf node is always balanced
		return new MinMaxPair(Integer.MAX_VALUE, Integer.MIN_VALUE);
		MinMaxPair left = isBST(root.getLeft());
		MinMaxPair right = isBST(root.getRight());
		if (left == null || right == null)
		return null;
		if (left.getMax() > root.getValue()
		|| right.getMin() < root.getValue()) {
		return null;
		}
		// root is BST, return max and min
		int min = root.getLeft() == null ? root.value : left.getMin();
		int max = root.getRight() == null ? root.value : right.getMax();
		return new MinMaxPair(min, max);
		}
}
