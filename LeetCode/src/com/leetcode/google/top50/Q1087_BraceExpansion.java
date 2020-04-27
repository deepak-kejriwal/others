package com.leetcode.google.top50;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
 A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.

 

Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
Example 2:

Input: "abcd"
Output: ["abcd"]
 

Note:

1 <= S.length <= 50
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
public class Q1087_BraceExpansion {
	   public String[] expand(String S) 
	    {
	        List<String> result = new ArrayList();
	        dfs(result, "", S, 0);
	        Collections.sort(result);
	        return result.toArray(new String[0]);
	    }
	    
	    
	    void dfs(List<String> result, String curr, String s, int pos)
	    {
	        if (pos == s.length())
	        {
	            result.add(curr);
	            return;
	        }
	        
	        if (s.charAt(pos) == '{')
	        {
	            // find all the options
	            int j = pos + 1;
	            while (s.charAt(j) != '}')
	            {
	                ++j;
	            }
	            String[] o = s.substring(pos + 1, j).split(",");
	            for (String letter : o)
	            {
	                dfs(result, curr + letter, s, j + 1);
	            }
	        }
	        else
	        {
	            dfs(result, curr + s.charAt(pos), s, pos + 1);
	        }
	    }
}
