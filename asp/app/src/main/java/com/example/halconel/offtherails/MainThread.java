package com.example.halconel.offtherails;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by halconel on 11.02.2018.
 * Класс основного потока отрисовки и обновления состояния объектов игрового мира
 */
public class MainThread extends Thread {

    public static final int MAX_FPS = 30;
    private int frameCount = 0;
    private long totalTime = 0;

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {

        while (running) {
            long startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }
            catch (Exception e) { e.printStackTrace(); } finally {
                if(canvas != null) {
                    try { surfaceHolder.unlockCanvasAndPost(canvas); }
                    catch (Exception e) { e.printStackTrace(); }
                }
            }
            limitFPS(startTime);
        }
    }

    private void limitFPS(long startTime) {
        long targetTime = 1000/MAX_FPS;
        long timeMillis = (System.nanoTime() - startTime)/1000000;
        long waitTime = targetTime - timeMillis;

        try {
            if(waitTime > 0) sleep(waitTime);
        } catch (Exception e) {e.printStackTrace();}

        totalTime += System.nanoTime() - startTime;
        frameCount++;
        if(frameCount == MAX_FPS) {
            double averageFPS = 1000 / (totalTime / frameCount / 1000000);
            frameCount = 0; totalTime = 0;
            System.out.println(averageFPS);
        }
    }
}
