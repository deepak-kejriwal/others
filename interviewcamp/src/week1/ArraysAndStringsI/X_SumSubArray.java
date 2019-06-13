package week1.ArraysAndStringsI;

import util.blocks.Pair;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/*
 * Level: Medium Given an array of positive integers, find a subarray that sums
 * to a given number X.
 * 
 * 
 */
public class X_SumSubArray {

	public static Pair<Integer> xSumSubarray(int[] a, int x) {
		if (a == null || a.length == 0)
			return null;
		int i = 0, j = 0, sum = a[0];
		while (i < a.length) {
			if (i > j) { // i inched forward, bring j back to i
				j = i;
				sum = a[i];
			} else if (sum > x) {
				sum = sum - a[i++];
			} else if (sum < x) {
				if ((j + 1) < a.length)
					sum = sum + a[++j];
				else
					break; // reached end, cannot expand further
			} else {
				return new Pair<Integer>(i, j);
			}
		}
		return null;
	}
}
