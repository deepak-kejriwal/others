package com.leetcode.amazon.top50;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

/*
 Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
 */
/*
 * 
 https://leetcode.com/problems/binary-tree-vertical-order-traversal/discuss/76401/5ms-Java-Clean-Solution
 */
public class Q314_BinaryTreeVerticalOrderTraversalAlt2 {

	public List<List<Integer>> verticalOrder(TreeNode root) {
	    List<List<Integer>> cols = new ArrayList<>();
	    if (root == null) {
	        return cols;
	    }
	    
	    int[] range = new int[] {0, 0};
	    getRange(root, range, 0);
	    
	    for (int i = range[0]; i <= range[1]; i++) {
	        cols.add(new ArrayList<Integer>());
	    }
	    
	    Queue<TreeNode> queue = new LinkedList<>();
	    Queue<Integer> colQueue = new LinkedList<>();
	    
	    queue.add(root);
	    colQueue.add(-range[0]);
	    
	    while (!queue.isEmpty()) {
	        TreeNode node = queue.poll();
	        int col = colQueue.poll();
	        
	        cols.get(col).add(node.val);
	        
	        if (node.left != null) {
	            queue.add(node.left);   
	            colQueue.add(col - 1);
	        } 
	        if (node.right != null) {
	            queue.add(node.right);
	            colQueue.add(col + 1);
	        }
	    }
	    
	    return cols;
	}

	public void getRange(TreeNode root, int[] range, int col) {
	    if (root == null) {
	        return;
	    }
	    range[0] = Math.min(range[0], col);
	    range[1] = Math.max(range[1], col);
	    
	    getRange(root.left, range, col - 1);
	    getRange(root.right, range, col + 1);
	}
	
	  public class TreeNode {
		     int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
}
