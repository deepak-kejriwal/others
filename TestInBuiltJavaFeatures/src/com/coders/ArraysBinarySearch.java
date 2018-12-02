package com.coders;

import java.util.Arrays;

/**
* 
* @author deepak1037
*
*/
public class ArraysBinarySearch {

	public static void main(String[] args) {
		int[] nums= {1,2,3,6,5,4};
		Arrays.sort(nums);	
		//Print Array
		System.out.println("Given input: "+Arrays.toString(nums));
		int x=Arrays.binarySearch(nums, 3);
		System.out.println(x+"  -> Output is 2 which is index of number 3");
		int y=Arrays.binarySearch(nums, 7);
		System.out.println(y+" -> Output is -7 which is -ve of (array length+1) as number 7 not found in input array");

	}

}
