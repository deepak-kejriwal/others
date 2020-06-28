package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q282_ExpressionAddOperators {

	public static void main(String[] args) {
		Q282_ExpressionAddOperators main = new Q282_ExpressionAddOperators();
		System.out.println(main.addOperators("1234", 6));
	}
	
    public List<String> addOperators(String num, int target) {
        char[] nums = num.toCharArray();
        Stack<String> aux = new Stack<>();
        List<String> res = new ArrayList<>();
        aux.push(Integer.toString(nums[0]-'0'));
        helper(nums, res , aux, nums[0]-'0', target, 1);
        return res;
    }
    
    public void helper(char[] nums, List<String> res , Stack<String> aux, int value, int target, int index){
        if(value == target && index == nums.length){
            System.out.println(index);
             res.add(String.join("",aux));
        }
        
        for(int i=index; i<nums.length; i++){
            int v = nums[i]-'0';
            
            aux.push("*");
            aux.push(Integer.toString(v));
            helper(nums, res , aux, value*v, target, i+1);
            aux.pop();
            aux.pop();
            
            aux.push("+");
            aux.push(Integer.toString(v));
            helper(nums, res , aux, value+v, target, i+1);
            aux.pop();
            aux.pop();
            
            aux.push("-");
            aux.push(Integer.toString(v));
            helper(nums, res , aux, value-v, target, i+1); 
            aux.pop();
            aux.pop();
                
            int last = Integer.valueOf(aux.peek());            
            aux.push(Integer.toString(v));
            helper(nums, res , aux, last*10+v, target, i+1);

            aux.pop();
        }

    }

}
