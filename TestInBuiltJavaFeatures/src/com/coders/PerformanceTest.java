package com.coders;

/**
* 
* @author Deepak Kejriwal
*
*/
import java.util.ArrayList;
import java.util.List;

public class PerformanceTest {
	private static final long MEGABYTE = 1024L * 1024L;

	public static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}

	public static void main(String[] args) {
		PerformanceTest pt = new PerformanceTest();
		// I assume you will know how to create a object Person yourself...
		List<Person> list = new ArrayList<Person>();
		for (int i = 0; i <= 100000; i++) {
			list.add(pt.getPersonObject());
		}
		// Get the Java runtime
		Runtime runtime = Runtime.getRuntime();
		// Run the garbage collector
		runtime.gc();
		// Calculate the used memory
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory is bytes: " + memory);
		System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
	}

	private Person getPersonObject() {
		return new Person("Jim", "Knopf");
	}

	private class Person {
		String first;
		String last;

		Person(String first, String last) {
			this.first = first;
			this.last = last;
		}
	}
}
