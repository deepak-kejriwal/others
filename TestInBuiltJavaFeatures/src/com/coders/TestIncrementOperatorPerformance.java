package com.coders;

public class TestIncrementOperatorPerformance {
	private static long counter = 3000000000l;

	public static void main(String[] args) {

		testPositiveIncrement();
		testNegativeIncrement();
		// testPostIncrement();
		testPreIncrement();
	}

	private static void testNegativeIncrement() {
		long count = 0;
		long st = System.currentTimeMillis();
		for (int i = -2147483648; i < 1; i++) {
			count++;
		}
		long ed = System.currentTimeMillis();
		System.out.println("testNegativeIncrement: " + (ed - st));
	}

	private static void testPositiveIncrement() {
		long count = 0;
		long st = System.currentTimeMillis();
		for (int i = 0; i < 2147483647; i++) {
			count++;
		}
		long ed = System.currentTimeMillis();
		System.out.println("testPositiveIncrement: " + (ed - st));
	}

	private static void testPreIncrement() {
		long count = 0;
		long st = System.currentTimeMillis();
		for (int i = 0; i < 2147483647; ++i) {
			count++;

		}
		long ed = System.currentTimeMillis();
		System.out.println("testPreIncrement: " + (ed - st));
	}
}
