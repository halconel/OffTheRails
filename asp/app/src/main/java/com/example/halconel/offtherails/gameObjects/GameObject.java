package com.example.halconel.offtherails.gameObjects;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by d.vinogradov on 12.02.2018.
 * Интерфейс класса игровых объектов
 */
public interface GameObject {
    public void update(Point center, float scale);
    public void draw(Canvas canvas);
}
