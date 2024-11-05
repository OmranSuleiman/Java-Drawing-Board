package se.miun.ommo2200.dt187g.jpaint.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.function.Predicate;
import javax.swing.JPanel;

import se.miun.ommo2200.dt187g.jpaint.Drawing;
import se.miun.ommo2200.dt187g.jpaint.geometry.Circle;
import se.miun.ommo2200.dt187g.jpaint.geometry.Point;
import se.miun.ommo2200.dt187g.jpaint.geometry.Rectangle;
import se.miun.ommo2200.dt187g.jpaint.geometry.Shape;

/**
 * Klassen representerar platsen där användaren får rita formen
 * 
 * @author (ommo2200)
 * @version 1.0
 */
public class DrawingPanel extends JPanel {

    private Predicate<Shape> shapeFilter;
    Drawing drawing = new Drawing();
    private Color drawColor;
    private boolean drawIsActive;
    private String activeShape;
    private Point startPoint;
    private Point endPoint;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Color getDrawColor() {
        return drawColor;
    }

    public void setDrawColor(Color drawColor) {
        this.drawColor = drawColor;
    }

    public boolean isDrawIsActive() {
        return drawIsActive;
    }

    public void setDrawIsActive(boolean drawIsActive) {
        this.drawIsActive = drawIsActive;
    }

    public String getActiveShape() {
        return activeShape;
    }

    public void setActiveShape(String activeShape) {
        this.activeShape = activeShape;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setStartPoint(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;

    }

    public void setEndPoint(int x2, int y2) {

        this.x2 = x2;
        this.y2 = y2;

    }

    public DrawingPanel() {
        this.setBackground(Color.WHITE);
        shapeFilter = s -> true;
    }

    public DrawingPanel(Color background) {
        this.setBackground(background);
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    public void removeLastShape() {
        drawing.removeLastShape();
    }

    public void addShape() {
        switch (activeShape) {
            case "Rectangle":
                Point rectStart = new Point(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2);
                Point rectEnd = new Point(x1 > x2 ? x1 : x2, y1 > y2 ? y1 : y2);
                Shape rect = new Rectangle(rectStart, getColorAsHexString(drawColor));
                rect.addPoint(rectEnd);
                drawing.addShape(rect);
                break;
            case "Circle":
                Point circleStart = new Point(x1, y1);
                Point circleEnd = new Point(x2, y1);
                Shape circle = new Circle(circleStart, getColorAsHexString(drawColor));
                circle.addPoint(circleEnd);
                drawing.addShape(circle);
                break;
            default:
                break;
        }
    }

    private void drawRect(Graphics2D g2) {
        java.awt.Shape rect = new java.awt.Rectangle(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
                Math.abs(y1 - y2));
        g2.fill(rect);
    }

    private void drawCircle(Graphics2D g2) {
        java.awt.Shape circle = new Ellipse2D.Double(x1 - Math.abs(x1 - x2), y1 - Math.abs(x1 - x2),
                Math.abs(x1 - x2) * 2, Math.abs(x1 - x2) * 2);
        g2.fill(circle);
    }

    public void setShapeFilter(Predicate<Shape> filter) {
        this.shapeFilter = filter;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawing.getShapes().stream().filter(shapeFilter).forEach(shape -> shape.draw(g2));
        if (drawIsActive) {
            g2.setColor(drawColor);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            switch (activeShape) {
                case "Rectangle":
                    drawRect(g2);
                    break;
                case "Circle":
                    drawCircle(g2);
                    break;
                default:
                    break;
            }
        }
    }

    public static String getColorAsHexString(Color color) {

        String red = Integer.toHexString(color.getRed());
        String green = Integer.toHexString(color.getGreen());
        String blue = Integer.toHexString(color.getBlue());
        red = red.length() == 1 ? "0" + red : red;
        green = green.length() == 1 ? "0" + green : green;
        blue = blue.length() == 1 ? "0" + blue : blue;

        return "#" + red + green + blue;
    }

}
