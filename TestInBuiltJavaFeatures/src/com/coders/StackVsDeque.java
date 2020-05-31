package com.coders;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class StackVsDeque {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.push("d");
		System.out.println(String.join(":", stack));
		//Output: a:b:c:d , which follow FIFO opposite of LIFO of Stack
		
		Iterator<String> itr = stack.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		Deque<String> deque = new LinkedList<>();
		deque.push("a");
		deque.push("b");
		deque.push("c");
		deque.push("d");
		System.out.println(String.join(":", deque));
		//Output: d:c:b:a , which follow LIFO pattern of Stack
		
		itr = deque.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

}
