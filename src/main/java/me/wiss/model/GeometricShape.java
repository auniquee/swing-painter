package me.wiss.model;


import java.awt.*;

public class GeometricShape extends Shape {
    private int width;
    private int height;

    /**
     *
     * @param shapeType must be a geometric shape like rectangle or dot!
     */
    public GeometricShape(int x, int y, int width, int height, Color color, ShapeType shapeType) {
        super(x, y, color, shapeType);
        if (shapeType == ShapeType.DOT){
            throw new IllegalArgumentException("Dot is not a geometric shape!");
        }
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
