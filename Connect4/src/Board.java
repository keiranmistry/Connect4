
//Import packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JFrame {

	// Declare title variables
	private JLabel titleY;
	private JLabel titleR;
	private JLabel titleD;

	// Creating the board screen class
	public Board() {
		// Title the window
		super("Connect 4 | Game");

		// Setting up the JPanel
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				// Determining the dimensions of the board
				int panelWidth = getWidth();
				int panelHeight = getHeight();
				int x = (panelWidth - 860) / 2;
				int y = (panelHeight - 760) / 2;
				g.setColor(Colours.mainColour);
				g.fillRect(x, y, 860, 740);

				// Adding the red and yellow tokens to the board
				for (int r = 0; r < 6; r++) {
					for (int c = 0; c < 7; c++) {
						if (Game.board[r][c] == 0) {
							g.setColor(Color.WHITE);
						} else if (Game.board[r][c] == 1) {
							g.setColor(Colours.red);
						} else {
							g.setColor(Colours.yellow);
						}
						g.fillOval(x + 20 * (c + 1) + 100 * c, y + 20 * (r + 1) + 100 * r, 100, 100);
					}
				}
			}
		};
		add(panel, BorderLayout.CENTER);

		// Setting up the title
		JPanel title = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// If there is no winner
		if (!Game.winner) {
			// If the game is not drawn
			if (!Game.draw) {
				// If it's Player 1's turn
				if (Game.turn % 2 == 1) {

					// Formatting the Player 1's turn title
					titleR = new JLabel();
					titleR.setFont(new Font("SANS SERIF", Font.BOLD, 80));
					titleR.setText("PLAYER 1'S TURN");
					titleR.setForeground(Colours.red);
					title.add(titleR);

					// Changing the user's turn
					Game.turn++;
				}

				// If it's Player 2's turn
				else {

					// Formatting the Player 2's turn title
					titleY = new JLabel();
					titleY.setFont(new Font("SANS SERIF", Font.BOLD, 80));
					titleY.setText("PLAYER 2'S TURN");
					titleY.setForeground(Colours.yellow);
					title.add(titleY);

					// Changing the user's turn
					Game.turn++;
				}

				// Adding the column selector buttons
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // 20px horizontal gap
				for (int i = 0; i < 7; i++) {
					JButton button = new JButton("" + (i + 1));
					int columnNumber = i + 1;

					// Formatting the buttons
					button.setFont(new Font("SANS SERIF", Font.BOLD, 50));
					button.setBorderPainted(false);
					button.setBackground(Colours.buttonbkgnd);
					addHoverEffect(button, Colours.buttonbkgnd, Colours.buttononhover);
					button.setFocusPainted(false);
					button.setPreferredSize(new Dimension(100, 80));

					// Adding a colour change when the start button is hovered
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Game.setColumnNumber(columnNumber);
							dispose();
						}
					});
					buttonPanel.add(button);
				}
				add(buttonPanel, BorderLayout.SOUTH);
			}

			// If the game is drawn
			else {

				// Formatting draw title
				titleD = new JLabel();
				titleD.setFont(new Font("SANS SERIF", Font.BOLD, 80));
				titleD.setText("DRAW");
				titleD.setForeground(Colours.heading);
				title.add(titleD);

				// Adding the exit button
				exitButton(50);

				// Updating stats
				Game.recentWinner = 0;
				Game.draws++;
			}
		}

		// If the game has been won
		else {

			// If Player 1 won
			if ((Game.turn - 1) % 2 == 1) {

				// Formatting Player 1 win title
				titleR = new JLabel();
				titleR.setFont(new Font("SANS SERIF", Font.BOLD, 80));
				titleR.setText("PLAYER 1 WINS");
				titleR.setForeground(Colours.red);
				title.add(titleR);

				// Updating stats
				Game.p1wins++;
				if (Game.recentWinner == 1 || Game.gamesPlayed == 1) {
					Game.streak++;
				} else {
					Game.streak = 1;
				}
				Game.recentWinner = 1;
			}

			// If Player 2 won
			else {

				// Formatting Player 2 win title
				titleY = new JLabel();
				titleY.setFont(new Font("SANS SERIF", Font.BOLD, 80));
				titleY.setText("PLAYER 2 WINS");
				titleY.setForeground(Colours.yellow);
				title.add(titleY);

				// Updating stats
				Game.p2wins++;
				if (Game.recentWinner == 2 || Game.gamesPlayed == 1) {
					Game.streak++;
				} else {
					Game.streak = 1;
				}
				Game.recentWinner = 2;
			}

			// Adding the exit button
			exitButton(50);

			// Resetting variables
			Game.winner = false;
			Game.turn = 1;

		}

		// Adding the title to the JFrame
		add(title, BorderLayout.NORTH);

	}

	// Hovering button effect class
	private void addHoverEffect(JButton button, Color originalColour, Color hoverColour) {
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			// When the cursor enters the button
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// Change the colour of the button to a lighter colour
				button.setBackground(hoverColour);
			}

			// When the cursor exits the button
			public void mouseExited(java.awt.event.MouseEvent evt) {
				// Revert the colour of the button to the original colour
				button.setBackground(originalColour);
			}
		});
	}

	// Add exit button class
	private void exitButton(int size) {

		// Adding and formatting exit button
		JButton button = new JButton("EXIT");
		button.setFont(new Font("SANS SERIF", Font.BOLD, size));
		button.setForeground(Colours.mainColour);
		button.setBorderPainted(false);
		button.setBackground(Colours.buttonbkgnd);
		addHoverEffect(button, Colours.buttonbkgnd, Colours.buttononhover);
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(100, 80));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Return back to the main menu
				Main.main(null);
				// Close the board window
				dispose();
			}
		});
		add(button, BorderLayout.SOUTH);
	}

}
