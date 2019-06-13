package week1.ArraysAndStringsI;

import util.blocks.Pair;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/*
 * 
 * Level: Medium Given an array of integers, find the shortest subarray, that if
 * sorted, results in the entire array being sorted. [1,2,4,5,3,5,6,7,9] -->
 * [4,5,3] - If you sort from indices 2 to 4, the entire array is sorted.
 * [1,3,5,2,6,4,7,8,9] --> [3,5,2,6,4] - indices 1 to 5
 * 
 */
public class UnsortedSubarray {
	public static Pair<Integer> shortestUnsortedSubarray(Integer[] a) {
		int i, j;
		for (i = 0; i < a.length - 1; i++) { // find start of dip
			if (a[i + 1] < a[i])
				break;
		}
		for (j = a.length - 1; j > 0; j--) { // find start of bump
			if (a[j - 1] > a[j])
				break;
		}
		if (i >= (a.length - 1) || j <= 0) // no dip or bump
			return null;
		// find min and max of a[i..j]
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (int k = i; k <= j; k++) {
			if (a[k] > max)
				max = a[k];
			if (a[k] < min)
				min = a[k];
		}
		// expand i and j outward
		while (i > 0 && a[i - 1] > min) {
			i--;
		}
		while (j < (a.length - 1) && a[j + 1] < max) {
			j++;
		}
		return new Pair<Integer>(i, j);
	}
}
