package com.Cubism.main;

import java.awt.*;
import java.util.Random;

public class enemy_Tracker extends GameObject{

    Handler handler;
    private Random r = new Random();

    private int tDim = 16;

    private int tVel = 2;

    public enemy_Tracker(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        tracking();
        collision(25);
        //handler.addObject(new Particle((r.nextInt(10) + (x + tDim/2) - 5), (r.nextInt(10) + (y + tDim/2) - 5), ID.Particle, handler, 255, 102, 0));
    }

    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, tDim, tDim);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, tDim, tDim);
    }

    public Rectangle getBoundsR() {
        return null;
    }

    public void tracking(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == (ID.Player)){
                if(getX() < tempObject.getX() - tDim){
                    velX = tVel;
                }
                if(getX() > tempObject.getX() + tDim){
                    velX = -tVel;
                }
                if(getY() < tempObject.getY() - tDim){
                    velY = tVel;
                }
                if(getY() > tempObject.getY() + tDim){
                    velY = -tVel;
                }

            }
        }
    }

    public void collision(int n){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            //Collide with enemy
            if(tempObject.getID() == (ID.Idiot)) {
                //System.out.println(tempObject.getBounds());
                //System.out.println(getBounds());
                if (tempObject.getBounds().intersects(getBounds())) {
                    for(int j = 0; j < n; j++){
                        handler.addObject(new Particle((r.nextInt(50) + x - 25), (r.nextInt(50) + y - 25), ID.Particle, handler, 255, 102, 0));
                    }
                    handler.removeObject(this);
                    handler.addObject(new enemy_Tracker(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.Tracker, handler));
                }
            }
        }
    }

}
