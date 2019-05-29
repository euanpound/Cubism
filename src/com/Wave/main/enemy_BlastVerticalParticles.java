package com.Wave.main;

import java.awt.*;

public class enemy_BlastVerticalParticles extends enemy_BlastVertical {

    boolean hasFired = false;

    enemy_BlastVertical parent;

    int j = 255;

    boolean oneTime = true;

    public enemy_BlastVerticalParticles(int x, int y, ID id, Handler handler, enemy_BlastVertical parent) {
        super(x, y, id, handler);
        this.parent = parent;
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
            g.fillRect(r.nextInt(25) + x, r.nextInt(Game.HEIGHT), 5, 5);
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
