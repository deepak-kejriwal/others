import java.util.Arrays;

public class MsftConnectFour {

	enum Player {
		None, Yellow, Red, Empty
	}

	int M = 6;
	int N = 7;
	int C = 4;

	Player[][] board = null;
	int[] rowMoveAvailable;
	private Player winner;

	MsftConnectFour() {
		board = new Player[M][N];
		rowMoveAvailable = new int[N];
		for (int i = 0; i < M; i++) {
			Arrays.fill(board[i], Player.Empty);
		}
		Arrays.fill(rowMoveAvailable, M - 1);
		winner = Player.None;
	}

	private void validateMove(Player player, int col) throws Exception {
		if (col < 0 || col >= N) {
			throw new Exception("Invalid column provided");
		}
		if (rowMoveAvailable[col] < 0) {
			throw new Exception("Column is full");
		}
	}

	public Player makeMove(Player player, int col) throws Exception {
		validateMove(player, col);
		makeMove(player, col, rowMoveAvailable[col]--);
		return winner;
	}

	private void makeMove(Player player, int col, int row) {
		board[row][col] = player;
		hasWon(player, col, row);
	}

	private void hasWon(Player player, int col, int row) {
		if (wonByRow(player, col, row)) {
			return;
		}
		if (wonByCol(player, col, row)) {
			return;
		}

		if (wonByDiag(player, col, row, 1)) {
			return;
		}
		if (wonByDiag(player, col, row, -1)) {
			return;
		}

	}

	private boolean wonByDiag(Player player, int col, int row, int direction) {
		int colstart = 0;
		if (direction == 1) {
			colstart = Math.max(0, col - C + 1);
		} else {
			colstart = Math.min(N - 1, col + C - 1);
		}
		int rowstart = Math.max(0, row - C + 1);
		int count = 0;
		for (int i = 0; i < (2 * (C - 1)) && rowstart < M && colstart >= 0
				&& colstart < N; i++, rowstart++, colstart = colstart + direction) {
			if (board[rowstart][colstart] == player) {
				count++;
			} else {
				count = 0;
			}
			
			if (count == C) {
				winner = player;
				return true;
			}
		}


		return false;
	}

	private boolean wonByRow(Player player, int col, int row) {
		int start = Math.max(0, col - C + 1);
		int end = Math.min(N - 1, col + C - 1);

		int count = 0;
		for (int i = start; i <= end; i++) {
			if (board[row][i] == player) {
				count++;
			} else {
				count = 0;
			}
			if (count == C) {
				winner = player;
				return true;
			}
		}
		return false;
	}

	private boolean wonByCol(Player player, int col, int row) {
		int start = row;
		int end = Math.min(M - 1, row + C - 1);

		int count = 0;
		for (int i = start; i <= end; i++) {
			if (board[i][col] == player) {
				count++;
			} else {
				count = 0;
			}
			if (count == C) {
				winner = player;
				return true;
			}
		}
		return false;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public static void main(String[] args) throws Exception {
		MsftConnectFour game = new MsftConnectFour();
		System.out.println(game.makeMove(Player.Yellow, 0));
		System.out.println(game.makeMove(Player.Yellow, 0));
		System.out.println(game.makeMove(Player.Yellow, 0));
		System.out.println(game.makeMove(Player.Red, 0));
		System.out.println(game.makeMove(Player.Red, 0));
		System.out.println(game.makeMove(Player.Red, 0));
		System.out.println(game.makeMove(Player.Yellow, 1));
		System.out.println(game.makeMove(Player.Yellow, 1));
		System.out.println(game.makeMove(Player.Red, 1));
		System.out.println(game.makeMove(Player.Yellow, 2));
		System.out.println(game.makeMove(Player.Red, 2));
		System.out.println(game.makeMove(Player.Red, 3));
	}
}
