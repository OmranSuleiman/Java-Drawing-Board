package se.miun.ommo2200.dt187g.jpaint.GUI;
import javax.swing.*;
import java.awt.*;
import static javax.swing.plaf.metal.MetalLookAndFeel.getBlack;

/**
 * Klassen representerar 
 * @author (ommo2200)
 * @version 1.0
 */
public class ColorPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    //Sätt bakgrunden på denna komponent till det
	//Color-objekt som skickas som argument.
    public ColorPanel(Color color) {
        this.setBackground(color);
    }


    // Returnera bakgrunden för detta objekt.
    public Color getColor() {
        return this.getBackground();
    }

    public String getColorAsHexString() {
        Color color = this.getBackground(); // getBackground hämtar den valda färgen och lägger den i color
        //omvandla till hexadecimal strängar med hälpa av string format sätt ihop att de tre färgar med #
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue(), getBlack());
    }
}
