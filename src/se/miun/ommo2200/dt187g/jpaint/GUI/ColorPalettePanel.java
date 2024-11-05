package se.miun.ommo2200.dt187g.jpaint.GUI;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ColorPalettePanel extends JPanel {

    private ArrayList<ColorPanel> colorPanels; // En lista som lagrar ColorPanel-objekt

    /*
     * Oavsett vilken constructor som anropas, sätt layout till GridLayout.
     */
    public ColorPalettePanel() {
        this.setLayout(new GridLayout());
        colorPanels = new ArrayList<>();
    }

    /*
     * Denna constructor tar emot en ArrayList med ColorPanel-objekt och
     * initialiserar colorPanels med listan. Varje ColorPanel i listan läggs till i
     * ColorPalettePanel.
     */
    public ColorPalettePanel(ArrayList<ColorPanel> colorPanels) {
        this(); // Anropa den tidigare constructor för att sätta layout och initiera colorPanels.
        this.colorPanels = colorPanels;
        for (ColorPanel cp : colorPanels) {
            this.add(cp); // Lägg till varje ColorPanel i ColorPalettePanel.
        }
    }

    /*
     * Lägg till ett ColorPanel-objekt i colorPanels och lägg det till i
     * ColorPalettePanel för att göra det synligt.
     */
    public void addColorPanel(ColorPanel cp) {
        colorPanels.add(cp); // lägg den i listan 
        this.add(cp);
    }

    /*
     * Sätt en MouseListener på alla ColorPanel-objekt i colorPanels.
     * för att övervaka mustryck och rörelsen
     */
    public void setMouseListenerForColorPanels(MouseListener listener) {
        //iterera geneom colorPanels i listan 
        for (ColorPanel cp : colorPanels) {
            cp.addMouseListener(listener);
        }
    }
}
