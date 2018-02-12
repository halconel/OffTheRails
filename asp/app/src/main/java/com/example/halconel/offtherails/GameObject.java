package com.example.halconel.offtherails;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by d.vinogradov on 12.02.2018.
 * Интерфейс класса игровых объектов
 */

public interface GameObject {
    public void update(Point point);
    public void draw(Canvas canvas);
}
