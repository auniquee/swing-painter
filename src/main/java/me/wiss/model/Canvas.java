package me.wiss.model;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Canvas {
    private final Deque<Shape> shapes = new ArrayDeque<>();
    private final GeometricShape previewShape = new GeometricShape(0, 0, 0, 0, Color.BLACK, ShapeType.OVAL);
    //index 0 is last added element

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
        tempList.add(previewShape);
        return tempList.reversed().iterator();
    }

    public GeometricShape getPreviewShape() {
        return previewShape;
    }

    public void resetPreviewShape(){
        previewShape.setX(0);
        previewShape.setY(0);
        previewShape.setX2(0);
        previewShape.setY2(0);


    }
}
