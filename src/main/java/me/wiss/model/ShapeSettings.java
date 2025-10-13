package me.wiss.model;

import java.awt.*;
import java.io.Serializable;

public class ShapeSettings implements Cloneable, Serializable {
    private Color color;
    private ShapeType shapeType;

    public ShapeSettings(Color color, ShapeType shapeType) {
        this.color = color;
        this.shapeType = shapeType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    public ShapeSettings clone() {
        try {
            ShapeSettings clone = (ShapeSettings) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
