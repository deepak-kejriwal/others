

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> {
	private boolean enablePrettyPrint = true;

	T data;
	public TreeNode<T> left;
	public TreeNode<T> right;

	public TreeNode(T x) {
		data = x;
	}

	@Override
	public String toString() {
		if (enablePrettyPrint) {
			List<Object> list = levelOrder(this);
			return toString(list);
		}
		return super.toString();
	}

	private String toString(List<Object> tree) {

		String output = "";
		int depth = 0;
		int arraySpots = tree.size();
		while (arraySpots > 0) {
			arraySpots /= 2;
			++depth;
		}
		convertTreeToFullTree(tree, depth);
		int maxWidth = (int) (Math.pow(2, depth));

		int charWidth = 4 * maxWidth;

		int idx = 0;

		for (int i = 0; i < depth; ++i) {

			int level = (int) Math.pow(2, i);
			int preSpace = 0;
			for (int j = 0; j < level; ++j) {

				preSpace = (int) ((charWidth / (Math.pow(2, (i + 2))) - 1));

				for (int k = 0; k < preSpace; ++k) {

					output += " ";

				}

				output += tree.get(idx);

				++idx;

				if (idx >= tree.size()) {

					output += "\n";

					break;

				}

				for (int k = 0; k < preSpace; ++k) {

					output += " ";

				}
				output += " ";

			}

			output += "\n";
			if (idx >= tree.size()) {
				continue;
			}
			for (int j = 0; j < level; ++j) {

				int x = preSpace / 2;
				int y = x / 2;
				x = x + y + 1;
				for (int k = 0; k < x; ++k) {

					output += " ";

				}

				output += "/";

				for (int k = 0; k < y; ++k) {

					output += " ";

				}
				output += " ";

				for (int k = 0; k < y; ++k) {

					output += " ";

				}
				output += "\\";
				for (int k = 0; k < x; ++k) {

					output += " ";

				}
				output += " ";
			}
			output += "\n";

		}

		return output;

	}

	private void convertTreeToFullTree(List<Object> tree, int depth) {
		// Making tree as full tree start
		int maxNode = (int) (Math.pow(2, depth) - 1);
		while (tree.size() != maxNode) {
			tree.add("-");
		}
		// Making tree as full tree end
	}

	private List<Object> levelOrder(TreeNode<T> root) {
		int count = 0;
		List<Object> levelTraversal = new ArrayList<>();
		if (root != null) {
			Deque<TreeNode<T>> tn = new LinkedList<>();
			tn.add(root);
			count++;
			while (!tn.isEmpty() && count != 0) {
				TreeNode<T> node = tn.remove();
				if (node != null) {
					count--;
					if (node.left != null)
						count++;
					tn.add(node.left);
					if (node.right != null)
						count++;
					tn.add(node.right);
				}

				levelTraversal.add(node != null ? node.data : "-");
			}
		}
		return levelTraversal;
	}

	public static void main(String[] args) {
		TreeNode<Integer> node = new TreeNode<>(1);
		TreeNode<Integer> left = new TreeNode<>(2);
		TreeNode<Integer> right = new TreeNode<>(3);
		node.left = left;
		node.right = right;
		TreeNode<Integer> right1 = new TreeNode<>(4);
		TreeNode<Integer> right2 = new TreeNode<>(5);
		right.left = right1;
		right.right = right2;
		left.left = right1;
		left.right = right2;
		TreeNode<Integer> right3 = new TreeNode<>(6);
		TreeNode<Integer> right4 = new TreeNode<>(7);
		right1.left = right3;
		right1.right = right4;
		System.out.println("Tree Structure:\n");
		System.out.println(node);
		System.out.println("Level Order Structure:");
		System.out.println(node.levelOrder(node));
	}
}
