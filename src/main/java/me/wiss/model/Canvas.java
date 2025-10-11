package me.wiss.model;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Canvas {
    private final Deque<Shape> shapes = new ArrayDeque<>(); //index 0 is last added element
    private final GeometricShape previewShape = new GeometricShape(0, 0, 0, 0, Color.BLACK, ShapeType.OVAL);
    private int previewShapeStartX;
    private int previewShapeStartY;

    public void addShape(Shape s) {
        shapes.push(s);
    }

    public Shape undo() {
        return shapes.pop();
    }

    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    /**
     * .next() will return first element drawn.
     * @return an iterator starting with the first shape drawn.
     */
    public Iterator<Shape> getIterator(){
        Deque<Shape> tempList = new ArrayDeque<>(shapes);
        tempList.addFirst(previewShape);
        return tempList.reversed().iterator();
    }

    public GeometricShape getPreviewShape() {
        return previewShape;
    }

    public void resetPreviewShape(){
        previewShape.setX(0);
        previewShape.setY(0);
        previewShape.setWidth(0);
        previewShape.setHeight(0);
    }

    public int getPreviewShapeStartX() {
        return previewShapeStartX;
    }

    public void setPreviewShapeStartX(int previewShapeStartX) {
        this.previewShapeStartX = previewShapeStartX;
    }

    public int getPreviewShapeStartY() {
        return previewShapeStartY;
    }

    public void setPreviewShapeStartY(int previewShapeStartY) {
        this.previewShapeStartY = previewShapeStartY;
    }
}
