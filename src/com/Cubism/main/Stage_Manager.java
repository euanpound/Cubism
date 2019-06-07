package com.Cubism.main;

import javax.swing.*;
import java.io.IOException;
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

    public void tick() {
        //If the player has passed ten bars go to stage two
        if (Game.gameStage == 5) {
            allGone = false;
            stageTwo();
        }
        if (Game.gameStage == 20) {
            allGone = false;
            stageThree();
        }
        if (Game.gameStage == 35) {
            allGone = false;
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (!allGone) {
                    if (tempObject.getID() != ID.Player) {
                        handler.removeObject(tempObject);
                    }
                }
                if (handler.object.size() == 1) {
                    if (Game.amountOfTicks <= 105){
                        allGone = true;
                        stageOne();
                        Game.amountOfTicks += 15;
                        Game.gameStage = 0;
                    }else if(Game.amountOfTicks > 105){
                        int input = JOptionPane.showOptionDialog
                                (null, "Play again?", "You Won", JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE, null, null, null);
                        if(input == JOptionPane.OK_OPTION)
                        {
                            try {
                                Runtime.getRuntime().exec("java -jar Cubism.jar");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.exit(0);
                        } else if(input == JOptionPane.CANCEL_OPTION){
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }
}

