package me.wiss.model;


import java.awt.*;

public class GeometricShape extends Shape {
    private int x2;
    private int y2;

    /**
     *
     * @param shapeType must be a geometric shape like rectangle or dot!
     */
    public GeometricShape(int x, int y, int x2, int y2, Color color, ShapeType shapeType) {
        super(x, y, color, shapeType);
        if (shapeType == ShapeType.DOT){
            throw new IllegalArgumentException("Dot is not a geometric shape!");
        }
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
