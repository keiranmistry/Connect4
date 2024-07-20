
//Import packages
import java.util.*;
import javax.swing.JFrame;

public class Game {
	// Declare game variables
	public static int turn = 1;
	public static boolean winner = false;
	public static int streak = 0;
	public static int recentWinner = 0;
	public static boolean draw = false;
	public static int[][] board = new int[6][7];
	public static int p1wins = 0;
	public static int p2wins = 0;
	public static int draws = 0;
	public static int gamesPlayed = 0;

	// User makes a turn class
	public static void setColumnNumber(int col) {

		// Checking if turn is valid
		col -= 1;
		boolean validTurn = false;
		for (int i = 5; i > -1; i--) {

			// If the space is empty
			if (board[i][col] == 0) {
				// Turn is valid
				validTurn = true;

				// Player 1's token
				if (turn % 2 == 0 || turn == 1) {
					board[i][col] = 1;
				}

				// Player 2's token
				else {
					board[i][col] = 2;
				}
				break;
			}
		}

		// If an invalid turn, redo the move
		if (!validTurn) {
			turn -= 1;
		}

		// Check if someone has won
		checkForWin();

		// Check for draw circumstance
		if (!winner && turn == 43) {
			draw = true;
		}

		// Set up a new board after turn and close the old window
		Board board = new Board();
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setSize(1000, 1000);
		board.setVisible(true);

	}

	// Check if someone has won class
	public static void checkForWin() {
		// Vertical check
		for (int c = 0; c < 7; c++) {
			for (int r = 0; r < 3; r++) {
				if (board[r][c] != 0 && board[r][c] == board[r + 1][c] && board[r][c] == board[r + 2][c]
						&& board[r][c] == board[r + 3][c]) {
					winner = true;
				}
			}
		}

		// Horizontal check
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 4; c++) {
				if (board[r][c] != 0 && board[r][c] == board[r][c + 1] && board[r][c] == board[r][c + 2]
						&& board[r][c] == board[r][c + 3]) {
					winner = true;
				}
			}
		}

		// Diagonal, bottom right to top left, check
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 4; col++) {
				if (board[row][col] != 0 && board[row][col] == board[row + 1][col + 1]
						&& board[row][col] == board[row + 2][col + 2] && board[row][col] == board[row + 3][col + 3]) {
					winner = true;
				}
			}
		}

		// Diagonal, bottom left to top right, check
		for (int r = 3; r < 6; r++) {
			for (int c = 0; c < 4; c++) {
				if (board[r][c] != 0 && board[r][c] == board[r - 1][c + 1] && board[r][c] == board[r - 2][c + 2]
						&& board[r][c] == board[r - 3][c + 3]) {
					winner = true;
				}
			}
		}

	}
}