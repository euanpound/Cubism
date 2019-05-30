package com.Cubism.main;

import java.util.Random;

public class Stage_Manager {

    Handler handler;
    Random r = new Random();

    boolean allGone = false;

    public Stage_Manager(Handler handler){
        this.handler = handler;
    }

    public void stageOne(){
        for (int i = 0; i < 5; i++) {
            handler.addObject(new enemy_Idiot(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.Idiot, handler));
        }
        //Tracker
        handler.addObject(new enemy_Tracker(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.Tracker, handler));

        //Bar
        handler.addObject(new enemy_Bar(0, 0, ID.Bar, handler));

        //Beams
        handler.addObject(new enemy_BlastHorizontal(0, r.nextInt(Game.HEIGHT - 50) + 50, ID.Blaster, handler));
        handler.addObject(new enemy_BlastVertical(r.nextInt(Game.WIDTH - 50) + 50, 0, ID.Blaster, handler));

    }

    public void stageTwo(){
        for(int i = 0; i < handler.object.size(); i ++){
            GameObject tempObject = handler.object.get(i);
            if (!allGone) {
                if(tempObject.getID() != ID.Player){
                    handler.removeObject(tempObject);
                }
            }
            if(handler.object.size() == 1) {
                if(tempObject.getID() == ID.Player){
                    Game.gameStage++;
                    allGone = true;

                    handler.addObject(new enemy_Stage2_BlastHorizontal(0, tempObject.getY(), ID.Stage2_Blaster, handler));
                    handler.addObject(new enemy_Stage2_BlastVertical(tempObject.getX(), 0, ID.Stage2_Blaster, handler));
                }
            }
        }
    }

    public void stageThree(){
        for(int i = 0; i < handler.object.size(); i ++) {
            GameObject tempObject = handler.object.get(i);
            if(!allGone){
                if(tempObject.getID() != ID.Player) {
                    handler.removeObject(tempObject);
                }
            }
            if(handler.object.size() == 1){
                if(tempObject.getID() == ID.Player){
                    Game.gameStage++;
                    allGone = true;

                    handler.addObject(new enemy_Tracker(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Tracker, handler));
                    handler.addObject(new enemy_Stage2_BlastHorizontal(0, tempObject.getY(), ID.Stage2_Blaster, handler));
                    handler.addObject(new enemy_Stage2_BlastVertical(tempObject.getX(), 0, ID.Stage2_Blaster, handler));
                    handler.addObject(new enemy_BlastHorizontal(0, r.nextInt(Game.HEIGHT - 50) + 50, ID.Blaster, handler));
                    handler.addObject(new enemy_BlastVertical(r.nextInt(Game.WIDTH - 50) + 50, 0, ID.Blaster, handler));
                }
            }
        }
    }

    public void tick(){
        //If the player has passed ten bars go to stage two
        if(Game.gameStage == 5){
            stageTwo();
        }
        if(Game.gameStage == 20){
            allGone = false;
            stageThree();
        }
    }
}
