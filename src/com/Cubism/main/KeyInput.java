package com.Cubism.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    private boolean uP = false;
    private boolean dP = false;
    private boolean lP = false;
    private boolean rP = false;

    boolean isSprinting = false;

    private int pSpeed = 5;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        //System.out.println(e);
        for(int i = 0; i < handler.object.size(); i ++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == (ID.Player)){
                if(key == KeyEvent.VK_W) { //Forward movement
                    uP = true;
                    tempObject.setVelY(-pSpeed);
                }
                if(key == KeyEvent.VK_S){ //Backward movement
                    dP = true;
                    tempObject.setVelY(pSpeed);
                }
                if(key == KeyEvent.VK_A){ //Left movement
                    lP = true;
                    tempObject.setVelX(-pSpeed);
                }
                if(key == KeyEvent.VK_D){ //Right movement
                    rP = true;
                    tempObject.setVelX(pSpeed);
                }
                //if(key == KeyEvent.VK_SHIFT){ //Sprinting
                //        pSpeed *= 4;
                //        isSprinting = true;
                //}
            }
        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i ++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == (ID.Player)){
                if(key == KeyEvent.VK_W){ //Forward stopping
                    uP = false;
                    if(dP){ //If the opposite button is pressed move backwards
                        tempObject.setVelY(5);
                    }
                    else{
                        tempObject.setVelY(0);
                    }
                }
                if(key == KeyEvent.VK_S){ //Backwards stopping
                    dP = false;
                    if(uP){ //If the opposite button is pressed move forwards
                        tempObject.setVelY(-5);
                    }
                    else{
                        tempObject.setVelY(0);
                    }
                }
                if(key == KeyEvent.VK_A){ //Left stopping
                    lP = false;
                    if(rP){ //If the opposite button is pressed move right
                        tempObject.setVelX(5);
                    }
                    else{
                        tempObject.setVelX(0);
                    }
                }
                if(key == KeyEvent.VK_D){ //Right stopping
                    rP = false;
                    if(lP){ //If the opposite button is pressed move left
                        tempObject.setVelX(-5);
                    }
                    else{
                        tempObject.setVelX(0);
                    }
                }
                //if(key == KeyEvent.VK_SHIFT){
                //    if (isSprinting) { //If sprinting
                //        pSpeed /= 4;
                //        isSprinting = false;
                //    }
                //}
            }
        }
    }
}
