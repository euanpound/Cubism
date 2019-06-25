package com.Cubism.main;

import javax.swing.*;
import java.awt.*;


//OBSOLETE
public class DeathWindow {
    public DeathWindow(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setPreferredSize(new Dimension(480, 320));
        frame.setMinimumSize(new Dimension(480, 320));
        frame.setMaximumSize(new Dimension(480, 320));

        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setFocusable(true);
        frame.setVisible(true);
        game.start();
        JButton restart = new JButton();
        restart.setText("Restart");
        restart.setSize(new Dimension(50, 30));
        restart.setLocation(0, 0);
        restart.setBackground(Color.BLACK);
        JButton close = new JButton();
        close.setText("Close");
        close.setSize(new Dimension(50, 30));
        close.setLocation(50, 0);
        restart.setBackground(Color.BLACK);

        frame.add(restart);
        frame.add(close);
    }


}
