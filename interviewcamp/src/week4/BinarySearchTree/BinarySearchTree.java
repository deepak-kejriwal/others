package week4.BinarySearchTree;

import week4.BinaryTree.blocks.TreeNode;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class BinarySearchTree {
	TreeNode root;

	public BinarySearchTree() {
	}

	public void addTreeNode(int value) {
		TreeNode parent = null;
		TreeNode current = root;
		while (current != null) {
			parent = current;
			// In this case, if there is a duplicate node, it will end up
			// on the left side. You can discuss with the interviewer.
			current = current.getValue() < value ? current.getRight() : current.getLeft();
		}
		if (parent == null) {
			root = new TreeNode(value);
		} else if (parent.getValue() < value) {
			parent.setRight(new TreeNode(value));
		} else {
			parent.setLeft(new TreeNode(value));
		}
	}

	public TreeNode search(int target) {
		TreeNode current = root;
		while (current != null) {
			if (current.getValue() < target) {
				current = current.getRight();
			} else if (current.getValue() > target) {
				current = current.getLeft();
			} else {
				return current;
			}

		}
		return null;
	}

	public void deleteTreeNode(TreeNode node, TreeNode parent) {
		if (node.getLeft() == null && node.getRight() == null) {
			replaceChild(parent, node, null);
		} else if (node.getLeft() == null) { // only left is null
			replaceChild(parent, node, node.getRight());
		} else if (node.getRight() == null) {
			replaceChild(parent, node, node.getLeft());
		} else {
			TreeNode successorParent = node;
			TreeNode successor = node.getRight();
			while (successor.getLeft() != null) {
				successorParent = successor;
				successor = successor.getLeft();
			}
			node.setValue(successor.getValue());
			deleteTreeNode(successor, successorParent);
		}
	}

	/*
	 * Helper function. Ask interviewer if they want you to implement.
	 */
	private void replaceChild(TreeNode parent, TreeNode oldChild, TreeNode newChild) {
		if (parent.getLeft() == oldChild) {
			parent.setLeft(newChild);
		} else if (parent.getRight() == oldChild) {
			parent.setRight(newChild);
		} else {
			throw new IllegalArgumentException("Invalid parent-child.");
		}
	}
}
