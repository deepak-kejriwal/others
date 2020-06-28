package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q406_QueueReconstructionbyHeight {

	public static void main(String[] args) {
		int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
		people = reconstructQueue(people);
        for (int[] arr : people) {
            System.out.println(Arrays.toString(arr));
        }

	}

	public static int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
		List<int[]> list = new ArrayList<>();
		for (int[] p : people) {
			list.add(p[1], p);
		}
		return list.toArray(new int[people.length][2]);
	}

}
