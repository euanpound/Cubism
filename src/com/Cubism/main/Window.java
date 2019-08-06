package com.Cubism.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

}
