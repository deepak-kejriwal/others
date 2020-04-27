
/*
 * 
 
 Raman is traveling from California(bottom-left cell of grid) to New York(top-right grid of cell).
 On a way he is collecting rocks as specified in cells.
 How many max rocks he can collect on his path to new york provided he can move in UP (North) and Right (East) direction.
 							
 								Grid:
 									{ 0, 0, 0, 0, 5 }--->End New York
									{ 0, 1, 1, 1, 0 }
		       --->Start California { 2, 0, 0, 0, 0 } 
 
 

 *
 */

public class Solution {

	public static void main(String[] args) {
		Integer[][] grid = { { 0, 0, 0, 0, 5 }, { 0, 1, 1, 1, 0 }, { 2, 0, 0, 0, 0 } };
		int result = rocksCollected(grid);
		System.out.print(doTest(result));
	}

	private static String doTest(int result) {
		return result == 10 ? "pass" : "failed";

	}

	private static int rocksCollected(Integer[][] grid) {
		//TO DO
		return 0;
	}



}
