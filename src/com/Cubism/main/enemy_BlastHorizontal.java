package com.Cubism.main;

import java.awt.*;
import java.util.Random;

public class enemy_BlastHorizontal extends GameObject {

    //Variable declaration

    //Inject Handler
    Handler handler;
    //Random number
    Random r = new Random();
    //Timer
    int timer = 250;
    boolean warn = false;
    boolean released = false;

    public enemy_BlastHorizontal(int x, int y, ID id, Handler handler) {
        //Constructor
        super(x, y, id);
        //Inject Handler
        this.handler = handler;

    }

    //Tick method
    public void tick() {
        //When the timer is over blast the particles
        if(timer == 0){
            handler.addObject(new enemy_BlastHorizontalParticles(x, y, ID.BlasterParticles, handler));
            timer--;
            released = true;

        //Flashing the warning light for the laser
        } else if (timer % 50 == 0 && timer > 0){
            warn = true;
        } else if (timer % 10 == 0){
            warn = false;
        }
        if(timer > 0){
            timer--;
        }
        if(released){
            //Check if any children classes exist
            angelSense();
        }
    }

    public void render(Graphics g) {
        if(warn){
            //Show the red warning when needed
            g.setColor(Color.RED);
            g.fillRect(5, y, 20, 20);
        }
    }

    @Override
    public void playerDeath() {
        //Unnecessary
    }

    //Returns a rectangle with the bounds of the object
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

    //Checks for children classes (Specifically Particle children)
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
            handler.addObject(new enemy_BlastHorizontal(0, r.nextInt(Game.HEIGHT), ID.Idiot, handler));
        }
    }
}
