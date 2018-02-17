package com.example.halconel.offtherails.GameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by divin on 17.02.2018.
 */

public class Moon implements GameObject{
    // Координаты
    private Point center;
    private float radius;
     // Отрисовка
    private int color;
    private int strokeColor;
    private int strokeWidth;
    private float scale;

    @Override
    public void update(Point center, int scale) {
        this.scale = scale;
        this.center.set(center.x, center.y);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        // Выведем контур луны
        if(strokeWidth > 0) {
            paint.setColor(strokeColor);
            paint.setStrokeWidth(strokeWidth);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(center.x, center.y, radius, paint);
        }
        // Выведем тело луны
        paint.setColor(color);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(center.x, center.y, radius + scale, paint);
    }

    public Moon(Point center, float radius, int color) {
        this.center = center;
        this.radius = radius;
        this.color = color;

        this.strokeColor = 0;
        this.strokeWidth = 0;
        this.scale = 0;
    }

    public void addStroke(int strokeWidth, int strokeColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    public void removeStroke() {
        this.strokeColor = 0;
        this.strokeWidth = 0;
    }
}
