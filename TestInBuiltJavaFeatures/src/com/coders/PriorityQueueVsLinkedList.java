package com.coders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PriorityQueueVsLinkedList {

	public static void main(String[] args) {
		IntStream intStream = IntStream.range(1, 101);
		List<Integer> list = intStream.boxed().collect(Collectors.toList());
		System.out.println("List consist of number from 1 to 100");
		Collections.shuffle(list);
		long start = System.nanoTime();
		List<Integer> result = testPQHeapSort(list);
		long end = System.nanoTime();
		System.out.println("Sorted: " + list.equals(result));
		System.out.println("PriorityQueue Time Taken in nano second: " + (end - start));
		start = System.nanoTime();
		result = testCollectionMergeSort(list);
		end = System.nanoTime();
		System.out.println("Sorted: " + list.equals(result));
		System.out.println("Collection.sort Time Taken in nano second: " + (end - start));
		System.out.println("It demonstrate that Priority Queue Heap Sort is faster");
	}

	private static List<Integer> testCollectionMergeSort(List<Integer> list) {
		List<Integer> result = new ArrayList<>();
		LinkedList<Integer> ll = new LinkedList<>();
		for (Integer i : list) {
			ll.add(i);
		}
		Collections.sort(ll);
		while (!ll.isEmpty()) {
			result.add(ll.removeFirst());
		}
		return result;
	}

	private static List<Integer> testPQHeapSort(List<Integer> list) {
		List<Integer> result = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (Integer i : list) {
			pq.add(i);
		}
		while (!pq.isEmpty()) {
			result.add(pq.remove());
		}
		return result;
	}

}
