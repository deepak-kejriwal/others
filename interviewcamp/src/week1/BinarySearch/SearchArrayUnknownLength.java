package week1.BinarySearch;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class SearchArrayUnknownLength {

	public static <A extends Comparable<A>> int findWithUnknownLength(A[] a, A target) {
		if (a == null || a.length == 0 || target == null) {
			return -1;
		}
		int high = 1; // 1,2,4,8,16,32..
		int lastIndex = -1;
		int low=0;
		while (true) { // consider putting a limit here, for e.g, dont go more than index 1 million.
						// Discuss with interviewer.
			try {
				A temp = a[high];
				if(target.compareTo(temp)<=0) {
					lastIndex=high;
					low=high/2;
					break;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				lastIndex = binarySearchForLastIndex(a, high / 2, high);
				low=high/2;
				break;
			}
			high *= 2;
		}
		return BinarySearchImpl.binarySearchOverRange(a, target, low, lastIndex);
	}

	private static <A extends Comparable<A>> int binarySearchForLastIndex(A[] a, int low, int high) {
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			try {
				A temp = a[mid];
			} catch (ArrayIndexOutOfBoundsException e) { // mid is out of bounds, go to lower half
				high = mid - 1;
				continue;
			}
			try {
				A temp = a[mid + 1];
			} catch (ArrayIndexOutOfBoundsException e) { // mid + 1 is out of bounds, mid is last index
				return mid;
			}
			low = mid + 1; // both mid and mid + 1 are inside array, mid is not last index.
		}
		return -1; // this subarray does not have end of the array
	}

}
