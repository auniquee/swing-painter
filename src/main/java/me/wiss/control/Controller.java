package me.wiss.control;

import me.wiss.model.*;
import me.wiss.model.Canvas;
import me.wiss.model.Shape;
import me.wiss.view.GraphicsPanel;
import me.wiss.view.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EnumMap;
import java.util.Map;

public class Controller {


    private final Canvas canvas; // model
    private final ShapeSettings shapeSettings; //model
    private final Window window; // view
    private final GraphicsPanel graphicsPanel; // view
    private final ButtonListener buttonListener; //controller
    private final DrawListener drawListener; //controller

    public Controller(){
        canvas = new Canvas();
        shapeSettings = new ShapeSettings(Color.BLACK, ShapeType.DOT);

        graphicsPanel = new GraphicsPanel(canvas);
        window = new Window(graphicsPanel);

        buttonListener = new ButtonListener();
        drawListener = new DrawListener();

        graphicsPanel.addMouseListener(drawListener);
        setupButtonListeners(window.getButtons());

    }

    private void setupButtonListeners(EnumMap<Window.ButtonType, JButton> buttons){
        for (Map.Entry<Window.ButtonType, JButton> entry : buttons.entrySet()){
            entry.getValue().addActionListener(buttonListener);
            entry.getValue().setActionCommand(entry.getKey().name());
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            switch (actionEvent.getActionCommand()) {
                case "DOT" -> shapeSettings.setShapeType(ShapeType.DOT);
                case "RECT" -> shapeSettings.setShapeType(ShapeType.RECTANGLE);
                case "OVAL" -> shapeSettings.setShapeType(ShapeType.OVAL);
                case "RED" -> shapeSettings.setColor(Color.RED);
                case "BLACK" -> shapeSettings.setColor(Color.BLACK);
                case "GREEN" -> shapeSettings.setColor(Color.GREEN);
                default -> throw new IllegalStateException();
            }
        }
    }
    private class DrawListener implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(shapeSettings.getShapeType() == ShapeType.DOT){
                int x = mouseEvent.getX(), y = mouseEvent.getY();
                graphicsPanel.addShape(new Shape(x, y, shapeSettings.clone()));
                graphicsPanel.repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            if (shapeSettings.getShapeType() != ShapeType.DOT){
                GeometricShape previewShape = canvas.getPreviewShape();
                ShapeSettings previewSettings = previewShape.getShapeSettings();

                previewShape.setX(mouseEvent.getX());
                previewShape.setY(mouseEvent.getY());
                previewShape.setX2(mouseEvent.getX());
                previewShape.setY2(mouseEvent.getY());

                previewSettings.setColor(shapeSettings.getColor());
                previewSettings.setShapeType(shapeSettings.getShapeType());
                graphicsPanel.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if(shapeSettings.getShapeType() != ShapeType.DOT){
                canvas.addShape(canvas.getPreviewShape());
                canvas.resetPreviewShape();

                graphicsPanel.repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}

        @Override
        public void mouseExited(MouseEvent mouseEvent) {}

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            if(shapeSettings.getShapeType() != ShapeType.DOT){
                GeometricShape previewShape = canvas.getPreviewShape();

                previewShape.setX2(mouseEvent.getX());
                previewShape.setY2(mouseEvent.getY());

                graphicsPanel.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {}
    }
}
