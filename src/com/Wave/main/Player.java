package com.Wave.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Handler handler;
    private int j = 100;

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
        if(id == ID.Player) g.setColor(Color.black);
        g.fillRect(x, y, pDim, pDim);
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
            if(tempObject.getID() == (ID.Idiot)){
                if(tempObject.getBounds().intersects(getBounds())) {
                    HUD.HEALTH--;
                    j--;
                }
            } else if(tempObject.getID() == ID.Tracker){
                if(tempObject.getBounds().intersects(getBounds())){
                    HUD.HEALTH--;
                    j--;
                }
            } else if(tempObject.getID() == ID.Bar){
                if(tempObject.getBounds().intersects(getBounds()) || tempObject.getBoundsR().intersects(getBounds())){
                    HUD.HEALTH--;
                    j--;
                }
            }
        }
    }
}
