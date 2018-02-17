package com.example.halconel.offtherails.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.halconel.offtherails.GameObjects.GameObject;

/**
 * Created by d.vinogradov on 12.02.2018.
 * Класс игрового объекта планетохода
 */
public class Vehicle implements GameObject {

    private Rect rectangle;
    private int color;

    @Override
    public void update(Point center, int scale) {
        rectangle.set(center.x - rectangle.width()/2
                , center.y - rectangle.height()/2
                , center.x + rectangle.width()/2
                , center.y + rectangle.height()/2);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    public Vehicle(Rect rectangle) {
        this.rectangle = rectangle;
        this.color = Color.parseColor("#03241d");
    }
}
