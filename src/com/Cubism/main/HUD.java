package com.Cubism.main;

import javax.swing.*;
import java.awt.*;

public class HUD {

    public static int HEALTH = 1;

    public void tick(){

    }
    public void render(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.green);
        g.fillRect(16, 16, HEALTH * 20 - 2, 30);
        //g.setColor(Color.darkGray);
        //g.fillRect(15, (Game.HEIGHT - 15), 200, 32);
    }
    public static Rectangle getBounds(){
        return new Rectangle(15, 15, 200, 32);
    }

}
