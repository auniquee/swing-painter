package me.wiss.model;

import java.awt.*;

public class Shape implements Cloneable{

    private int x;
    private int y;
    private ShapeSettings shapeSettings;



    public Shape(int x, int y, ShapeSettings shapeSettings){
        this.x = x;
        this.y = y;
        this.shapeSettings = shapeSettings;
    }

    public Shape(int x, int y, Color color, ShapeType shapeType) {
        this.x = x;
        this.y = y;
        this.shapeSettings = new ShapeSettings(color, shapeType);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return shapeSettings.getColor();
    }

    public ShapeType getShapeType() {
        return shapeSettings.getShapeType();
    }
    public ShapeSettings getShapeSettings() {
        return shapeSettings;
    }

    @Override
    public Shape clone() {
        try {
            Shape clone = (Shape) super.clone();
            clone.shapeSettings = new ShapeSettings(this.shapeSettings.getColor(), this.shapeSettings.getShapeType());
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
