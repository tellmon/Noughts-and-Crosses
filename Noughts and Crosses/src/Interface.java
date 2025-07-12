import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The main interface panel for the game.
 * Handles buttons for reset, switching between AI and human modes, life displays, and game panels.
 * Manages the color picker functionality and updates colors in real-time.
 */
public class Interface extends JPanel implements ActionListener {

    /**
     * The game panel for AI gameplay.
     */
    private static GamePanel myGamePanel;

    /**
     * The game panel for human vs. human gameplay.
     */
    private static GamePanelHuman humanGamePanel;

    /**
     * Tracks whether the game is in human mode (true) or AI mode (false).
     */
    private boolean human = true;

    /**
     * Button displaying the current turn (X or O).
     */
    static JButton myTurnLabel;

    /**
     * Button displaying the current life total for player X.
     */
    static JButton Xlifes = new JButton();

    /**
     * Button displaying the current life total for player O.
     */
    static JButton Olifes = new JButton();

    /**
     * Button to reset the game.
     */
    JButton reset;

    /**
     * Button to toggle between AI and human gameplay.
     */
    JButton aiHuman;

    /**
     * Panel containing the buttons for layout organization.
     */
    JPanel buttonsPanel = new JPanel();

    /**
     * Timer to update colors every millisecond.
     */
    Timer tick;

    /**
     * Constructs the main interface panel.
     * Initializes all buttons, panels, and game elements.
     * Adds action listeners, sets button styles, and organizes the layout.
     * 
     * @param labelFont the font to use for the buttons and labels
     */
    public Interface(Font labelFont) {
        new ColourPicker(labelFont);

        tick = new Timer(1, this);
        tick.start();

        myTurnLabel = new JButton();
        myGamePanel = new GamePanel(myTurnLabel);
        humanGamePanel = new GamePanelHuman(myTurnLabel);

        reset = new JButton("reset");
        reset.addActionListener(this);
        reset.setFocusPainted(false);
        reset.setBackground(Color.WHITE);
        reset.setPreferredSize(new Dimension(150, 50));

        aiHuman = new JButton("Ai/Human");
        aiHuman.addActionListener(this);
        aiHuman.setFocusPainted(false);
        aiHuman.setBackground(Color.WHITE);
        aiHuman.setPreferredSize(new Dimension(150, 50));

        myTurnLabel.setFocusPainted(false);
        myTurnLabel.setBackground(Color.WHITE);

        Xlifes.setBackground(Color.WHITE);
        Olifes.setBackground(Color.WHITE);
        Xlifes.setHorizontalAlignment(JLabel.CENTER);
        Olifes.setHorizontalAlignment(JLabel.CENTER);
        Olifes.setFocusPainted(false);
        Xlifes.setFocusPainted(false);
        Olifes.setBorderPainted(false);
        Xlifes.setBorderPainted(false);

        Xlifes.setText("X Lifes = " + LifeSystem.getOLifes());
        Olifes.setText("O Lifes = " + LifeSystem.getXLifes());

        Xlifes.setFont(labelFont);
        Olifes.setFont(labelFont);

        this.setLayout(new BorderLayout());
        this.add(myGamePanel, BorderLayout.CENTER);

        myTurnLabel.setHorizontalAlignment(JLabel.CENTER);
        myTurnLabel.setText("Current Turn: " + myGamePanel.getTurn());
        myTurnLabel.setFont(labelFont);

        this.add(myTurnLabel, BorderLayout.NORTH);

        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(reset, BorderLayout.EAST);
        buttonsPanel.add(aiHuman, BorderLayout.WEST);
        buttonsPanel.add(Xlifes, BorderLayout.NORTH);
        buttonsPanel.add(Olifes, BorderLayout.SOUTH);

        this.add(buttonsPanel, BorderLayout.EAST);

        reset.setFont(labelFont);
        aiHuman.setFont(labelFont);
    }

    /**
     * Updates the turn label and life counters based on the game state.
     * 
     * @param myTurnL the button displaying the current turn (unused in this method)
     */
    public static void update(JButton myTurnL) {
        myTurnLabel.setText("Current Turn: " + myGamePanel.getTurn());
        myTurnLabel.setText("Current Turn: " + humanGamePanel.getTurn());
        Xlifes.setText("X Lifes = " + LifeSystem.getOLifes());
        Olifes.setText("O Lifes = " + LifeSystem.getXLifes());
    }

    /**
     * Updates colors based on the color picker slider.
     * Adjusts the background and text colors for all relevant components.
     */
    public void changeColour() {
        humanGamePanel.colourChange();
        myGamePanel.colourChange();

        ColourPicker.changeColourPickerColour();

        aiHuman.setForeground(new Color(ColourPicker.revertReturnRed(), ColourPicker.revertReturnGreen(), ColourPicker.revertReturnBlue()));
        reset.setForeground(new Color(ColourPicker.revertReturnRed(), ColourPicker.revertReturnGreen(), ColourPicker.revertReturnBlue()));
        Xlifes.setForeground(new Color(ColourPicker.revertReturnRed(), ColourPicker.revertReturnGreen(), ColourPicker.revertReturnBlue()));
        Olifes.setForeground(new Color(ColourPicker.revertReturnRed(), ColourPicker.revertReturnGreen(), ColourPicker.revertReturnBlue()));
        myTurnLabel.setForeground(new Color(ColourPicker.revertReturnRed(), ColourPicker.revertReturnGreen(), ColourPicker.revertReturnBlue()));

        myTurnLabel.setBackground(new Color(ColourPicker.returnRed(), ColourPicker.returnGreen(), ColourPicker.returnBlue()));
        Xlifes.setBackground(new Color(ColourPicker.returnRed(), ColourPicker.returnGreen(), ColourPicker.returnBlue()));
        Olifes.setBackground(new Color(ColourPicker.returnRed(), ColourPicker.returnGreen(), ColourPicker.returnBlue()));
        aiHuman.setBackground(new Color(ColourPicker.returnRed(), ColourPicker.returnGreen(), ColourPicker.returnBlue()));
        reset.setBackground(new Color(ColourPicker.returnRed(), ColourPicker.returnGreen(), ColourPicker.returnBlue()));

        this.revalidate();
        this.repaint();
    }

    /**
     * Resets the game panels and life counters.
     * Closes the death screen if one was active.
     */
    private void reset() {
        myGamePanel.reset();
        humanGamePanel.reset();

        if (LifeSystem.getXLifes() <= 0 || LifeSystem.getOLifes() <= 0) {
            DeathScreen.closeWindow();
            LifeSystem.resetLifes();
        }
    }

    /**
     * Toggles between human and AI game panels, updating the display accordingly.
     */
    private void aiHumanSwitcher() {
        if (human) {
            this.remove(myGamePanel);
            this.add(humanGamePanel, BorderLayout.CENTER);
            human = false;
        } else {
            this.remove(humanGamePanel);
            this.add(myGamePanel, BorderLayout.CENTER);
            myGamePanel.reset();
            human = true;
        }

        this.revalidate();
        this.repaint();
    }

    /**
     * Handles button actions and timer updates.
     * - Calls {@link #reset()} when the reset button is clicked.
     * - Toggles game modes when the AI/Human button is clicked.
     * - Updates colors every millisecond based on the timer.
     * 
     * @param e the action event triggered by a button or timer
     */
    public void actionPerformed(ActionEvent e) {
        if (reset == e.getSource()) {
            reset();
        }

        if (aiHuman == e.getSource()) {
            aiHumanSwitcher();
        }

        if (tick == e.getSource()) {
            changeColour();
        }
    }
}
