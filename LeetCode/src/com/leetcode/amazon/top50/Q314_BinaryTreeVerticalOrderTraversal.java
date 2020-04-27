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
public class Q314_BinaryTreeVerticalOrderTraversal {
	public int index = 0;
	public TreeMap<Integer, List<Integer>> tm;

	public class Pair {
	    TreeNode node;
	    int index;
	    public Pair(TreeNode n, int i) {
	        node = n;
	        index = i;
	    }
	}

	public List<List<Integer>> verticalOrder(TreeNode root) {
	    List<List<Integer>> res = new ArrayList<List<Integer>>();
	    tm = new TreeMap<Integer, List<Integer>>();
	    if (root == null) return res;
	    
	    Queue<Pair> q = new LinkedList<Pair>();
	    q.offer(new Pair(root, 0));
	    
	    while (!q.isEmpty()) {
	        Pair cur = q.poll();
	        if (!tm.containsKey(cur.index)) tm.put(cur.index, new ArrayList<Integer>());
	        tm.get(cur.index).add(cur.node.val);
	        
	        if (cur.node.left != null) q.offer(new Pair(cur.node.left, cur.index-1));
	        if (cur.node.right != null) q.offer(new Pair(cur.node.right, cur.index+1));
	    }
	    
	    for (int key : tm.keySet()) res.add(tm.get(key));
	    return res;
	}
	
	  public class TreeNode {
		     int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
}
