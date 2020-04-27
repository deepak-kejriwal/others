package com.leetcode.amazon.top50;

import java.util.Stack;

/*
 * 
 Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 */
public class Q772_BasicCalculatorIII {
	public int calculateSol1(String s) {
		s = s.replaceAll("\\s+", "");
		Stack<Integer> stack = new Stack<>();
		char sign = '+';
		for (int i = 0; i < s.length();) {
			char c = s.charAt(i);
			if (c == '(') {
				// find the block and use the recursive to solve
				int l = 1;
				int j = i + 1;
				while (j < s.length() && l > 0) {
					if (s.charAt(j) == '(')
						l++;
					else if (s.charAt(j) == ')')
						l--;
					j++;
				}
				int blockValue = calculateSol1(s.substring(i + 1, j - 1));
				i = j;
				if (sign == '+') {
					stack.push(blockValue);
				} else if (sign == '-') {
					stack.push(-blockValue);
				} else if (sign == '*') {
					stack.push(stack.pop() * blockValue);
				} else if (sign == '/') {
					stack.push(stack.pop() / blockValue);
				}
			} else if (Character.isDigit(c)) {
				int j = i;
				int value = 0;
				while (j < s.length() && Character.isDigit(s.charAt(j))) {
					value = 10 * value + (s.charAt(j) - '0');
					j++;
				}
				i = j;
				if (sign == '+') {
					stack.push(value);
				} else if (sign == '-') {
					stack.push(-value);
				} else if (sign == '*') {
					stack.push(stack.pop() * value);
				} else if (sign == '/') {
					stack.push(stack.pop() / value);
				}
			} else {
				sign = c;
				i++;
			}
		}
		int res = 0;
		while (!stack.isEmpty()) {
			res += stack.pop();
		}
		return res;
	}
	
	public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
		int len;
		if (s == null || (len = s.length()) == 0)
			return 0;
		Stack <Integer> stack = new Stack<>();
		int num = 0;
		char sign = '+';
		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == '(') {
				// find the block and use the recursive to solve
				int l = 1;
				int j = i + 1;
				while (j < s.length() && l > 0) {
					if (s.charAt(j) == '(')
						l++;
					else if (s.charAt(j) == ')')
						l--;
					j++;
				}
				num = calculate(s.substring(i + 1, j - 1));
				i = j;
               // System.out.print(num);
			} else if (Character.isDigit(s.charAt(i))) {
					num = num * 10 + s.charAt(i) - '0';
			}
			if (i>=len-1||(!Character.isDigit(s.charAt(i))) ) {
				if (sign == '-') {
					stack.push(-num);
				}
				if (sign == '+') {
					stack.push(num);
				}
				if (sign == '*') {
					stack.push(stack.pop() * num);
				}
				if (sign == '/') {
					stack.push(stack.pop() / num);
				}
                if(i<len){
                   	sign = s.charAt(i); 
                }
				num = 0;
			}
		}

		int re = 0;
		for (int i: stack) {
			re += i;
		}
		return re;
	}
}
