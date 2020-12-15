import java.util.ArrayList;

public class MsftConnectFour2 {
	enum Piece {
		Empty, Yellow, Red
	};

	class Check {
		public int row, column;
		private int rowincrement, columnIncrement;

		public Check(int row, int column, int rowI, int colI) {
			this.row = row;
			this.column = column;
			this.rowincrement = rowI;
			this.columnIncrement = colI;
		}

		public void increment() {
			row += rowincrement;
			column += columnIncrement;
		}

		public boolean inBounds(int size) {
			return row >= 0 && column >= 0 && row < size && column < size;
		}
	}

	Piece hasWon(Piece[][] board) {
		if (board.length != board[0].length)
			return Piece.Empty;
		int size = board.length;

		/* Create list of things to check. */
		ArrayList<Check> instructions = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			instructions.add(new Check(0, i, 1, 0));//col
			instructions.add(new Check(i, 0, 0, 1));//row
		}
		instructions.add(new Check(0, 0, 1, 1));
		instructions.add(new Check(0, size - 1, 1, -1));

		/* Check them. */
		for (Check instr : instructions) {
			Piece winner = hasWon(board, instr);
			if (winner != Piece.Empty) {
				return winner;
			}
		}
		return Piece.Empty;
	}

	private Piece hasWon(Piece[][] board, Check instr) {
		Piece first = board[instr.row][instr.column];
		while (instr.inBounds(board.length)) {
			if (board[instr.row][instr.column] != first) {
				return Piece.Empty;
			}
			instr.increment();
		}
		return first;
	}
}
