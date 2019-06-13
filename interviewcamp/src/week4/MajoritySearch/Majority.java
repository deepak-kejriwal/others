package week4.MajoritySearch;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/*
 * 
 * Level: Medium
You are given an array of numbers. Find a number that occurs more than half the time.
Find the number. For example,
A => [4,2,3,4,4,5,4,4,5,4,4], Result is 4
A => [2,4,6,6,3,6,7,9,5,3], There is no result because there is no majority
 * 
 */
public class Majority {

	public static Integer majorityFind(int[] a) {
		if (a == null || a.length == 0)
			return null;
		int candidate = a[0];
		int count = 1;
		for (int i = 1; i < a.length; i++) {
			if (a[i] == candidate) {
				count++;
			} else if (count > 0) {
				count--;
			} else {
				candidate = a[i];
				count = 1;
			}
		}
		// check array, verify candidate is majority
		count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == candidate)
				count++;
		}
		return count > a.length / 2 ? candidate : null;
	}

}
