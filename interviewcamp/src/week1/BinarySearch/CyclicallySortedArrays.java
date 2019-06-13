package week1.BinarySearch;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/*
 * Level: Easy Given an array that is cyclically sorted, find the minimum
 * element. A cyclically sorted array is a sorted array rotated by some number
 * of elements. Assume all elements are unique. For example: A = [4,5,1,2,3],
 * which is just [1,2,3,4,5] rotated by 2 Result = index 2
 * 
 */
public class CyclicallySortedArrays {
	public static int cyclicallySortedMin(int[] a) {
		if (a == null) {
			return -1;
		}
		int low = 0, high = a.length - 1, right = a[a.length - 1];
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] <= right && (mid == 0 | a[mid - 1] > a[mid])) {
				return mid;
			} else if (a[mid] > right) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}
}
