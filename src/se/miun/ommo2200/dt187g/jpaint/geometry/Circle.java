package se.miun.ommo2200.dt187g.jpaint.geometry;

import java.awt.Graphics;
//import se.miun.ommo2200.dt187g.jpaint.geometry.Circle;

/**
 * Klassen representerar en cirkelformad
 * 
 * @author (ommo2200)
 * @version 1.0
 */
public class Circle extends Shape {
    private static final double PI = 3.14159265;

    public Circle(Point startPoint, String color) {
        super(startPoint, color);
    }

    public Circle(double x, double y, String color) {
        this(new Point(x, y), color);
    }

    public double getRadius() {
        if (hasEndpoint()) {
            return points.get(0).distanceTo(points.get(1));

        }
        return -1;
    }

    public double getCircumference() {
        if (hasEndpoint()) {
            return 2 * PI * getRadius();
        }
        return -1;
    }

    public double getArea() {
        if (hasEndpoint()) {
            return PI * getRadius() * getRadius();

        }
        return -1;
    }

    public void addPoint(Point p) {
        if (points.size() < 2) {
            points.add(p);
        } else {
            points.set(1, p);
        }
    }

    public void addPoint(double x, double y) {
        addPoint(new Point(x, y));
    }

    public boolean hasEndpoint() {
        return points.size() == 2;
    }

    @Override
    public void draw() {
        System.out.println(toString());
    }

    @Override
    public void draw(Graphics g) {
        if (hasEndpoint()) {
            int radius = (int) getRadius();
            int x = (int) (points.get(0).getX() - radius);
            int y = (int) (points.get(0).getY() - radius);
            int diameter = 2 * radius;

            g.setColor(java.awt.Color.decode(getColor()));
            g.fillOval(x, y, diameter, diameter);
        }
    }

    @Override
    public String toString() {
        return "Circle," + points.get(0) +
                "," + (hasEndpoint() ? points.get(1) : "N/A") +
                "," + getColor();
    }

}
