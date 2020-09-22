import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuGame {
	public List<char[][]>  soln = new ArrayList<>();
	public static void main(String[] args) {
		SudokuGame sg = new SudokuGame();
		char[][] board = new char[9][9];
		for(int i=0; i<9; i++) {
			Arrays.fill(board[i], '.');
		}
		sg.solveSudoku(board);
		for(int i=0;i<9; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		
		char[][] game = sg.copy(board);
		sg.generateGame(game);
		System.out.println();
		System.out.println("Game:");
		System.out.println();
		for(int i=0;i<9; i++) {
			System.out.println(Arrays.toString(game[i]));
		}
		
		
		for(int j=0; j<sg.soln.size(); j++) {
			System.out.println();
			System.out.println("SolGame:"+j+1);
			game = sg.soln.get(j);
			for(int i=0;i<9; i++) {
				System.out.println(Arrays.toString(game[i]));
			}
		}

	}
	


	private char[][] copy(char[][] game) {
		char[][] copy = new char[9][9];
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++)
				copy[y][x] = game[y][x];
		}
		return copy;
	}
	
    public boolean solveSudoku(char[][] board) {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
    	int[] sol = {0};
        return solveSudoku(board, 0, sol, numbers);
    }
    
    private boolean solveSudoku(char[][] board, int index, int[] sol, List<Integer> numbers) {
        if(index>80){
            return ++sol[0]==1;
        }
        
        int r = index/9;
        int c = index%9;
        
        if(board[r][c]=='.'){
            for(int i=0; i<9; i++){
                char v = (char)(numbers.get(i)+'0');
                 if(isValidPlace(board, v, r, c)){
                      board[r][c] = v;
                        
                     if(solveSudoku(board, index+1, sol, numbers)){
                        return true;
                     }
                     
                     board[r][c] = '.';
                 }
                                                
            }
        }else{
            return solveSudoku(board, index+1, sol, numbers);
        }
        
        return false;
    }
    
    private boolean isValidPlace(char[][] board, char v, int r, int c){
        return isValidX(board, v, r, c) && isValidY(board, v, r, c) && isValidBlock(board, v, r, c);
    }
                    
    private boolean isValidX(char[][] board, char v, int r, int c){
        
        for(int col = 0; col<9; col++){
            if(board[r][col]==v){
                return false;
            }
        }
        
        return true;
    }
                    
    private boolean isValidY(char[][] board, char v, int r, int c){
        
        for(int row = 0; row<9; row++){
            if(board[row][c]==v){
                return false;
            }
        }
        
        return true;
    }
                    
    private boolean isValidBlock(char[][] board, char v, int r, int c){
        
        int XYr = (r/3)*3;
        int XYc = (c/3)*3;
        
        for(int rowBlock=XYr; rowBlock<(XYr+3); rowBlock++){
            for(int colBlock=XYc; colBlock<(XYc+3); colBlock++){
                if(board[rowBlock][colBlock]==v){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    
	private void generateGame(char[][] game) {
		List<Integer> positions = new ArrayList<Integer>();
		for (int i = 0; i < 81; i++) {
			positions.add(i);
		}
		Collections.shuffle(positions);
		generateGame(game, positions);
		
	}

	private void generateGame(char[][] game, List<Integer> positions) {
		while (positions.size() > 0) {
			soln = new ArrayList<>();
			int position = positions.remove(0);
			int x = position % 9;
			int y = position / 9;
			char temp = game[y][x];
			game[y][x] = '.';

			if (!isValidGame(game)) {
				//if(soln.size()==2) {
				//	return;
				//}
				game[y][x] = temp;
			}else {
				game[y][x] = '.';
			}
			

		}

	}
	
    public boolean isValidGame(char[][] board) {
    	int[] sol = {0};
        return isValidGame(board, 0, sol);
    }
    
    private boolean isValidGame(char[][] board, int index, int[] sol) {
        if(index>80){
        	//soln.add(copy(board));
            return ++sol[0]==1;
        }
        
        int r = index/9;
        int c = index%9;
        
        if(board[r][c]=='.'){
            for(int i=1; i<=9; i++){
                
                char v = (char)(i+'0');
                 if(isValidPlace(board, v, r, c)){
                      board[r][c] = v;
                        
                     if(!isValidGame(board, index+1, sol)){
                    	board[r][c] = '.';
                        return false;
                     }
                     
                     board[r][c] = '.';
                 }
                                                
            }
        }else{
            return isValidGame(board, index+1, sol);
        }
        
        return true;
    }

}
