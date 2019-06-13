package week1.ArraysAndStringsI;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class MaximumSumSubarray {
	public static Integer maximumSumSubarray(int[] a) {
		if (a == null)
			return 0;
		int maxSum = 0, maxEndingHere = 0;
		for (int i = 0; i < a.length; i++) {
			maxEndingHere = Math.max(maxEndingHere + a[i], 0);
			maxSum = Math.max(maxSum, maxEndingHere);
		}
		return maxSum;
	}
}
