import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Entry point for the Noughts and Crosses game.
 * This class sets up the main panel, custom font, and a ring-shaped custom cursor.
 */
public class RunnerNoughtsAndCrosses {

    /**
     * The main method that initializes the game window, font, and cursor.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create the font used across the system
        Font labelFont = new Font("Hobbiton Brushhand", Font.BOLD, 25);
        
        // Create the main game window
        JFrame window = new JFrame();
        Interface ox = new Interface(labelFont);
        window.setContentPane(ox);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 500);
        window.setVisible(true);
        
        // Create a custom cursor shaped like a ring
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("lotr ring.png");
        Cursor c = toolkit.createCustomCursor(image, new Point(ox.getX(), ox.getY()), "img");
        ox.setCursor(c);
    }
}
