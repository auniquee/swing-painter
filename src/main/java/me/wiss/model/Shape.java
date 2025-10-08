package me.wiss.model;

import java.awt.*;

public class Shape {

    private int x;
    private int y;
    private final ShapeSettings shapeSettings;



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
}
