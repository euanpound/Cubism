package com.Cubism.main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Player extends GameObject {

    //TODO Make the player flash red when hit, and make him lighter when invincible

    Handler handler;
    private int j = 99;

    public static int pDim = 32;

    private Random r = new Random();

    private boolean isMoving = false;

    private int Stamina = 100;

    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        if(velX != 0 || velY != 0){
            isMoving = true;
        } else{
            isMoving = false;
        }

        x = Game.clamp(x, 0, Game.WIDTH - pDim);
        y = Game.clamp(y, 0, Game.HEIGHT - pDim);

        //Collision Cool-down
        if(j == 100){
            collision();
        } else if (j == 0){
            j = 100;
        } else {
            j--;
        }
        if (isMoving) {
            x += velX;
            y += velY;
            handler.addObject(new Particle(r.nextInt(pDim - 10) + (x + pDim / 2) - pDim/2, r.nextInt(pDim - 10) + (y + pDim / 2) - pDim/2, ID.Particle, handler, 0, 0, 0));
        }

    }

    public void render(Graphics g) {
        //if (deathTimer != 0) {
            if(id == ID.Player) g.setColor(Color.black);
            g.fillRect(x, y, pDim, pDim);
        //}
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, pDim, pDim);
    }

    public Rectangle getBoundsR() {
        return null;
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            //Collide with enemy
            if(tempObject.getID() == (ID.Idiot) || tempObject.getID() == ID.Tracker || tempObject.getID() == ID.Bar || tempObject.getID() == ID.Blaster || tempObject.getID() == ID.Stage2_Blaster){
                if(tempObject.getBounds().intersects(getBounds())) {
                    HUD.HEALTH--;
                    j--;
                }
            }
        }
    }
    //Animate the player's death
    public void playerDeath(){
        for (int i = 0; i < 100; i ++) {
            handler.addObject(new Particle(r.nextInt(pDim) + (x + pDim / 2) - pDim/2, r.nextInt(pDim) + (y + pDim / 2) - pDim/2, ID.Particle, handler, 255, 0, 0));
        }
        handler.removeObject(this);

    }

}
