package me.wiss.view;

import me.wiss.model.Canvas;
import me.wiss.model.GeometricShape;
import me.wiss.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class GraphicsPanel extends JPanel {

    Canvas canvas;

    public GraphicsPanel(Canvas canvas){
        setBackground(Color.white);

        this.canvas = canvas;
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Iterator<Shape> drawIterator = canvas.getIterator();
        while(drawIterator.hasNext()){
            Shape shape = drawIterator.next();
            graphics.setColor(shape.getColor());
            switch (shape.getShapeType()){
                case DOT -> {
                    int radius = 15;
                    //first 2 variables is top-left and the other two are width and height
                    graphics.fillOval(shape.getX() - radius, shape.getY() - radius, radius*2, radius*2);
                }
                case OVAL -> {
                    GeometricShape geometricShape = (GeometricShape) shape;
                    int x1 = geometricShape.getX();
                    int y1 = geometricShape.getY();
                    int x2 = geometricShape.getWidth();
                    int y2 = geometricShape.getHeight();

                    graphics.fillOval(x1, y1, x2, y2);
                }
                case RECTANGLE -> {
                    GeometricShape geometricShape = (GeometricShape) shape;

                    int x1 = geometricShape.getX();
                    int y1 = geometricShape.getY();
                    int x2 = geometricShape.getWidth();
                    int y2 = geometricShape.getHeight();

                    graphics.fillRect(x1, y1, x2, y2);
                }
            }
        }
    }

    public void addShape(Shape shape){
        canvas.addShape(shape);
    }

    public void undo(){
        canvas.undo();
    }

}
