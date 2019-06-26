package com.Cubism.main;

import java.awt.*;

public class enemy_BlastHorizontalParticles extends enemy_BlastHorizontal {

    //Copy of enemy_BlastHorizontal

    boolean hasFired = false;

    enemy_BlastHorizontal parent;

    int j = 255;

    boolean oneTime = true;

    public enemy_BlastHorizontalParticles(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }


    public void tick() {
        if(j > 0){
            j -= 5;
        }
        if(j == 0){
            j--;
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(255, 0, 0, j));
        for(int i = 0; i < 500; i ++) {
            g.fillRect(r.nextInt(Game.WIDTH), r.nextInt(25) + y, 5, 5);
            hasFired = true;
        }
    }

    public Rectangle getBounds() {
        return null;
    }

    public Rectangle getBoundsR() {
        return null;
    }

}
