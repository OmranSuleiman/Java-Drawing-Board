package se.miun.ommo2200.dt187g.jpaint.GUI;

import java.io.*;
import java.nio.file.*;

import se.miun.ommo2200.dt187g.jpaint.Drawing;
import se.miun.ommo2200.dt187g.jpaint.DrawingException;
import se.miun.ommo2200.dt187g.jpaint.geometry.Shape;

public class FileHandler {

    public static void save(Drawing drawing, String fileName) throws DrawingException {
        if (!fileName.endsWith(".shape")) {
            fileName += ".shape";
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(drawing.getName());
            writer.newLine();
            writer.write(drawing.getAuthor());
            writer.newLine();
            for (Shape shape : drawing.getShapes()) {
                writer.write(shape.toString());
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            System.err.println("Error while saving the drawing: " + e.getMessage());
        }
    }

    public static Drawing load(String fileName) throws DrawingException {
        Drawing drawing = new Drawing();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String name = reader.readLine();
            String author = reader.readLine();

            if (name == null || name.trim().isEmpty()) {
                name = "YOU DIDN'T GAVE THE NAME";
            }
            if (author == null || author.trim().isEmpty()) {
                author = "YOU DIDN'T GAVE THE AUTHOR";
            }

            drawing.setName(name);
            drawing.setAuthor(author);

            String line;
            while ((line = reader.readLine()) != null) {
                Shape shape = Shape.fromString(line);
                if (shape != null) {
                    drawing.addShape(shape);
                } else {
                    System.out.println("Failed to load shape from line: " + line);
                }
            }
        } catch (IOException e) {
            throw new DrawingException("Error while loading the drawing: " + e.getMessage());
        }

        return drawing;
    }

}
