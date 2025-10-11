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
        graphicsPanel.addMouseMotionListener(drawListener);
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

                int eventX = mouseEvent.getX();
                int eventY = mouseEvent.getY();

                previewShape.setX(eventX);
                previewShape.setY(eventY);
                previewShape.setWidth(0);
                previewShape.setHeight(0);
                canvas.setPreviewShapeStartX(eventX);
                canvas.setPreviewShapeStartY(eventY);

                previewSettings.setColor(shapeSettings.getColor());
                previewSettings.setShapeType(shapeSettings.getShapeType());
                graphicsPanel.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if(shapeSettings.getShapeType() != ShapeType.DOT){
                canvas.addShape(canvas.getPreviewShape().clone());
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

                int eventX = mouseEvent.getX();
                int eventY = mouseEvent.getY();

                int startX = canvas.getPreviewShapeStartX();
                int startY = canvas.getPreviewShapeStartY();

                // Medveten om att man kan göra detta med nested if-satser, men anser att det blir väldigt svårt att läsa
                // och tolka.
                // Varje if sats hanterar varje kvardrant för sig, och sista else-blocket hanterar edge-cases när
                // eventX == startX och/eller samma fast med Y.
                int width, height;
                if(eventX > startX && eventY > startY) { //Ner höger kvadrant
                    width = eventX - startX;
                    height = eventY - startY;

                    previewShape.setX(startX);
                    previewShape.setY(startY);
                }else if(eventX < startX && eventY > startY){ // Ner vänster kvadrant
                    width = startX - eventX;
                    height = eventY - startY;

                    previewShape.setX(eventX);
                    previewShape.setY(startY);
                } else if(eventX > startX && eventY < startY) { // Upp höger kvadrant
                    width = eventX - startX;
                    height = startY - eventY;

                    previewShape.setX(startX);
                    previewShape.setY(eventY);
                }
                else if(eventX < startX && eventY < startY) { // Upp vänster kvadrant
                    width = startX - eventX;
                    height = startY - eventY;

                    previewShape.setX(eventX);
                    previewShape.setY(eventY);
                }else { // mitt emellan kvadranter
                    width = 0;
                    height = 0;
                }
                previewShape.setWidth(width);
                previewShape.setHeight(height);
                graphicsPanel.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {}
    }


}
