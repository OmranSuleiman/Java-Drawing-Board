package se.miun.ommo2200.dt187g.jpaint.GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import se.miun.ommo2200.dt187g.jpaint.Drawing;
import se.miun.ommo2200.dt187g.jpaint.DrawingException;
import se.miun.ommo2200.dt187g.jpaint.geometry.Circle;
import se.miun.ommo2200.dt187g.jpaint.geometry.Rectangle;

/**
 * Klassen representerar huvudfönstret för ritapplikationer.
 * Laboration: 5
 * Datum: 2023-12-13
 * 
 * @author (ommo2200)
 * @version 1.0
 *          //omran
 */

public class JPaintFrame extends JFrame {

    private Container c = this.getContentPane();
    private StatusBarPanel statusBarPanel;
    private DrawingPanel drawingPanel;
    // private Drawing drawing;
    private ColorPalettePanel colorPalettePanel;
    private JComboBox<String> toolComboBox;
    private String header;

    public JPaintFrame() {
        init();
    }

    private void init() {

        statusBarPanel = new StatusBarPanel();
        drawingPanel = new DrawingPanel();
        drawingPanel.drawing = new Drawing();

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(
                "C:\\Users\\omran\\OneDrive\\Skrivbord\\Projekt_mapp\\src\\se\\miun\\ommo2200\\dt187g\\jpaint\\java-icon-image-10.png")
                .getImage());

        Menu menu = createMenu();
        JRadioButton allButton = new JRadioButton("All");
        allButton.setSelected(true);
        JRadioButton circleButton = new JRadioButton("Circle");
        JRadioButton rectangleButton = new JRadioButton("Rectangle");
        @SuppressWarnings("serial")
        ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>() {
            {
                add(allButton);
                add(circleButton);
                add(rectangleButton);
            }
        };

        JMenu jMenu = new JMenu("FILTER");
        ButtonGroup group = new ButtonGroup();
        for (var rb : radioButtons) {
            jMenu.add(rb);
            group.add(rb);
        }
        menu.add(jMenu);
        setJMenuBar(menu);
        this.header = "GRAFISKT ANVÄNDARGRÄNSSNITT";
        this.setTitle(header);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(0, 50));

        colorPalettePanel = new ColorPalettePanel();
        colorPalettePanel.addColorPanel(new ColorPanel(Color.RED));
        colorPalettePanel.addColorPanel(new ColorPanel(Color.PINK));
        colorPalettePanel.addColorPanel(new ColorPanel(Color.ORANGE));
        colorPalettePanel.addColorPanel(new ColorPanel(Color.BLACK));
        colorPalettePanel.addColorPanel(new ColorPanel(Color.MAGENTA));
        colorPalettePanel.addColorPanel(new ColorPanel(Color.GREEN));

        toolComboBox = new JComboBox<>(new String[] { "Rectangle", "Circle" });
        toolComboBox.setPreferredSize(new Dimension(100, 0));
        toolComboBox.setSelectedIndex(1);

        toolComboBox.addActionListener(e -> {
            String selectedShape = (String) toolComboBox.getSelectedItem();
            drawingPanel.setActiveShape(selectedShape);
        });

        drawingPanel.setDrawColor(Color.BLACK);
        drawingPanel.setActiveShape("Circle");

        CustomMouseAdapter customMouseAdapter = new CustomMouseAdapter();
        drawingPanel.addMouseListener(customMouseAdapter);
        drawingPanel.addMouseMotionListener(customMouseAdapter);

        statusBarPanel.setPreferredSize(new Dimension(0, 45));

        colorPalettePanel.setMouseListenerForColorPanels(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getSource() instanceof ColorPanel) {
                    ColorPanel cp = (ColorPanel) e.getSource();
                    statusBarPanel.updateSelectedColor(cp.getColor());
                }
            }
        });

        topPanel.setLayout(new BorderLayout());
        topPanel.add(colorPalettePanel, BorderLayout.CENTER);
        topPanel.add(toolComboBox, BorderLayout.LINE_END);

        c.add(topPanel, BorderLayout.NORTH);
        c.add(drawingPanel, BorderLayout.CENTER);
        c.add(statusBarPanel, BorderLayout.SOUTH);

        statusBarPanel.setOnChangeListener(new OnChangeListener<StatusBarPanel>() {
            @Override
            public void onChange(StatusBarPanel object) {
                drawingPanel.setDrawColor(object.getSelectedColor());
                drawingPanel.repaint();
            }
        });

        allButton.addActionListener(event -> {
            drawingPanel.setShapeFilter(filter -> (filter instanceof Circle) || (filter instanceof Rectangle));
            drawingPanel.repaint();
        });

        circleButton.addActionListener(event -> {
            drawingPanel.setShapeFilter(filter -> filter instanceof Circle);
            drawingPanel.repaint();
        });

        rectangleButton.addActionListener(event -> {
            drawingPanel.setShapeFilter(filter -> filter instanceof Rectangle);
            drawingPanel.repaint();
        });

    }

    class CustomMouseAdapter extends MouseAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            drawingPanel.setEndPoint(e.getX(), e.getY());
            drawingPanel.repaint();

        }

        @Override
        public void mousePressed(MouseEvent e) {
            drawingPanel.setDrawIsActive(true);
            drawingPanel.setStartPoint(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            drawingPanel.setDrawIsActive(false);
            drawingPanel.setEndPoint(e.getX(), e.getY());
            drawingPanel.addShape();

        }

        @Override
        public void mouseExited(MouseEvent e) {
            statusBarPanel.resetCoordinates();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            statusBarPanel.updateCoordinates(e.getX(), e.getY());
        }
    }

    private Menu createMenu() {
        Menu menu = new Menu();

        String sFile = "FILE";
        String sEdit = "EDIT";
        String sDrawing = "DRAWING";
        menu.addJMenu(sFile);
        menu.getJMenu(0).setMnemonic(KeyEvent.VK_F);
        menu.addJMenuItem(sFile, "NEW...", al -> {
            String newName = JOptionPane.showInputDialog(this, "PLEASE GIVE THE THE NAME: ");

            if (newName == null) {
                return;
            }
            String newAuthor = JOptionPane.showInputDialog(this, "PLEASE GIVE THE AUTHOR NAME: ");

            if (newAuthor == null) {
                return;
            }

            try {
                drawingPanel.drawing.setName(newName);
            } catch (DrawingException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fel", JOptionPane.ERROR_MESSAGE);

            }
            try {
                drawingPanel.drawing.setAuthor(newAuthor);
            } catch (DrawingException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fel", JOptionPane.ERROR_MESSAGE);

            }

        }, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));

        menu.addJMenuItem(sFile, "Load...", al -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    drawingPanel.drawing = FileHandler.load(selectedFile.getAbsolutePath());
                } catch (DrawingException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                setDrawingTitle(drawingPanel.drawing.getName(), drawingPanel.drawing.getAuthor());
                updateTitle();
                drawingPanel.repaint();

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));

        menu.addJMenuItem(sFile, "Save As...", al -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    FileHandler.save(drawingPanel.drawing, selectedFile.getAbsolutePath());
                } catch (DrawingException e) {
                    e.printStackTrace();
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

        menu.addJMenuItem(sFile, "INFO", al -> {

            String title = ((drawingPanel.drawing.getName() != null || !drawingPanel.drawing.getName().isEmpty())
                    ? drawingPanel.drawing.getName()
                    : "[UNTITLED DRAWING]") +
                    ((drawingPanel.drawing.getAuthor() != null || !drawingPanel.drawing.getAuthor().isEmpty())
                            ? " BY " + drawingPanel.drawing.getAuthor()
                            : "");

            int totalNumberOfShapes = drawingPanel.drawing.getSize();
            double totalCircumference = drawingPanel.drawing.getTotalCircumference();
            double totalArea = drawingPanel.drawing.getTotalArea();

            String message = title + "\n" + "Number of shapes: "
                    + totalNumberOfShapes + "\n" + "Total area: "
                    + totalArea + "\n" + "Total Circumference: " + totalCircumference + "\n";

            JOptionPane.showMessageDialog(this, message);

        });

        menu.getJMenu(0).addSeparator();

        menu.addJMenuItem(sFile, "EXIT", al -> {
            int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO EXIT?");
            if (confirm == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });

        menu.addJMenu(sEdit);

        menu.getJMenu(1).setMnemonic(KeyEvent.VK_E);
        menu.addJMenuItem(sEdit, "UNDO", al -> {
            drawingPanel.removeLastShape();
            drawingPanel.repaint();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        menu.addSubJMenu(sEdit, sDrawing);
        menu.getJMenu(1).setMnemonic(KeyEvent.VK_F);

        menu.addJMenuItem(sDrawing, "NAME...", al -> {
            String newName = JOptionPane.showInputDialog("ENTER A NEW NAME FOR YHE DRAWING: ");

            if (newName == null) {
                return;
            }
            try {
                drawingPanel.drawing.setName(newName);
                setDrawingTitle(drawingPanel.drawing.getName(), drawingPanel.drawing.getAuthor());
                updateTitle();
            } catch (DrawingException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        });

        menu.addJMenuItem(sDrawing, "AUTHOR...", al -> {
            String newAuthor = JOptionPane.showInputDialog("ENTER A NEW AUTHOR FOR YHE DRAWING:");

            if (newAuthor == null) {
                return;
            }
            try {
                drawingPanel.drawing.setAuthor(newAuthor);
                setDrawingTitle(drawingPanel.drawing.getName(), drawingPanel.drawing.getAuthor());
                updateTitle();
            } catch (DrawingException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        });

        return menu;
    }

    private void setDrawingTitle(final String name, final String author) {

        this.header = ((name == null || name.isEmpty()) ? "[UNTITLED DRAWING]" : name) +
                ((author == null || author.isEmpty()) ? " " : " BY " + author);

    }

    private void updateTitle() {
        setTitle(header);
    }

}
