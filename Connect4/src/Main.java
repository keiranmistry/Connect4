
//Import packages
import javax.swing.*;

public class Main {

	// Main class
	public static void main(String[] args) {

		// Set up board
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				Game.board[r][c] = 0;
			}
		}

		// Open up the menu
		Home menu = new Home();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(1000, 1000);
		menu.setVisible(true);

	}

}
