import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

public class SolutionSolved {

	public static void main(String[] args) {
		Integer[][] grid1 = { { 0, 0, 0, 0, 5 }, { 0, 1, 1, 1, 0 }, { 2, 0, 0, 0, 0 } };
		int result = rocksCollected(grid1);
		System.out.println(doTest(result, 10) + "\n");

		Integer[][] grid2 = { { 0, 0, 0, 0, 5 } };
		result = rocksCollected(grid2);
		System.out.println(doTest(result, 5) + "\n");

		Integer[][] grid3 = { { 0, 0, 10, 8, 0 } };
		result = rocksCollected(grid3);
		System.out.println(doTest(result, 18) + "\n");

		Integer[][] grid4 = {};
		result = rocksCollected(grid4);
		System.out.println(doTest(result, 0) + "\n");

		Integer[][] grid5 = { { 8 } };
		result = rocksCollected(grid5);
		System.out.println(doTest(result, 8) + "\n");
	}

	private static String doTest(int result, int expected) {
		return result == expected ? "pass" : "failed: Expected is " + expected;

	}

	private static int rocksCollected(Integer[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int row = grid.length;
		int col = grid[0].length;
		Integer[][] origGrid = new Integer[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				origGrid[i][j] = grid[i][j];
			}

		}

		//System.out.println("Number of rows: " + row + " " + "\nNumber of columns: " + col + "\n");
		for (int i = 1; i < col; i++) {
			grid[row - 1][i] = grid[row - 1][i - 1] + grid[row - 1][i];
		}
		for (int i = row - 2; i >= 0; i--) {
			grid[i][0] = grid[i + 1][0] + grid[i][0];
		}
		// For debug purpose printing modified
		// printGrid(grid);

		for (int i = row - 2; i >= 0; i--) {
			for (int j = 1; j < col; j++) {
				if (grid[i][j - 1] >= grid[i + 1][j]) {
					grid[i][j] = grid[i][j] + grid[i][j - 1];
				} else {
					grid[i][j] = grid[i][j] + grid[i + 1][j];
				}
			}

		}

		getPath(grid, origGrid);
		// For debug purpose printing modified
		// printGrid(grid);
		return grid[0][col - 1];
	}

	private static void getPath(Integer[][] grid, Integer[][] origGrid) {
		int row = grid.length;
		int i = 0;
		int j = grid[0].length - 1;
		Stack<Integer> stack = new Stack<>();
		Stack<Character> stackDirection = new Stack<>();
		while (i < row - 1 && j > 0) {
			stack.push(origGrid[i][j]);
			if (grid[i][j - 1] >= grid[i + 1][j]) {
				stackDirection.push('R');
				j--;
			} else {
				stackDirection.push('U');
				i++;
			}
		}
		while (i < row - 1) {
			stack.push(origGrid[i][j]);
			stackDirection.push('U');
			i++;
		}
		while (j > 0) {
			stack.push(origGrid[i][j]);
			stackDirection.push('R');
			j--;
		}
		printGrid(origGrid);
		System.out.println("Direction");
		while(!stackDirection.isEmpty()) {
			System.out.print(stackDirection.pop()+"->");
		}
		System.out.println();
	}

	private static void printGrid(Integer[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
