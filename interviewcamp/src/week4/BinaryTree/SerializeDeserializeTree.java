package week4.BinaryTree;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class SerializeDeserializeTree {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	public TreeNode construct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
		if (preStart > preEnd || inStart > inEnd) {
			return null;
		}
		// create root node
		int rootValue = preorder[preStart];
		TreeNode root = new TreeNode(rootValue);
		// find root value's index in inorder traversal
		int k = -1;
		for (int i = 0; i < inorder.length; i++) {
			if (rootValue == inorder[i]) {
				k = i;
				break;
			}
		}
		if (k == -1)
			throw new IllegalArgumentException("Value mismatch in inorder and preorder traversals");
		// add left and right subtrees to root node
		root.left = construct(preorder, preStart + 1, preStart + (k - inStart), inorder, inStart, k - 1);
		root.right = construct(preorder, preStart + (k - inStart) + 1, preEnd, inorder, k + 1, inEnd);
		return root;
	}
}
