package se.miun.ommo2200.dt187g.jpaint.geometry;

import se.miun.ommo2200.dt187g.jpaint.Drawable;

/**
 * Point klassen representerar koordinatsystem med x, y
 * 
 * @author (ommo2200)
 * @version 1.1
 */

public class Point implements Drawable {
    private double x;
    private double y;

    public Point() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void draw() {
    }

    public void draw(java.awt.Graphics g) {
    }

    @Override
    public String toString() {
        return (int) x + "," + (int) y;
    }

    public double distanceTo(Point point) {
        double PointX = this.x - point.getX();
        double PointY = this.y - point.getY();
        return Math.sqrt(PointX * PointX + PointY * PointY);
    }

}
