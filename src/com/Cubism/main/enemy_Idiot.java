package com.Cubism.main;

import java.awt.*;
import java.util.Random;

public class enemy_Idiot extends GameObject{

    //Inject Handler
    Handler handler;

    private int iDim = 16;

    Random r = new Random();
    public enemy_Idiot(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = r.nextInt(3) + 2;
        velY = r.nextInt(3) + 2;
        //Inject Handler
        this.handler = handler;
    }

    public void tick() {
        //Move the object
        x += velX;
        y += velY;

        if(getX() < 0 || getX() > Game.WIDTH - iDim){
            velX *= -1;
        }
        if(getY() < 0 || getY() > Game.HEIGHT - iDim){
            velY *= -1;
        }
        //tracking();
    }

    //Render the object
    public void render(Graphics g) {
        if(id == ID.Idiot) g.setColor(Color.red);
        g.fillRect(x, y, iDim, iDim);
    }

    @Override
    public void playerDeath() {

    }

    //Return rectangle
    public Rectangle getBounds(){
        return new Rectangle(x, y, iDim, iDim);
    }

    public Rectangle getBoundsR() {
        return null;
    }
}
