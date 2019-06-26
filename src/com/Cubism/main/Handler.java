package com.Cubism.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    //Tick all GameObjects
    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject  tempObject= object.get(i);
            tempObject.tick();
        }

    }

    //Render all GameObjects
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}
