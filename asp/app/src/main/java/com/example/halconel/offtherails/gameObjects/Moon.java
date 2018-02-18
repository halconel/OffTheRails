package com.example.halconel.offtherails.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.example.halconel.offtherails.Animation;
import com.example.halconel.offtherails.BuildConfig;

import java.util.Arrays;

/**
 * Created by divin on 17.02.2018.
 * Класс вывода планет и лун
 */
public class Moon implements GameObject{
    // Координаты
    private Point center;
    private float radius;
     // Отрисовка
    private float scale; //Маштаб отображения 0..1
    private int color;
    // Текстура
    private Animation texture;

    public Moon(Point center, float radius, Bitmap img) {
        this.center = center;
        this.radius = radius;
        this.scale = 0;

        texture = new Animation(Arrays.asList(img), 9999999);
        texture.play();

        // Только для отладки
        Bitmap newBitmap = Bitmap.createScaledBitmap(img, 1, 1, true);
        this.color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
    }

    @Override
    public void update(Point center, float scale) {
        this.scale = scale;
        this.center.set(center.x, center.y);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        if(BuildConfig.BUILD_TYPE == "debug_no_textures") draw_debug(canvas);
        else {
            Rect output = applyScale(new Rect(center.x - (int) radius
                            , center.y - (int) radius
                            , center.x + (int) radius
                            , center.y + (int) radius));
            texture.draw(canvas, output);
        }
    }

    private void draw_debug(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        // Выведем тело луны
        paint.setColor(color);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(center.x, center.y, radius * scale, paint);
    }

    @NonNull
    private Rect applyScale(Rect rectangle) {
        RectF outputF = new RectF(rectangle);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale, rectangle.centerX(), rectangle.centerY());
        matrix.mapRect(outputF);

        return new Rect((int)outputF.left
                , (int) outputF.top
                , (int) outputF.right
                , (int) outputF.bottom);
    }

}
