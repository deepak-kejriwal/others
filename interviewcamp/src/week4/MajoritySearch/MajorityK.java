package week4.MajoritySearch;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/*
 * 
 * Level: Hard
You are given an array of numbers. Find a number that occurs more than Length/K of the time.
For example:
A = [2,4,5,2,4,2,2,1,5] and K = 3, Result = 2, which occurs more than Length/3 times.
B = [2,4,5,2,4,2,6,1,5] and K = 3, No result as there is no number occurring > Length/3 times.
 * 
 */
public class MajorityK {

	public static Integer find(int[] a, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			// update count
			if (map.containsKey(a[i])) {
				map.put(a[i], map.get(a[i]) + 1);
			} else {
				map.put(a[i], 1);
			}
			// discard if K unique elements exist
			if (map.size() == k) {
				Iterator<Integer> iter = map.keySet().iterator();
				while (iter.hasNext()) {
					int key = (Integer) iter.next();
					if (map.get(key) == 1)
						iter.remove(); // use iter's remove method to remove keys mid-iteration
					else
						map.put(key, map.get(key) - 1);
				}
			}
		}
		// We now have at most k candidates. set all counts to 0
		for (Integer key : map.keySet()) {
			map.put(key, 0);
		}
		// calculate count of each candidate
		for (int i = 0; i < a.length; i++) {
			if (map.containsKey(a[i]))
				map.put(a[i], map.get(a[i]) + 1);
		}
		// check if any of them have target majority
		for (Integer key : map.keySet()) {
			if (map.get(key) > a.length / k)
				return key;
		}
		return null;
	}

}
