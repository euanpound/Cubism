package com.Cubism.main;

import java.awt.*;
import java.util.Random;

public class Particle extends GameObject {

    Handler handler;
    Random r;

    private int pDim = 5;
    int j = 255;

    int red, green, blue;

    public Particle(int x, int y, ID id, Handler handler, int red, int green, int blue) {
        super(x, y, id);
        this.handler = handler;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void tick() {
        if(j > 0){
            j -= 5;
        }
        if(j == 0){
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(red, green, blue, j));
        g.fillRect(x, y, pDim, pDim);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, pDim, pDim);
    }


    public Rectangle getBoundsR() {
        return null;
    }
}
