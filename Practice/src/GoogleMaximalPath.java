import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GoogleMaximalPath {

	public static void main(String[] args) {

		System.out.println(paths(3));
	}

	static int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static List<List<Integer>> paths(int N) {
		List<List<Integer>> res = new ArrayList<>();
		boolean[][] visited = new boolean[N][N];
		res = dfs(visited, 0, 0);
		return res;
	}

	private static List<List<Integer>> dfs(boolean[][] visited, int row, int col) {
		List<List<Integer>> res = new ArrayList<>();

		visited[row][col] = true;
		int count = 0;
		for (int[] dir : directions) {
			int x = row + dir[0];
			int y = col + dir[1];

			if (x < 0 || y < 0 || x >= visited.length || y >= visited.length || visited[x][y])
				continue;
			count++;
			List<List<Integer>> tmp = dfs(visited, x, y);
			for (List<Integer> temp : tmp) {
				temp.add(0, row*visited.length+col);
				res.add(temp);
			}
		}
		
		visited[row][col] = false;
		if (count == 0) {
			List<Integer> base = new LinkedList<>();
			base.add(row*visited.length+col);
			res.add(base);
		}

		return res;
	}
}
