package com.coders;

import java.util.Arrays;

/**
* 
* @author Deepak Kejriwal
*
*/
public class ArraysBinarySearch {

	public static void main(String[] args) {
		int[] nums= {1,2,3,4,9};
		Arrays.sort(nums);	
		//Print Array
		System.out.println("Given input: "+Arrays.toString(nums));
		int x=Arrays.binarySearch(nums, 3);
		System.out.println(x+"  -> Output is 2 which is index of number 3");
		int y=Arrays.binarySearch(nums, 7);
		System.out.println(y+" -> Output is -5 which is -ve of (insertion index+1) as number 5 not found in input array");
		int z=Arrays.binarySearch(nums, 10);
		System.out.println(z+" -> Output is -6 which is -ve of (insertion index+1) as number 10 not found in input array");

	}

}
