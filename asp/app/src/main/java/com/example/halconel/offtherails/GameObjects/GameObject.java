package com.example.halconel.offtherails.GameObjects;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by d.vinogradov on 12.02.2018.
 * Интерфейс класса игровых объектов
 */
public interface GameObject {
    public void update(Point center, int scale);
    public void draw(Canvas canvas);
}
