package com.leetcode;

public class Q1277_CountSquareSubmatricesWithAllOnes {

	public static void main(String[] args) {
		int[][] matrix = {
		                  {0,1,1,1},
		                  {1,1,1,1},
		                  {0,1,1,1}
		};
		System.out.println(countSquares(matrix));
	}

    public static int countSquares(int[][] matrix) {
        int M = matrix.length, N = matrix[0].length;
        int[][] sum = new int[M][N + 1];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                sum[i][j + 1] = sum[i][j] + matrix[i][j]; //row-wise sum
            }
        }
        
        int res = 0;
        
        for (int i = 0; i < M; i++) {
            
            int[] preSum = new int[N + 1];
            int L = 0;
            for (int j = i; j < M; j++) {
                for (int l = 0; l <= N; l++) {
                    preSum[l] += sum[j][l]; // presum[l] value is sum of all 1's inside the matrix of size i+1 and l 
                }
                
                L++; // length of sub matrics squares
                
                for (int k = L ; k <= N; k++) {
                    int area = preSum[k] - preSum[k  - L];// windows of size L
                    if (area == L * L) {
                        res++;
                    }
                }
            }
        }
        
        return res;
    }
}
