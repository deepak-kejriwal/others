package week1.ArraysAndStringsI;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/*
 * Level: Easy 2 Sum Problem: Given a sorted array of integers, find two numbers
 * in the array that sum to an integer X. For example, given a=[1,2,3,5,6,7] and
 * X=11, the answer would be 5 and 6, which sum to 11.
 * 
 * 
 */
public class TwoSum {

	public Integer[] twoSumAppraoch1(int[] a, int x) {

		long beforeUsedMem = getMemoryUsage();
		Integer[] result = null;
		// if not sorted, use Arrays.sort then time complexity is O(nlogn).
		if (a == null)
			return null;
		int start = 0, end = a.length - 1;
		while (start < end) {
			int sum = a[start] + a[end];
			if (sum < x) {
				start++;
			} else if (sum > x) {
				end--;
			} else {
				result = new Integer[] { start, end };
				break;
			}

		}
		long afterUsedMem = getMemoryUsage();
		long actualMemUsed = afterUsedMem - beforeUsedMem;
		System.out.println(actualMemUsed);
		return result;
	}

	// if not sorted approach 2 can be useful.
	public Integer[] twoSumAppraoch2(int[] a, int target) {
		long beforeUsedMem = getMemoryUsage();
		if (a == null)
			return null;
		Integer[] result = null;
		Set<Integer> set = new HashSet<>();
		for (int x : a) {
			int xComplement = target - x;
			if (set.contains(xComplement)) {
				result = new Integer[] { xComplement, x };
				break;
			} else {
				set.add(x);
			}
		}
		long afterUsedMem = getMemoryUsage();
		long actualMemUsed = afterUsedMem - beforeUsedMem;
		System.out.println(actualMemUsed);
		return result;
	}

	private long getMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();
	}

	public static void main(String[] args) {
		TwoSum twoSum = new TwoSum();
		long beforeUsedMem = twoSum.getMemoryUsage();	

		int[] arr = IntStream.rangeClosed(0, 10000000).toArray();
		int target = 10000000+10000000-1;

		Integer[] result = twoSum.twoSumAppraoch1(arr, target);
		long afterUsedMem = twoSum.getMemoryUsage();
		long actualMemUsed = afterUsedMem - beforeUsedMem;
		System.out.println(actualMemUsed);
		System.out.println(result[0] + ":" + result[1]);
	}
}
