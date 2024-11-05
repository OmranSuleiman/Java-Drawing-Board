package se.miun.ommo2200.dt187g.jpaint.geometry;

import java.awt.Graphics;

/**
 * Detta klass representerar en rektangel
 * 
 * @author (ommo2200)
 * @version 1.1
 */
public class Rectangle extends Shape {

    public Rectangle(Point startPoint, String color) {
        super(startPoint, color);
    }

    public Rectangle(double x, double y, String color) {
        this(new Point(x, y), color);
    }

    public double getWidth() {
        if (hasEndpoint()) {
            return Math.abs(points.get(1).getX() - points.get(0).getX());
        }
        return -1;
    }

    public double getHeight() {
        if (hasEndpoint()) {
            return Math.abs(points.get(1).getY() - points.get(0).getY());
        }
        return -1;
    }

    public double getCircumference() {
        if (hasEndpoint()) {
            return 2 * (getWidth() + getHeight());
        }
        return -1;
    }

    public double getArea() {
        if (hasEndpoint()) {
            return getWidth() * getHeight();
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

            int x = (int) Math.min(points.get(0).getX(), points.get(1).getX());
            int y = (int) Math.min(points.get(0).getY(), points.get(1).getY());

            int width = (int) getWidth();
            int height = (int) getHeight();

            g.setColor(java.awt.Color.decode(getColor()));
            g.fillRect(x, y, width, height);

        }
    }

    @Override
    public String toString() {

        return "Rectangle," + points.get(0) +
                "," + (hasEndpoint() ? points.get(1) : "N/A") + "," + getColor();
    }
}
