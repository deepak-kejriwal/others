package week1.BinarySearch;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/*
 * Level: Medium Given a sorted array of Integers, find the target. If the
 * target is not found, return the element closest to the target. A =
 * [1,2,4,5,7,8,9], Target = 6 -> Output Index = 3 or 4 (since both 5 and 7 are
 * equally close)
 * 
 */
public class RecordAndMoveOn {

	public static int closestElement(int[] a, int target) {
		if (a == null) {
			return -1;
		}
		int low = 0, high = a.length - 1;
		int result = -1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			result = record(a, mid, result, target);
			if (a[mid] > target) {
				high = mid - 1;
			} else if (a[mid] < target) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return result;
	}

	private static int record(int[] a, int mid, int result, int target) {
		if (result == -1 || Math.abs(a[mid] - target) < Math.abs(a[result] - target))
			return mid;
		return result;
	}
}
