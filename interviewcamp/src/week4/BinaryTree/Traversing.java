package week4.BinaryTree;

import java.util.Stack;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class Traversing {
	public static void preOrderVisit(TreeNode n) {
		if (n == null)
			return;
		System.out.println(n.getValue());
		preOrderVisit(n.getLeft());
		preOrderVisit(n.getRight());
	}

	public static void inOrderVisit(TreeNode n) {
		if (n == null)
			return;
		inOrderVisit(n.getLeft());
		System.out.println(n.getValue());
		inOrderVisit(n.getRight());
	}

	public static void postOrderVisit(TreeNode n) {
		if (n == null)
			return;
		postOrderVisit(n.getLeft());
		postOrderVisit(n.getRight());
		System.out.println(n.getValue());
	}

	public static void inOrderIterative(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node.isVisited())
				System.out.println(node.getValue());
			else {
				node.setVisited(true);
				if (node.getRight() != null)
					stack.push(node.getRight());
				stack.push(node);
				if (node.getLeft() != null)
					stack.push(node.getLeft());
			}
		}
	}

	public static void inOrderWithoutMarker(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.getLeft();
			} else {
				node = stack.pop();
				System.out.println(node.getValue());
				node = node.getRight();
			}
		}
	}
}
