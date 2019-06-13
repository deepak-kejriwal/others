package week1.ArraysAndStringsI;

import util.blocks.Utils;

/**
* 
* @author Deepak Kejriwal
*
*/
public class DutchNationalFlag {

	
	public static int[] dutchNationalFlag(int[] a, int x) {
		if (a == null || x < 0 || x >= a.length)
		return a;
		int pivot = a[x];
		int low = -1, mid = -1, high = a.length;
		while (mid + 1 < high) {
		if (a[mid + 1] > pivot) {
		Utils.swap(a, high - 1, mid + 1);
		high--;
		} else if (a[mid + 1] == pivot) {
		mid++;
		} else {
		Utils.swap(a, mid + 1, low + 1);
		mid++;
		low++;
		}
		}
		return a;
		}
}
