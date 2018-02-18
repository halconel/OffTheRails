package com.example.halconel.offtherails;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.halconel.offtherails.gameScenes.SceneManager;

/**
 * Created by halconel on 11.02.2018.
 * Расширение SurfaceView для прорисовки конвы
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    // Основной цикл приложения
    private MainThread thread;
    // Основной менеджер игровый сцен
    private SceneManager manager;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        Constants.curenContext = context;
        thread = new MainThread(getHolder(), this);
        manager = new SceneManager(0);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder,  int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.reciveTouch(event);

        return true;
        //return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        manager.draw(canvas);
    }

    public void update() {
        manager.update();
    }
}
