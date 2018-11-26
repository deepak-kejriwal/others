public class InterviewIO {
	public static void main(String[] args) {}

	public static int[][] memo = null;// new int[m+1][n+1];

	public int countPaths(int m, int n) {
		if (m == 1 || n == 1) {
			return 1;
		}
		memo = new int[m + 1][n + 1];
		return countPathsWithMemo(m, n);
	}

	public int countPathsWithMemo(int m, int n) {

		if (m == 1 || n == 1)
			return 1;
		if (memo == null)
			memo = new int[m + 1][n + 1];
		int x = 0;
		int y = 0;
		if (memo[m - 1][n] != 0) {
			x = memo[m - 1][n];
		} else {
			x = countPathsWithMemo(m - 1, n);
			memo[m - 1][n] = x;
		}
		if (memo[m][n - 1] != 0) {
			y = memo[m][n - 1];
		} else {
			y = countPathsWithMemo(m, n - 1);
			memo[m][n - 1] = y;
		}
		return x + y;
	}
}
