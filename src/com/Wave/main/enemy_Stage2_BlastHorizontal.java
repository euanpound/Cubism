package com.Wave.main;

import java.awt.*;
import java.util.Random;

public class enemy_Stage2_BlastHorizontal extends GameObject {

    Handler handler;
    Random r = new Random();
    int timer = 50 - Game.gameStage;
    boolean warn = false;
    boolean released = false;

    public enemy_Stage2_BlastHorizontal(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        //System.out.println("Hello");
        if(timer == 0){
            handler.addObject(new enemy_BlastHorizontalParticles(x, y, ID.BlasterParticles, handler));
            timer--;
            released = true;
        } else if (timer % 10 == 0 && timer > 0){
            warn = true;
        } else if (timer % 5 == 0){
            warn = false;
        }
        if(timer > 0){
            timer--;
        }
        if(released){
            angelSense();
        }
    }

    public void render(Graphics g) {
        if(warn){
            g.setColor(Color.RED);
            g.fillRect(5, y, 20, 20);
        }
    }

    public Rectangle getBounds() {
        if (timer <= 0) {
            return new Rectangle(0, y, Game.WIDTH, 25);
        } else{
            return new Rectangle(0,0,0,0);
        }
    }

    public Rectangle getBoundsR() {
        return null;
    }

    public void angelSense(){
        int counter = 0;
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            //Collide with enemy
            if(tempObject.getID() == ID.BlasterParticles){
                counter++;
            }
        }
        if(counter == 0){
            handler.removeObject(this);
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getID() == ID.Player) {
                    handler.addObject(new enemy_Stage2_BlastHorizontal(0, tempObject.getY(), ID.Stage2_Blaster, handler));
                }
            }
        }
    }
}
