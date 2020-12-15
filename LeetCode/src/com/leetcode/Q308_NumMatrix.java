package com.leetcode;

import java.util.Arrays;

public class Q308_NumMatrix {

	// https://leetcode.com/problems/range-sum-query-2d-mutable/discuss/75905/15ms-Very-Concise-Java-Code-Using-BIT

	// Using 2D Binary Indexed Tree, 2D BIT Def:
	// bit[i][j] saves the rangeSum of [i-(i&-i), i] x [j-(j&-j), j]
	// note bit index == matrix index + 1
	int n, m;
	int[][] bit;
	int[][] a;

	public Q308_NumMatrix(int[][] matrix) {
		if (matrix.length < 1)
			return;
		n = matrix.length;
		m = matrix[0].length;
		bit = new int[n + 1][m + 1];
		a = new int[n][m];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				update(i, j, matrix[i][j]);
	}

	public void update(int row, int col, int val) {
		int diff = val - a[row][col];
		a[row][col] = val;
		for (int i = row + 1; i <= n; i += i & -i)
			for (int j = col + 1; j <= m; j += j & -j)
				bit[i][j] += diff;
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
	}

	public int sum(int row, int col) {
		int tot = 0;
		for (int i = row + 1; i > 0; i -= i & -i)
			for (int j = col + 1; j > 0; j -= j & -j)
				tot += bit[i][j];
		return tot;
	}
	
	public static void main(String[] args) {
		int[][] input = {{1,0,0},{1,0,0},{1,0,0}};
		Q308_NumMatrix impl = new Q308_NumMatrix(input);
		impl.sumRegion(0, 2, 1, 2);
		for(int[] b: impl.bit) {
			System.out.println(Arrays.toString(b));
		}
		
	}
}