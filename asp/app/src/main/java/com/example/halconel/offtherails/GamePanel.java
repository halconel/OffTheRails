package com.example.halconel.offtherails;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by halconel on 11.02.2018.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    // Основной цикл приложения
    private MainThread thread;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
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

        /* Тут пишем что выводит на экран наше приложение */

            // Что бы приложение выглядело не так скучно, зальем его цветом
            canvas.drawColor(Color.parseColor("#ffbb6e"));

            Paint mPaint = new Paint();
            mPaint.setAntiAlias(true); //включить сглаживание
            mPaint.setColor(Color.BLACK); //цвет черный
            mPaint.setStrokeWidth(2f); //ширина контура 2 пикселя
            mPaint.setStyle(Paint.Style.FILL); //закрашивать примитивы черным цветом
            mPaint.setTextSize(50f); //размер шрифта для canvas.drawText()

            canvas.drawText("Это только начало", 510, 710, mPaint); //рисует повернутый текст в точке (310, 1010)

    }

    public void update() {

    }
}
