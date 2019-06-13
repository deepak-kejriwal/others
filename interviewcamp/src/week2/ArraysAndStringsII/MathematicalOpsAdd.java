package week2.ArraysAndStringsII;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class MathematicalOpsAdd {

	public static void main(String[] args) {
		int[] a = { 3, 4, 6, 8 };
		int[] b = { 1, 3, 4 };
		int[] c = add(a, b);
		int[] d = modiFiedAdd(a, b);
		System.out.println();
	}

	public static int[] add(int[] a, int[] b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("Input is null");
		}
		int[] larger = a.length > b.length ? a : b;
		int[] smaller = larger == a ? b : a;
		int[] result = new int[larger.length + 1];
		int carry = 0;
		for (int i = 0; i < larger.length; i++) {
			int sum = larger[i] + (i < smaller.length ? smaller[i] : 0) + carry;
			carry = sum / 10;
			result[i] = sum % 10;
		}
		if (carry != 0)
			result[larger.length] = carry;
		return result;
	}

	public static int[] modiFiedAdd(int[] a, int[] b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("Input is null");
		}
		int[] larger = a.length > b.length ? a : b;
		int[] smaller = larger == a ? b : a;

		int carry = 0;
		for (int i = 0; i < larger.length; i++) {
			int sum = larger[i] + (i < smaller.length ? smaller[i] : 0) + carry;
			carry = sum / 10;
			larger[i] = sum % 10;
		}
		if (carry != 0) {
			int[] result = new int[larger.length + 1];
			for (int i = 0; i < larger.length; ++i) {
				result[i] = larger[i];
			}
			result[larger.length] = carry;
			return result;
		}

		return larger;
	}
}
