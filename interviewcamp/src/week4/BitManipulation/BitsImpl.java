package week4.BitManipulation;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class BitsImpl {

	/*
	 * 
	 * Level: Easy 1. Given an integer, get the bit at a given index I. Return 0 or
	 * 1 2. Given an integer, set the bit at index I to a given value V .
	 * 
	 */

	public static int getBit(int i, int num) {
		return (num >> i) & 1;
	}

	public static int setBit(int i, int num, int value) {
		if (value == 1)
			return (1 << i) | num;
		else
			return ~(1 << i) & num;
	}

	/*
	 * Level: Easy Given a number N, swap bits i and j.
	 * 
	 */
	public static int swapBits(int num, int i, int j) {
		if (getBit(i, num) != getBit(j, num)) {
			int mask = (1 << i) | (1 << j);
			return num ^ mask;
		}
		return num;
	}

	/*
	 * 
	 * Level: Easy Given a number, reverse the order of its bits. For example: A =>
	 * 11110000, Result => 00001111 A => 01001101, Result => 10110010
	 * 
	 */
	public static int reverseBits(int num) {
		int i = 0, j = 31;
		while (i < j) {
			num = swapBits(num, i++, j--);
		}
		return num;
	}

	/*
	 * 
	 * Level: Easy Given an integer, count the number of bits in its binary
	 * representation. For example, given 6 => Binary: 000110 -> Result = 2 given 22
	 * => Binary: 010110 -> Result = 3 Note: The number of bits is same as the
	 * number of 1's in its binary representation.
	 * 
	 */
	public static int numBits(int num) {
		int count = 0;
		while (num != 0) {
			count++;
			num = num & (num - 1);
		}
		return count;
	}

	/*
	 * Level: Medium Find the complement of an integer. A complement has the
	 * number's bits flipped, starting from the most significant 1. For example, A
	 * => 00010001, Complement => 00001110 A => 00000111, Complement => 00000000
	 * 
	 */
	public static int complement(int num) {
		int mask = (1 << (logBase2(num) + 1)) - 1;
		return mask ^ num;
	}

	/*
	 * To calculate N log Base, divide log(N) by log(Base).
	 */
	private static int logBase2(int num) {
		return (int) (Math.log(num) / Math.log(2));
	}

	/*
	 * 
	 * Level: Easy Given an array with all numbers in [1,2,..,n] except one number,
	 * find the missing number. For example: A = [1,2,5,4] and n = 5, Missing Number
	 * => 3 A = [7,3,5,4,1,2] and n = 7, Missing Number => 6
	 * 
	 */
	public static int findMissingNumber(int[] a, int n) {
		if (a == null || a.length != (n - 1))
			throw new IllegalArgumentException();
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result ^= i;
		}
		for (int i = 0; i < a.length; i++) {
			result ^= a[i];
		}
		return result;
	}
}
