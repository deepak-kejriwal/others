package week1.ArraysAndStringsI;

import java.util.HashMap;

import util.blocks.Pair;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/*
 * 
 * Level: Medium Given an array of integers, both -ve and +ve, find a contiguous
 * subarray that sums to 0. For example: [2,4,-2,1,-3,5,-3] --> [4,-2,1,-3]
 * 
 */
public class ZeroSumSubArray {

	public static Pair<Integer> zeroSumSubarray(int[] a) {
		if (a == null || a.length == 0)
			return null;
		int sum = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum == 0) {
				return new Pair<Integer>(0, i);
			}
			if (map.containsKey(sum)) {
				return new Pair<Integer>(map.get(sum) + 1, i);
			}
			map.put(sum, i);
		}
		return null; // not found
	}
}
