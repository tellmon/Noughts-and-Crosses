import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The {@code DeathScreen} class is responsible for displaying a "death" message
 * in a separate window when a player loses or dies in the game. It ensures that
 * only one death screen is created and allows for updating the message and colors dynamically.
 */
public class DeathScreen {

    /**
     * The main frame for the death screen.
     */
    static JFrame deathframe;

    /**
     * The panel hosting the death message.
     */
    static JPanel deathScreen;

    /**
     * The label displaying the death message.
     */
    static JLabel deathText;

    /**
     * Tracks whether the death screen has already been created.
     * Ensures that only one death screen instance exists.
     */
    static boolean done = false;

    /**
     * Creates and displays the death screen with a custom message indicating who died.
     * If the death screen already exists, updates the message and colors instead of recreating it.
     *
     * @param deadMan The name of the player or entity that has "died".
     *                This name is included in the death message.
     */
    static void CreateDeathScreen(String deadMan) {
        if (!done) {
            // Create the death frame
            deathframe = new JFrame();
            deathframe.setSize(1000, 250);
            deathframe.setTitle("You're A Dead Man");
            deathframe.setResizable(false);
            deathframe.setLayout(new BorderLayout());
            deathframe.setLocationRelativeTo(null);
            deathframe.setVisible(true);

            // Create the panel to host the death message
            deathScreen = new JPanel();

            // Create and configure the label for the death message
            deathText = new JLabel();
            deathText.setText("HA, HA you died " + deadMan);
            deathText.setFont(new Font("Hobbiton Brushhand", Font.BOLD, 100));
            deathText.setHorizontalAlignment(JLabel.CENTER);

            // Add components and set colors
            deathScreen.add(deathText);
            deathframe.add(deathScreen);
            deathText.setForeground(new Color(ColourPicker.revertReturnRed(), ColourPicker.revertReturnGreen(), ColourPicker.revertReturnBlue()));
            deathScreen.setBackground(new Color(ColourPicker.returnRed(), ColourPicker.returnGreen(), ColourPicker.returnBlue()));

            // Mark the death screen as created
            done = true;
        } else {
            // Update the death screen if it already exists
            deathText.setText("HA, HA you died " + deadMan);
            deathText.setForeground(new Color(ColourPicker.revertReturnRed(), ColourPicker.revertReturnGreen(), ColourPicker.revertReturnBlue()));
            deathScreen.setBackground(new Color(ColourPicker.returnRed(), ColourPicker.returnGreen(), ColourPicker.returnBlue()));
            deathframe.setVisible(true);
        }
    }

    /**
     * Hides the death screen by setting its visibility to {@code false}.
     */
    static void closeWindow() {
        if (deathframe != null) {
            deathframe.setVisible(false);
        }
    }
}
