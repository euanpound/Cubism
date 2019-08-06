package com.Cubism.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    //Variable declaration
    public static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    //Gamestage to manage what level the game is at
    public static int gameStage = 0;
    //Limited FPS
    public static double amountOfTicks = 60.0;

    //public static final int WIDTH = (int) SCREEN_SIZE.getWidth(), HEIGHT = (int) SCREEN_SIZE.getHeight();
    public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;
    //Game thread
    private Thread thread;
    //Running boolean to stop and start the game
    private boolean running;
    private Random r = new Random();
    private int deathTimer = -1;

    //Injections
    private Handler handler;
    private HUD hud;
    private Stage_Manager stage_manager;

    int Score = 0;

    Window window;

    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        window = new Window(WIDTH, HEIGHT, "WAVE!", this);

        //Player
        handler.addObject(new Player(Game.WIDTH / 2 - 16, Game.HEIGHT / 2 - 16, ID.Player, handler));

        hud = new HUD(handler);
        stage_manager = new Stage_Manager(handler);
        System.out.println("test");

        //Stage one of the game-play
        stage_manager.stageOne();
        start();
    }

    public synchronized void start(){
        //New thread
        thread = new Thread(this);
        //start the thread
        thread.start();
        //set the game to run
        running = true;
    }
    public synchronized void stop(){
        try{
            //stop the thread
            thread.join();
            //stop the game loop
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Game loop
    public void run(){
        //Print fps limit
        System.out.println(amountOfTicks);
        //get focus on game
        this.requestFocus();
        long lastTime = System.nanoTime();
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        //while game is running
        while(running){
            //Maths to limit ticks to the chosen amount
            double ns = 1000000000 / amountOfTicks;
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                //render constantly without limit
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                //Calculate FPS
                timer += 1000;
                System.out.println("FPS:" + frames);
                frames = 0;
            }
            //Kill Player
            if(HUD.HEALTH <= 0){
                if(deathTimer == -1){
                    deathTimer = 50;
                }
                for(int i = 0; i < handler.object.size(); i++){
                    GameObject tempObject = handler.object.get(i);
                    if(tempObject.getID() == ID.Player){
                        tempObject.playerDeath();
                    }
                }
                if (deathTimer == 0) {
                    //Play again?
                    int input = JOptionPane.showOptionDialog
                            (null, "Play again?", "You lost", JOptionPane.OK_CANCEL_OPTION,
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
        stop();
    }

    private void tick(){
        handler.tick();
        try {
            stage_manager.tick();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (deathTimer >= 1 && deathTimer <= 50) {
            deathTimer--;
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(255 , 255, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max){
        if(var >= max) return var = max;
        else if(var <= min) return var = min;
        else return var;
    }

    public static void main(String[] args) {
        new Game();
    }

}
