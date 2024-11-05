package se.miun.ommo2200.dt187g.jpaint.geometry;

import se.miun.ommo2200.dt187g.jpaint.Drawable;
import se.miun.ommo2200.dt187g.jpaint.geometry.Shape;
import java.util.ArrayList;

/**
 * detta klass representerar gemensamma metoder och attribut
 * grundklass för geometrisk figurer
 * 
 * @author (ommo2200)
 * @version 1.1
 */
public abstract class Shape implements Drawable {
    private String color;
    protected ArrayList<Point> points;

    public ArrayList<Point> getPoints() {
        return points;
    }

    public Shape(Point p, String color) {
        this.color = color;
        this.points = new ArrayList<>();
        this.points.add(p);
    }

    public Shape(double x, double y, String color) {
        this(new Point(x, y), color);
    }

    public String getColor() {
        return color;
    }

    protected void setColor(String color) {
        this.color = color;
    }

    public abstract double getCircumference();

    public abstract double getArea();

    public void addPoint(Point p) {
        points.add(p);
    }

    public void addPoint(double x, double y) {
        addPoint(new Point(x, y));

    }

    public abstract boolean hasEndpoint();

    @Override
    public void draw() {
        System.out.println(toString());
    }

    @Override
    public void draw(java.awt.Graphics g) {

    }

    public void hasEndPoint(java.awt.Point point) {

    }

    public java.awt.Shape getShape() {

        return null;
    }

    public static Shape fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 6) {
            return null;
        }

        String type = parts[0].trim();
        int x1 = Integer.parseInt(parts[1].trim());
        int y1 = Integer.parseInt(parts[2].trim());
        int x2 = Integer.parseInt(parts[3].trim());
        int y2 = Integer.parseInt(parts[4].trim());
        String color = parts[5].trim();

        if ("Rectangle".equalsIgnoreCase(type)) {
            Rectangle rectangle = new Rectangle(new Point(x1, y1), color);
            rectangle.addPoint(new Point(x2, y2));
            return rectangle;
        } else if ("Circle".equalsIgnoreCase(type)) {
            Circle circle = new Circle(new Point(x1, y1), color);
            circle.addPoint(new Point(x2, y2));
            return circle;
        }

        return null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "," +
                points.get(0).getX() + "," + points.get(0).getY() + "," +
                points.get(1).getX() + "," + points.get(1).getY() + "," +
                getColor();
    }

}
