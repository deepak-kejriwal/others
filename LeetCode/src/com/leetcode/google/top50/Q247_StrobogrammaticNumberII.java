package com.leetcode.google.top50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 
 A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]
 */
/*
 Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */
public class Q247_StrobogrammaticNumberII {
	public List<String> findStrobogrammatic(int n) {
	    return helper(n, n);
	}

	List<String> helper(int n, int m) {
	    if (n == 0) return new ArrayList<String>(Arrays.asList(""));
	    if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
	    
	    List<String> list = helper(n - 2, m);
	    
	    List<String> res = new ArrayList<String>();
	    
	    for (int i = 0; i < list.size(); i++) {
	        String s = list.get(i);
	        
	        if (n != m) res.add("0" + s + "0");
	        
	        res.add("1" + s + "1");
	        res.add("6" + s + "9");
	        res.add("8" + s + "8");
	        res.add("9" + s + "6");
	    }
	    
	    return res;
	}
	
	public List<String> findStrobogrammaticAlt(int n) {
	    this.n = n;//use n to avoid append 0 in the first recursion
	    List<String> result = new ArrayList<String>();
	    DFS(result, "", "", n);
	    return result;
	}

	int n;
	public void DFS(List<String> list, String left, String right, int k){
	    //we try to fill the list from two-side and converage at mid
	    if(k == 0){
	        //we have even length, so the remaining length is 0
	        list.add(left + right);
	        return;
	    }
	    
	    if(k == 1){
	        //we have odd length, so we need add a single mid, which could only be 0, 1, 8 
	        list.add(left + 0 + right);
	        list.add(left + 1 + right);
	        list.add(left + 8 + right);
	    
	        return;
	    }
	    
	    //otherwise we will append char pair in left and right part
	    
	    //0 is boundary case, we couldn't add 0 two the right and left bound, which would make num be 0...0
	    //and we don't have a valid num has len > 1, and starts with 0
	    if(k != n) DFS(list, left + 0, 0 + right, k-2);
	    
	    //for other pairs, we can add it freely
	    
	    DFS(list, left + 1, 1 + right, k - 2);
	    DFS(list, left + 6, 9 + right, k - 2);
	    DFS(list, left + 9, 6 + right, k - 2);
	    DFS(list, left + 8, 8 + right, k - 2);
	}
}
