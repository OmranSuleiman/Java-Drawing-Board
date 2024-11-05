package se.miun.ommo2200.dt187g.jpaint.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * Klassen representerar panelen som visar statusinfo, musens koordinater och
 * den valda färgen
 * 
 * @author (ommo2200)
 * @version 1.0
 */
public class StatusBarPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private JLabel coordinates;
    private JPanel selectedColor;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private OnChangeListener<StatusBarPanel> listener;

    public StatusBarPanel() {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());

        coordinates = new JLabel("0, 0");
        selectedColor = new JPanel();
        leftPanel = new JPanel(new BorderLayout());

        rightPanel = new JPanel(new BorderLayout());

        selectedColor.setBackground(Color.RED);

        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(new JLabel("COORDINATES: "), BorderLayout.WEST);
        leftPanel.add(coordinates, BorderLayout.CENTER);

        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.add(new JLabel("SELECTED COLOR: "), BorderLayout.WEST);
        rightPanel.add(selectedColor, BorderLayout.CENTER);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }

    public void updateCoordinates(int x, int y) {
        coordinates.setText(x + ", " + y);

    }

    public void resetCoordinates() {
        coordinates.setText("0, 0");
    }

    public void setOnChangeListener(OnChangeListener<StatusBarPanel> listener) {
        this.listener = listener;
    }

    public void updateSelectedColor(Color color) {
        selectedColor.setBackground(color);
        if (listener != null) {
            listener.onChange(this);
        }
    }

    public Color getSelectedColor() {
        return selectedColor.getBackground();
    }

}
