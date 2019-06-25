package com.Cubism.main;

import java.awt.*;
import java.util.Random;

public class enemy_Bar extends GameObject{


    Handler handler;
    Random r = new Random();
    int Rand = r.nextInt(Game.WIDTH - 250);
    int Rand2 = r.nextInt(50);

    public enemy_Bar(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velY = 3;
        this.handler = handler;
    }

    public void tick() {
        y += velY;

        if(getY() > Game.HEIGHT){
            Game.gameStage ++;
            handler.addObject(new enemy_Bar(0, -16, ID.Bar, handler));
            handler.removeObject(this);
        }

    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, y, Rand, 16);
        g.fillRect(Rand + Player.pDim + 32 + Rand2, y, Game.WIDTH - Rand - Player.pDim, 16);
    }

    @Override
    public void playerDeath() {

    }

    public Rectangle getBounds() {
        return new Rectangle(0, y, Rand, 16);
    }
    public Rectangle getBoundsR(){
        return new Rectangle(Rand + Player.pDim + 32 + Rand2, y, Game.WIDTH - Rand - Player.pDim, 16);
    }

}
