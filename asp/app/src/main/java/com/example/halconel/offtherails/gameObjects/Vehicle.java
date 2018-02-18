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


/**
 * Created by d.vinogradov on 12.02.2018.
 * Класс игрового объекта планетохода
 */
public class Vehicle implements GameObject {

    // Координаты
    private Rect rectangle;
    private float scale; //Маштаб отображения 0..1

    // Текстура
    private int color;
    private Animation texture;

    public Vehicle(Rect rectangle, Bitmap img) {
        this.rectangle = rectangle;

        // Только для отладки
        Bitmap newBitmap = Bitmap.createScaledBitmap(img, 1, 1, true);
        this.color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
    }

    @Override
    public void update(Point center, float scale) {
        // Обновим маштаб
        this.scale = scale;
        // Изменим положение игрового объекта
        this.rectangle.set(center.x - rectangle.width()/2
                , center.y - rectangle.height()/2
                , center.x + rectangle.width()/2
                , center.y + rectangle.height()/2);
    }

    @Override
    public void draw(Canvas canvas) {
        if(BuildConfig.BUILD_TYPE == "debug_no_textures") draw_debug(canvas);
        else {
            // Применим маштаб
            Rect output = applyScale();
            // Выведем текстуру
            this.texture.draw(canvas, output);
        }
    }

    private void draw_debug(Canvas canvas) {
        // Применим маштаб
        Rect output = applyScale();
        // Выведем текстуру
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(output, paint);
    }

    @NonNull
    private Rect applyScale() {
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
