package week4.BinaryTree;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class LowestCommonAncestor {
	public static TreeNode findLCA(TreeNode a, TreeNode b) {
		if (a == null || b == null)
			throw new NullPointerException("Input node is null");
		/*
		 * Notice we used pointers to 'a' (references in Java) because if we had
		 * modified 'a', we would have lost node.
		 */
		TreeNode aPointer = a, bPointer = b;
		// find aDepth
		int aDepth = -1;
		while (aPointer != null) {
			aDepth++;
			aPointer = aPointer.getParent();
		}
		// find bDepth
		int bDepth = -1;
		while (bPointer != null) {
			bDepth++;
			bPointer = bPointer.getParent();
		}
		// Raise the lower node
		TreeNode x = aDepth > bDepth ? a : b;
		for (int i = 0; i < Math.abs(aDepth - bDepth); i++) {
			x = x.getParent();
		}
		// Raise both until they meet (at LCA)
		TreeNode y = aDepth > bDepth ? b : a; // the node that wasn't raised
		while (x != y) {
			x = x.getParent();
			y = y.getParent();
		}
		return x; // can return either x or y here
	}

	public static TreeNode findLCAWithoutParent(TreeNode n /* root node */, final TreeNode a, final TreeNode b) {
		if (n == null)
			return null;
		if (n == a || n == b)
			return n;
		TreeNode leftLCA = findLCAWithoutParent(n.getLeft(), a, b);
		TreeNode rightLCA = findLCAWithoutParent(n.getRight(), a, b);
		if (leftLCA != null && rightLCA != null)
			return n;
		return leftLCA != null ? leftLCA : rightLCA;
	}
}
