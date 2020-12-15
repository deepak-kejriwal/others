package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q425_WordSquares {

	public static void main(String[] args) {
		Q425_WordSquares impl = new Q425_WordSquares();
		String[] words = {"area","lead","wall","lady","ball"};
		
		System.out.println(impl.wordSquares(words));
	}
	
	
    class Node {
        Node[] children = new Node[26];
        String val = null;
        
        public String toString() {
        	return Arrays.toString(children);
        }
    }
    
    private Node root = new Node();
    
    private void addToTrie(String s, Node root){
        for(int i = 0; i < s.length(); i++){
            int ind = s.charAt(i) - 'a';
            if(root.children[ind] == null){
                root.children[ind] = new Node();
            }
            root = root.children[ind];
        }
        root.val = s;
    }
    
    private void dfs(int row, int col, Node[] rows, List<List<String>> res){
        if(row == rows.length){
            List<String> temp = new ArrayList<>(rows.length);
            for(int i = 0; i < rows.length; i++){
                temp.add(rows[i].val);
            }
            res.add(temp);
            return;
        }
        
        if(col < rows.length){
            Node currow = rows[row];
            Node curcol = rows[col];
            for(int i = 0; i < 26; i++){
                if(currow.children[i] != null && curcol.children[i] != null){
                    rows[row] = currow.children[i];
                    rows[col] = curcol.children[i];
                    dfs(row, col + 1, rows, res);
                }
            }
            rows[row] = currow;
            rows[col] = curcol;
        }else{
            dfs(row + 1, row + 1, rows, res);
        }
    }
    
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if(words == null || words.length == 0 || words[0] == null || words[0].length() == 0) return res;
        for(String s : words){
            addToTrie(s, root);
        }
        Node[] rows = new Node[words[0].length()];
        Arrays.fill(rows, root);
        dfs(0, 0, rows, res);
        return res;
    }

}
