package com.example.halconel.offtherails.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.halconel.offtherails.Animation;
import com.example.halconel.offtherails.Constants;
import com.example.halconel.offtherails.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by divin on 17.02.2018.
 */

public class Moon implements GameObject{
    // Координаты
    private Point center;
    private float radius;
     // Отрисовка
    private float scale;
    // Текстура
    private Animation texture;

    @Override
    public void update(Point center, int scale) {
        this.scale = scale;
        this.center.set(center.x, center.y);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Rect rect = new Rect(center.x - (int)(radius + scale)
                , center.y - (int)(radius + scale)
                , center.x + (int)(radius + scale)
                , center.y + (int)(radius + scale));
        texture.draw(canvas, rect);
    }

    public Moon(Point center, float radius, Bitmap img) {
        this.center = center;
        this.radius = radius;

        this.scale = 0;

        texture = new Animation(Arrays.asList(img), 9999999);
        texture.play();
    }
}
