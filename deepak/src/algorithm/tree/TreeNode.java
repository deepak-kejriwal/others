package algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
	int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	@Override
	public String toString() {		
		return levelOrder(this).toString();
	}
	
	public List<Integer> levelOrder(TreeNode root) {
		List<Integer> levelTraversal=new ArrayList<>();
		if(root!=null) {
			Deque<TreeNode> tn=new LinkedList<>();
			tn.add(root);
			while(!tn.isEmpty()) {
				TreeNode node=tn.remove();
				if(node.left!=null) tn.add(node.left);
				if(node.right!=null) tn.add(node.right);
				levelTraversal.add(node.val);
			}
		}
		return levelTraversal;
	}
}
