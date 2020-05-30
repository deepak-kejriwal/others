package week1.RecursionAndBacktracking;

import java.util.Arrays;

public class PrintCombos {
	public static void main(String[] args) {
		printCombos(new int[]{1,2,3},3);
	}

	public static void printCombos(int[] a, int x) {
		if (a == null || a.length == 0 || x > a.length)
			return;
		int[] buffer = new int[x];
		printCombosHelper(a, buffer, 0, 0);
	}

	public static void printCombosHelper(int[] a, int[] buffer, int startIndex, int bufferIndex) {
		// termination cases - buffer full
		if (bufferIndex == buffer.length) {
			printArray(buffer);
			return;
		}
		if (startIndex == a.length) {
			return;
		}
		// find candidates that go into current buffer index
		for (int i = startIndex; i < a.length; i++) {
			// place item into buffer
			buffer[bufferIndex] = a[i];
			// recurse to next buffer index
			printCombosHelper(a, buffer, i + 1, bufferIndex + 1);
		}
	}

	private static void printArray(int[] buffer) {

		System.out.println(Arrays.toString(buffer));

	}
}
