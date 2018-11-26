package com.coders;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author deepak1037
 *
 */
public class JavaPriorityQueue {

	public static void main(String[] args) {
		List<Integer> list = Stream.of(1, 2, 3).collect(Collectors.toList());
		PriorityQueue<Integer> pq = new PriorityQueue<>(list);
		System.out.println("Sequence of poll from PQ: [" + pq.poll() + "," + pq.poll() + "," + pq.poll() + "]");
		// Re-initialize pq with comparator
		pq = new PriorityQueue<>((x, y) -> y.compareTo(x));
		pq.addAll(list);
		System.out.println("Sequence of poll from PQ with inverted comparator: [" + pq.poll() + "," + pq.poll() + ","
				+ pq.poll() + "]");
		System.out.println("Difference between poll() and remove()");
		System.out.println("Using poll on empty return null: " + pq.poll());

		System.out.println("Using remove on empty throw exception: " + pq.remove());
	}

}
