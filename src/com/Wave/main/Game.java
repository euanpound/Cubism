package com.Wave.main;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();


    //public static final int WIDTH = (int) SCREEN_SIZE.getWidth(), HEIGHT = (int) SCREEN_SIZE.getHeight();
    public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = true;

    private Random r = new Random();
    private Handler handler;
    private HUD hud;

    int Score = 0;

    Window window;

    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        window = new Window(WIDTH, HEIGHT, "WAVE!", this);

        hud = new HUD();

        for (int i = 0; i < 5; i++) {
            handler.addObject(new enemy_Idiot(r.nextInt(WIDTH - 32), r.nextInt(HEIGHT - 32), ID.Idiot, handler));
        }
        handler.addObject(new enemy_Tracker(r.nextInt(WIDTH - 32), r.nextInt(HEIGHT - 32), ID.Tracker, handler));
        handler.addObject(new Player(WIDTH / 2 - 16, HEIGHT / 2 - 16, ID.Player, handler));
        handler.addObject(new enemy_Bar(0, 0, ID.Bar, handler));
        handler.addObject(new enemy_BlastHorizontal(0, r.nextInt(HEIGHT - 50) + 50, ID.Idiot, handler));
        handler.addObject(new enemy_BlastHorizontal(0, r.nextInt(HEIGHT - 50) + 50, ID.Idiot, handler));
        handler.addObject(new enemy_BlastVertical(r.nextInt(WIDTH - 50) + 50, 0, ID.Idiot, handler));
        handler.addObject(new enemy_BlastVertical(r.nextInt(WIDTH - 50) + 50, 0, ID.Idiot, handler));
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
        //Hello
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS:" + frames);
                frames = 0;
            }
            if(HUD.HEALTH <= 0){
                int x = JOptionPane.showOptionDialog(null, "Play again",
                        "You Died",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                stop();
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
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
