import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The {@code GamePanelHuman} class represents the game panel for a human player in a Tic-Tac-Toe game.
 * It manages the tiles, turn switching, color changes, and determining the winner.
 */
public class GamePanelHuman extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    /**
     * Array of {@code Title} objects representing all the positions on the board.
     */
    private Title[] myTitles = new Title[9];

    /**
     * Tracks whose turn it is, either 'x' or 'o'.
     */
    private char myTurn = 'o';

    /**
     * Button used to display whose turn it is and update the game interface accordingly.
     */
    public JButton myTurnL;

    /**
     * Indicates if the game has stopped after a winner is determined.
     */
    private boolean stop = false;

    /**
     * Creates the game panel, initializes the tiles, and sets up the grid layout.
     * 
     * @param myTurnLabel A {@code JButton} displaying the current player's turn.
     */
    public GamePanelHuman(JButton myTurnLabel) {
        this.setLayout(new GridLayout(3, 3));
        myTurnL = myTurnLabel;

        for (int i = 0; i < myTitles.length; i++) {
            myTitles[i] = new Title();
            this.add(myTitles[i]);
            myTitles[i].addActionListener(this);
            myTitles[i].setBackground(Color.WHITE);
            myTitles[i].setFocusPainted(false);
            myTitles[i].setSymbol('-');
        }
    }

    /**
     * Resets the tiles to their default state by calling the {@code clear} method on each tile.
     */
    public void reset() {
        for (int i = 0; i < myTitles.length; i++) {
            myTitles[i].clear();
        }
    }

    /**
     * Returns whose turn it currently is.
     * 
     * @return A {@code char} indicating the current player's turn ('x' or 'o').
     */
    public char getTurn() {
        return myTurn;
    }

    /**
     * Checks for a winner by analyzing all possible winning combinations.
     * If a winner is found, updates all tiles with the winner's symbol and updates the life system.
     */
    public void checkWinner() {
        // Check rows for a winner
        for (int i = 0; i < myTitles.length; i += 3) {
            if (!stop && myTitles[i].getSymbol() == myTitles[1 + i].getSymbol()
                    && myTitles[1 + i].getSymbol() == myTitles[2 + i].getSymbol()) {
                if (getTurn() == myTitles[i].getSymbol()) {
                    for (int x = 0; x < myTitles.length; x++) {
                        myTitles[x].setSymbol(myTurn);
                    }
                    LifeSystem.CheckLife(myTitles[2 + i].getSymbol());
                    stop = true;
                }
            }
        }

        // Check columns for a winner
        for (int i = 0; i < 3; i += 1) {
            if (!stop && myTitles[i].getSymbol() == myTitles[3 + i].getSymbol()
                    && myTitles[3 + i].getSymbol() == myTitles[6 + i].getSymbol()) {
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
        if (!stop && myTitles[0].getSymbol() == myTitles[4].getSymbol()
                && myTitles[4].getSymbol() == myTitles[8].getSymbol()) {
            if (getTurn() == myTitles[0].getSymbol()) {
                for (int x = 0; x < myTitles.length; x++) {
                    myTitles[x].setSymbol(myTurn);
                }
                LifeSystem.CheckLife(myTitles[8].getSymbol());
                stop = true;
            }
        }

        if (!stop && myTitles[2].getSymbol() == myTitles[4].getSymbol()
                && myTitles[4].getSymbol() == myTitles[6].getSymbol()) {
            if (getTurn() == myTitles[6].getSymbol()) {
                for (int x = 0; x < myTitles.length; x++) {
                    myTitles[x].setSymbol(myTurn);
                }
                LifeSystem.CheckLife(myTitles[6].getSymbol());
                stop = true;
            }
        }

        stop = false;
    }

    /**
     * Switches the turn between 'x' and 'o' and updates the turn label on the interface.
     */
    private void switchTurn() {
        if (myTurn == 'x') {
            myTurn = 'o';
        } else {
            myTurn = 'x';
        }

        Interface.update(myTurnL);
    }

    /**
     * Updates the background and foreground colors of the tiles using values from the
     * {@code ColourPicker} class. Called periodically by the interface.
     */
    public void colourChange() {
        for (int i = 0; i < myTitles.length; i++) {
            myTitles[i].setBackground(new Color(ColourPicker.returnRed(), ColourPicker.returnGreen(), ColourPicker.returnBlue()));
            myTitles[i].setForeground(new Color(ColourPicker.revertReturnRed(), ColourPicker.revertReturnGreen(), ColourPicker.revertReturnBlue()));
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Handles the tile click events. If a tile is clicked and it is not already used, it updates
     * the tile with the current player's symbol, checks for a winner, and switches the turn.
     * 
     * @param a The {@code ActionEvent} triggered by a tile click.
     */
    public void actionPerformed(ActionEvent a) {
        Title clicked = (Title) a.getSource();

        if (clicked.used()) {
            clicked.setSymbol(myTurn);
            checkWinner();
            switchTurn();
        }
    }
}
