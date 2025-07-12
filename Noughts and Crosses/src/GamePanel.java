import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The {@code GamePanel} class represents the game board for a Tic-Tac-Toe game.
 * It manages the game state, including player and AI turns, and checks for a winner.
 * The board is displayed using a 3x3 grid of {@link Title} components, and the panel
 * updates dynamically based on the player's and AI's actions.
 */
public class GamePanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    /**
     * Array of {@link Title} objects that represent the 9 positions on the Tic-Tac-Toe board.
     */
    private Title[] myTitles = new Title[9];

    /**
     * The current player's symbol ('X' or 'O'). It starts as 'O'.
     */
    private char myTurn = 'o';

    /**
     * Button that displays whose turn it is (X or O).
     */
    public JButton myTurnL;

    /**
     * Flag used to stop the game when a winner is found.
     */
    private boolean stop = false;

    /**
     * Random number generator for AI delay to simulate thinking.
     */
    Random rand = new Random();

    /**
     * Instance of the {@link Ai} class that handles the AI's moves.
     */
    Ai ai = new Ai();

    /**
     * Creates the game panel, sets up the 3x3 grid layout, and initializes the board.
     * 
     * @param myTurnLabel The button that displays the current player's turn.
     */
    public GamePanel(JButton myTurnLabel) {
    	
        this.setLayout(new GridLayout(3, 3)); // Create a 3x3 grid layout
        myTurnL = myTurnLabel;

        // Initialize the 9 tiles
        for (int i = 0; i < myTitles.length; i++) {
            myTitles[i] = new Title();
            this.add(myTitles[i]);
            myTitles[i].addActionListener(this);
            myTitles[i].setBackground(Color.WHITE);
            myTitles[i].setFocusPainted(false);
            myTitles[i].setSymbol('-'); // Initially, tiles are empty
        }
    }

    /**
     * Resets the game board, clearing all tiles and resetting the AI.
     */
    public void reset() {
        for (int i = 0; i < myTitles.length; i++) {
            myTitles[i].clear(); // Clears each tile
        }
        ai.reset(); // Resets the AI logic
    }

    /**
     * Returns the current player's symbol ('X' or 'O').
     * 
     * @return The current player's symbol.
     */
    public char getTurn() {
        return myTurn;
    }

    /**
     * Checks the game board for a winner. It checks all rows, columns, and diagonals for
     * three matching symbols ('X' or 'O'). If a winner is found, the game is marked as stopped.
     */
    public void checkWinner() {
        // Check rows for a winner
        for (int i = 0; i < myTitles.length; i += 3) {
            if (stop == false && myTitles[i].getSymbol() == myTitles[1 + i].getSymbol() && myTitles[1 + i].getSymbol() == myTitles[2 + i].getSymbol()) {
                if (getTurn() == myTitles[i].getSymbol()) {
                    for (int x = 0; x < myTitles.length; x++) {
                        myTitles[x].setSymbol(myTurn); // Set all tiles to the winning symbol
                    }
                    LifeSystem.CheckLife(myTitles[2 + i].getSymbol()); // Update life system
                    stop = true;
                }
            }
        }

        // Check columns for a winner
        for (int i = 0; i < 3; i++) {
            if (stop == false && myTitles[i].getSymbol() == myTitles[3 + i].getSymbol() && myTitles[3 + i].getSymbol() == myTitles[6 + i].getSymbol()) {
                if (getTurn() == myTitles[i].getSymbol()) {
                    for (int x = 0; x < myTitles.length; x++) {
                        myTitles[x].setSymbol(myTurn);
                    }
                    LifeSystem.CheckLife(myTitles[6 + i].getSymbol());
                    stop = true;
                }
            }
        }

        // Check diagonals for a winner
        if (stop == false && myTitles[0].getSymbol() == myTitles[4].getSymbol() && myTitles[4].getSymbol() == myTitles[8].getSymbol()) {
            if (getTurn() == myTitles[0].getSymbol()) {
                for (int x = 0; x < myTitles.length; x++) {
                    myTitles[x].setSymbol(myTurn);
                }
                LifeSystem.CheckLife(myTitles[8].getSymbol());
                stop = true;
            }
        }

        if (stop == false && myTitles[2].getSymbol() == myTitles[4].getSymbol() && myTitles[4].getSymbol() == myTitles[6].getSymbol()) {
            if (getTurn() == myTitles[6].getSymbol()) {
                for (int x = 0; x < myTitles.length; x++) {
                    myTitles[x].setSymbol(myTurn);
                }
                LifeSystem.CheckLife(myTitles[6].getSymbol());
                stop = true;
            }
        }

        stop = false; // Reset the stop flag after checking
    }

    /**
     * Switches the turn between players 'X' and 'O'. Updates the turn label to reflect the current player.
     */
    private void switchTurn() {
        myTurn = (myTurn == 'x') ? 'o' : 'x'; // Toggle turn between 'x' and 'o'
        Interface.update(myTurnL); // Update the turn label
    }

    /**
     * Changes the background and foreground colors of the tiles based on the color sliders.
     * This method is called repeatedly to update the board's appearance.
     */
    public void colourChange() {
        for (int i = 0; i < myTitles.length; i++) {
            myTitles[i].setBackground(new Color(ColourPicker.returnRed(), ColourPicker.returnGreen(), ColourPicker.returnBlue()));
            myTitles[i].setForeground(new Color(ColourPicker.revertReturnRed(), ColourPicker.revertReturnGreen(), ColourPicker.revertReturnBlue()));
        }
        this.revalidate(); // Revalidate the panel to apply changes
        this.repaint(); // Repaint the panel to update the display
    }

    /**
     * Handles the player's turn when a tile is clicked.
     * The selected tile is updated with the current player's symbol, and the winner is checked.
     * After that, the turn is switched to the other player.
     * 
     * @param clicked The tile that was clicked by the player.
     */
    public void playersTurn(Title clicked) {
        clicked.setSymbol(myTurn); // Set the clicked tile's symbol
        checkWinner(); // Check if this move results in a win
        switchTurn(); // Switch the turn to the other player
    }

    /**
     * Makes the AI perform its turn. The AI blocks or wins based on the current board state.
     * After making a move, the winner is checked and the turn is switched.
     * The AI's move is followed by a slight delay to simulate thinking.
     */
    public void aiTurn() {
        myTitles = ai.blockAndWinsMove(myTitles); // AI makes its move
        checkWinner(); // Check if the AI's move results in a win
        switchTurn(); // Switch the turn to the player
        try {
            Thread.sleep(rand.nextInt(165)); // Introduce a slight delay to simulate AI thinking
        } catch (InterruptedException e) {
            System.out.println("The AI's thinking time was interrupted");
        }
    }

    /**
     * Handles the action event when a tile is clicked.
     * The tile is passed to the player's turn method, and then the AI's turn is triggered.
     * 
     * @param a The action event triggered by the tile click.
     */
    public void actionPerformed(ActionEvent a) {
        Title clicked = (Title) a.getSource(); // Get the clicked tile
        if (clicked.used()) { 
           
        	playersTurn(clicked); // Handle player's turn
            aiTurn(); // AI takes its turn after the player
        }
    }
}
