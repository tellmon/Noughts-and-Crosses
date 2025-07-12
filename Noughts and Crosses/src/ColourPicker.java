import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The {@code ColourPicker} class provides a graphical interface for selecting a color
 * using RGB sliders. The user can adjust the red, green, and blue color components,
 * and the panel's background color will dynamically update based on the selected values.
 */
public class ColourPicker implements ChangeListener {

    /**
     * The main frame for the color picker window.
     */
    static JFrame frame;

    /**
     * The panel that contains the RGB sliders.
     */
    static JPanel sliderPanel;

    /**
     * The slider for adjusting the red component of the color.
     */
    static JSlider red;

    /**
     * The slider for adjusting the green component of the color.
     */
    static JSlider green;

    /**
     * The slider for adjusting the blue component of the color.
     */
    static JSlider blue;

    /**
     * The label displaying the red component text.
     */
    static JLabel redL;

    /**
     * The label displaying the green component text.
     */
    static JLabel greenL;

    /**
     * The label displaying the blue component text.
     */
    static JLabel blueL;

    /**
     * The current value for the red component.
     */
    static int redness;

    /**
     * The current value for the green component.
     */
    static int greenness;

    /**
     * The current value for the blue component.
     */
    static int blueness;

    /**
     * Constructs the color picker window and initializes the RGB sliders and labels.
     * 
     * @param labelFont The font used for the labels.
     */
    ColourPicker(Font labelFont) {

        // Create the main window frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setTitle("Colour Picker");
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Create the slider panel
        sliderPanel = new JPanel();
        sliderPanel.setBackground(Color.LIGHT_GRAY);
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        // Create the sliders for red, green, and blue
        red = new JSlider(0, 255, 0);
        red.setMajorTickSpacing(64);
        red.setMinorTickSpacing(8);
        red.setPaintTicks(true);
        red.setLabelTable(red.createStandardLabels(255));
        red.setPaintLabels(true);
        red.addChangeListener(this);

        green = new JSlider(0, 255, 0);
        green.setMajorTickSpacing(64);
        green.setMinorTickSpacing(8);
        green.setPaintTicks(true);
        green.setLabelTable(green.createStandardLabels(255));
        green.setPaintLabels(true);
        green.addChangeListener(this);

        blue = new JSlider(0, 255, 0);
        blue.setMajorTickSpacing(64);
        blue.setMinorTickSpacing(8);
        blue.setPaintTicks(true);
        blue.setLabelTable(blue.createStandardLabels(255));
        blue.setPaintLabels(true);
        blue.addChangeListener(this);

        // Create the labels for each color slider
        redL = new JLabel("RED");
        redL.setFont(labelFont);
        redL.setForeground(Color.RED);

        greenL = new JLabel("GREEN");
        greenL.setFont(labelFont);
        greenL.setForeground(Color.GREEN);

        blueL = new JLabel("BLUE");
        blueL.setFont(labelFont);
        blueL.setForeground(Color.BLUE);

        // Add the labels and sliders to the panel
        sliderPanel.add(redL);
        sliderPanel.add(red);
        sliderPanel.add(greenL);
        sliderPanel.add(green);
        sliderPanel.add(blueL);
        sliderPanel.add(blue);

        // Add the slider panel to the frame and make the frame visible
        frame.add(sliderPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Updates the color of the labels and background based on the current RGB values.
     * This method is called whenever a slider's value changes.
     */
    public static void changeColourPickerColour() {
        // Update label colors based on RGB values
        blueL.setForeground(new Color(revertReturnRed(), revertReturnGreen(), revertReturnBlue()));
        redL.setForeground(new Color(revertReturnRed(), revertReturnGreen(), revertReturnBlue()));
        greenL.setForeground(new Color(revertReturnRed(), revertReturnGreen(), revertReturnBlue()));

        // Update the background colors
        sliderPanel.setBackground(new Color(returnRed(), returnGreen(), returnBlue()));
        blue.setBackground(new Color(returnRed(), returnGreen(), returnBlue()));
        green.setBackground(new Color(returnRed(), returnGreen(), returnBlue()));
        red.setBackground(new Color(returnRed(), returnGreen(), returnBlue()));

        // Revalidate and repaint to reflect changes
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Called whenever a slider's value changes. Updates the RGB values based on the slider's position.
     * 
     * @param evt The event triggered by a slider change.
     */
    
    @Override
    public void stateChanged(ChangeEvent evt) {
        if (evt.getSource() == red) {
            redness = red.getValue();
        } else if (evt.getSource() == green) {
            greenness = green.getValue();
        } else if (evt.getSource() == blue) {
            blueness = blue.getValue();
        }
    }

    /**
     * Returns the current red component value.
     * 
     * @return The current red value (0 to 255).
     */
    public static int returnRed() {
        return redness;
    }

    /**
     * Returns the current green component value.
     * 
     * @return The current green value (0 to 255).
     */
    public static int returnGreen() {
        return greenness;
    }

    /**
     * Returns the current blue component value.
     * 
     * @return The current blue value (0 to 255).
     */
    public static int returnBlue() {
        return blueness;
    }

    /**
     * Returns the inverted red component value (255 - red).
     * 
     * @return The inverted red value (0 to 255).
     */
    public static int revertReturnRed() {
        return (255 - redness);
    }

    /**
     * Returns the inverted green component value (255 - green).
     * 
     * @return The inverted green value (0 to 255).
     */
    public static int revertReturnGreen() {
        return (255 - greenness);
    }

    /**
     * Returns the inverted blue component value (255 - blue).
     * 
     * @return The inverted blue value (0 to 255).
     */
    public static int revertReturnBlue() {
        return (255 - blueness);
    }
}
