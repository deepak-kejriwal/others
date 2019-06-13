package week2.ArraysAndStringsII;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class MathematicalOpsMultiply {
	public static int[] multiply(int[] a, int[] b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("Input is null");
		}
		int[] result = null;
		for (int i = 0; i < a.length; i++) {
			int j = 0, carry = 0;
			int[] p = new int[i + b.length + 1];
			while (j < b.length || carry != 0) {
				int prod = a[i] * (j < b.length ? b[j] : 0) + carry;
				carry = prod / 10;
				p[i + j] = prod % 10;
				j++;
			}
			result = result == null ? p : add(result, p);
		}
		return result;
	}

	private static int[] add(int[] result, int[] p) {
		return MathematicalOpsAdd.add(result, p);
	}

}
