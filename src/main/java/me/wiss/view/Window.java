package me.wiss.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.Map;

public class Window extends JFrame {

    private final EnumMap<ButtonType, JButton> buttons = new EnumMap<>(ButtonType.class);

    //window is strictly view, only makes buttons, places them, sets everything up
    //rest is up to controller
    public Window(GraphicsPanel graphicsPanel){
        setSize(800, 600);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);


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
            buttonPanel.add(button, BorderLayout.NORTH);
        }

        buttonPanel.setSize(800, getX()/5);

        add(buttonPanel, BorderLayout.NORTH);
        add(graphicsPanel, BorderLayout.CENTER);




        setVisible(true);
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
