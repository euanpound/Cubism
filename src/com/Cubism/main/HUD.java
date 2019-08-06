package com.Cubism.main;

import java.awt.*;

public class HUD {

    public static int HEALTH = 10;

    Handler handler;

    GameObject p;

    public HUD(Handler handler) {
        this.handler = handler;
        p = getP();
    }

    public GameObject getP(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            //Collide with enemy
            if(tempObject.getID() == (ID.Player)){
                return tempObject;
            }
        }return null;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if (p.getBounds().intersects(getBounds())) {
            g.setColor(new Color(169,169,169, 50));
            g.fillRect(15, 15, 200, 32);
            g.setColor(new Color(0,255,0, 50));
            g.fillRect(16, 16, HEALTH * 20 - 2, 30);
        }else{
            g.setColor(Color.darkGray);
            g.fillRect(15, 15, 200, 32);
            g.setColor(Color.GREEN);
            g.fillRect(16, 16, HEALTH * 20 - 2, 30);
        }
    }
    //Return rectangle with the dimensions of the HUD
    public static Rectangle getBounds(){
        return new Rectangle(15, 15, 200, 32);
    }

}
