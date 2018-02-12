package com.example.halconel.offtherails;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by halconel on 11.02.2018.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    // Основной цикл приложения
    private MainThread thread;
    // Планетоход
    private Vehicle vehicle;
    private Point vehiclePoint;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        vehicle = new Vehicle(new Rect(0, 0, 400, 200));
        vehiclePoint = new Point(210,700);

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
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        vehicle.draw(canvas);
    }

    public void update() {
        vehicle.update(vehiclePoint);
    }
}
