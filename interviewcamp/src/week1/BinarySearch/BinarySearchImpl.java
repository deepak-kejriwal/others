package week1.BinarySearch;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class BinarySearchImpl {

	public static <A extends Comparable<A>> int search(A[] a, A target) {
		if (a == null || target == null) {
			return -1;
		}
		int low = 0;
		int high = a.length - 1;
		return binarySearchOverRange(a, target, low, high);
	}

	public static <A extends Comparable<A>> int binarySearchOverRange(A[] a, A target, int low, int high) {
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid].compareTo(target) == 0) {
				return mid;
			} else if (a[mid].compareTo(target) < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}

	/*
	 * 
	 * Level: Easy Given a sorted array that can contain duplicates, find the first
	 * occurrence of the target element. For example: A = [1,3,4,6,6,6,7] and Target
	 * = 6, return index 3.
	 * 
	 * 
	 */

	public static int firstOccurence(int[] a, int target) {
		if (a == null) {
			return -1;
		}
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (a[mid] > target || (a[mid] == target && mid > 0 && a[mid - 1] == target)) {
				high = mid - 1;
			} else if (a[mid] < target) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
}
