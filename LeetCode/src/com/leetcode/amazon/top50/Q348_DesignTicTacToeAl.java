package com.leetcode.amazon.top50;
/*
 * 
 Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?
Could you trade extra space such that move() operation can be done in O(1)?
You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.
 */

/*
 * 
 Inspired by N-Queen by Tushar Roy
 */


class Q348_DesignTicTacToeAlt {

    /** Initialize your data structure here. */
    
    private int[][] rows;
    private int[][] cols;
    private int[][] diags;
    private int[][] adiags;
    private int n;
    public Q348_DesignTicTacToeAlt(int n) {
        rows = new int[n][3];
        cols = new int[n][3];
        diags = new int[2*n][3];
        adiags = new int[2*n][3];
        this.n = n;
    }
    
    public int move(int row, int col, int player) {
        // 1 -> player1
        // 2 -> player2
        rows[row][player] +=1;
        cols[col][player] += 1;
        diags[row+col][player] += 1;
        adiags[row- col+n][player] += 1;
        
        if (rows[row][player] == n ||cols[col][player]==n || diags[row+col][player] == n || adiags[row- col+n][player] == n)
            return player;
        else
            return 0;
    }
}
