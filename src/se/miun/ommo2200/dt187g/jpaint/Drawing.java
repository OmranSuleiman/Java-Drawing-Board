package se.miun.ommo2200.dt187g.jpaint;

import se.miun.ommo2200.dt187g.jpaint.geometry.Shape;

import java.util.ArrayList;
import java.awt.*;

/**
 * Drawing klassen används för att hantera ritning
 * 
 * @author (ommo2200)
 * @version 1.0
 */
public class Drawing implements Drawable {

    public ArrayList<Shape> shapes;

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    private String name;
    private String author;

    public Drawing() {

        this.name = " ";
        this.author = " ";

        shapes = new ArrayList<Shape>();
    }

    public Drawing(String name, String author) throws DrawingException {
        if (name == null || name.trim().isEmpty() || author == null || author.trim().isEmpty()) {
            throw new DrawingException("Name and author cannot be empty.");
        }

        this.name = name;
        this.author = author;
        this.shapes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DrawingException {
        if (name == null || name.trim().isEmpty()) {
            throw new DrawingException("Name cannot be empty.");
        }

        this.name = name;

    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) throws DrawingException {
        if (author == null || author.trim().isEmpty()) {
            throw new DrawingException("Author cannot be empty.");
        }

        this.author = author;
    }

    public void addShape(Shape shape) {
        if (shape != null) {
            shapes.add(shape);
            System.out.println("Shape added! Total shapes: " + shapes.size());
        } else {
            throw new IllegalArgumentException("Shape cannot be null.");
        }
    }

    public int getSize() {
        return shapes.size();
    }

    public double getTotalCircumference() {
        double totalCircumference = 0.0;
        for (Shape shape : shapes) {
            double circumference = shape.getCircumference();
            if (circumference >= 0) {
                totalCircumference += circumference;
            }
        }
        return totalCircumference;
    }

    public double getTotalArea() {
        double totalArea = 0.0;
        for (Shape shape : shapes) {
            double area = shape.getArea();
            if (area >= 0) {
                totalArea += area;
            }
        }
        return totalArea;
    }

    @Override
    public void draw() {
        System.out.println("Drawing: " + name + " by " + author);
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    public void removeLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
        }
    }

    @Override
    public String toString() {
        double totalCircumference = getTotalCircumference();
        double totalArea = getTotalArea();

        return "Drawing[name=" + name + ", author=" + author + ", size=" + getSize() +
                ", totalCircumference=" + (totalCircumference >= 0 ? totalCircumference : 0) +
                ", totalArea=" + (totalArea >= 0 ? totalArea : 0) + "]";
    }

}
