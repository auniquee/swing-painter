package me.wiss.view;

import me.wiss.model.ShapeSettings;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class Window extends JFrame {

    private final EnumMap<ButtonType, JButton> buttons = new EnumMap<>(ButtonType.class);
    private final JPanel modePanel;
    //window is strictly view, only makes buttons, places them, sets everything up
    //rest is up to controller
    public Window(GraphicsPanel graphicsPanel){
        setSize(800, 600);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, getWidth() / 60, 5));
        modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        modePanel.setBackground(Color.gray);

        buttonPanel.setBackground(Color.gray);
        buttonPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 20));

        JTextArea modeText = new JTextArea();
        modeText.setRows(1);
        modeText.setBackground(Color.GRAY);
        modeText.setSelectionStart(0);
        modePanel.add(modeText, BorderLayout.WEST);

        buttons.put(ButtonType.BLACK, new JButton("black"));
        buttons.put(ButtonType.RED, new JButton("red"));
        buttons.put(ButtonType.GREEN, new JButton("green"));
        buttons.put(ButtonType.DOT, new JButton("dot"));
        buttons.put(ButtonType.OVAL, new JButton("oval"));
        buttons.put(ButtonType.RECT, new JButton("rect"));
        buttons.put(ButtonType.UNDO, new JButton("undo"));
        buttons.put(ButtonType.SAVE, new JButton("save"));
        buttons.put(ButtonType.LOAD, new JButton("load"));

        for(JButton button : buttons.values()){
            button.setPreferredSize(
                    new Dimension(
                            buttonPanel.getPreferredSize().width / 11,
                            buttonPanel.getPreferredSize().height
                    )
            );
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.NORTH);
        add(modePanel, BorderLayout.SOUTH);
        add(graphicsPanel, BorderLayout.CENTER);




        setVisible(true);
    }

    public void setMode(ShapeSettings shapeSettings) {
        ((JTextArea) modePanel.getComponent(0)).setText("Mode: " + shapeSettings.getShapeType().name() + " using color " + shapeSettings.getColor().toString());
    }

    public String showInputBox(boolean save){
        return JOptionPane.showInputDialog(
                "Give a file name for "
                + (save ? "save" : "load")
                + " operation"
        );
    }

    public EnumMap<ButtonType, JButton> getButtons() {
        return buttons;
    }

    public enum ButtonType {
        BLACK,
        RED,
        GREEN,
        DOT,
        OVAL,
        RECT,
        UNDO,
        SAVE,
        LOAD
    }
}
