package com.leetcode.google.top50;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
 * 
 A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false
 */
public class Q246_StrobogrammaticNumber {

	public boolean isStrobogrammaticSol1(String num) {
	    for (int i=0, j=num.length()-1; i <= j; i++, j--)
	        if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
	            return false;
	    return true;
	}
    public boolean isStrobogrammaticSol2(String num) {
        HashSet<String> set = new HashSet<>();
        set.add("0");
        set.add("1");
        set.add("8");
        set.add("00");
        set.add("11");
        set.add("88");
        set.add("69");
        set.add("96");
        
        for(int left = 0, right = num.length() - 1; left <= right; left++, right--) {
            if(!set.contains(num.charAt(left) + "" + num.charAt(right)))
               return false;
        }           
        return true;
    }
    
    public boolean isStrobogrammatic3(String num) {
        if (num == null) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('0','0'); map.put('1', '1'); map.put('6','9'); 
        map.put('8','8'); map.put('9','6');
        int l = 0, r = num.length()-1;
        while (l < r) {
            char ll = num.charAt(l), rr = num.charAt(r);
            if (! map.containsKey(ll) || rr != map.get(ll)) {
                return false;
            }
            l++;
            r--;
        }
        return (l > r ) || num.charAt(l) == '0' || num.charAt(l) == '1' || num.charAt(l) == '8';
    }
}
