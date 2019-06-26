package com.Cubism.main;

import java.awt.*;
import java.util.Random;

public class enemy_Stage2_BlastVertical extends GameObject {

    //Same as BlastHorizontal

    Handler handler;
    Random r = new Random();
    int timer = 50 - Game.gameStage;
    boolean warn = false;
    boolean released = false;

    public enemy_Stage2_BlastVertical(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        //System.out.println("Hello");
        if(timer == 0){
            handler.addObject(new enemy_BlastVerticalParticles(x, y, ID.BlasterParticles, handler));
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
            g.fillRect(x, 5, 20, 20);
        }
    }

    @Override
    public void playerDeath() {

    }

    public Rectangle getBounds() {
        if (timer <= 0) {
            return new Rectangle(x, 0, 25, Game.HEIGHT);
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
            Game.gameStage++;
            handler.removeObject(this);
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getID() == ID.Player) {
                    handler.addObject(new enemy_Stage2_BlastVertical(tempObject.getX(), 0, ID.Stage2_Blaster, handler));
                }
            }
        }
    }
}
