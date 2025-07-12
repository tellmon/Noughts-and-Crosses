import java.awt.Font;
import javax.swing.JButton;

/**
 * Represents a tile in a game, which extends JButton.
 * Each tile has a symbol ('X', 'O', or '-') and can be updated or cleared.
 */
public class Title extends JButton {
    
    /**
     * The symbol of the tile ('X', 'O', or '-').
     */
    private char symbol;
    
    /**
     * Sets the symbol of the tile and updates the button's text and font.
     * 
     * @param s the character symbol to set ('X' or 'O')
     */
    public void setSymbol(char s) {
        symbol = s;
        this.setText("" + s);
        this.setFont(new Font("Hobbiton Brushhand", Font.PLAIN, 100));
    }
    
    /**
     * Gets the symbol of the tile.
     * 
     * @return the character symbol of the tile
     */
    public char getSymbol() {
        return symbol;
    }
    
    /**
     * Resets the tile to its original state with the symbol '-'.
     */
    public void clear() {
        symbol = '-';
        this.setText("" + symbol);
    }
    
    /**
     * Checks if the tile is unused (i.e., the symbol is '-').
     * 
     * @return true if the tile is unused, false otherwise
     */
    public boolean used() {
        if (getSymbol() == '-') {
            return true;
        } else {
            return false;
        }
    }
}
