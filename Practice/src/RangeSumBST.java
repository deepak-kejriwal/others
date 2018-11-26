

/*
 * 
 * 
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

 

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
 * 
 * 
 */
public class RangeSumBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public int rangeSumBST(TreeNode<Integer> root, int L, int R) {
        if(root==null) return 0;
        int sum=root.data>=L&&root.data<=R?root.data:0;
        int lSum=rangeSumBST(root.left,L,R);
        int rSum=rangeSumBST(root.right,L,R);
        return sum+lSum+rSum;
    }
}
