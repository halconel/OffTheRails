package com.example.halconel.offtherails.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.halconel.offtherails.BuildConfig;
import com.example.halconel.offtherails.Constants;

/**
 * Created by divin on 18.02.2018.
 * Класс для вывода статичных задников
 */
public class Background implements GameObject {

    private Bitmap bgImg;
    private int color;
    private Rect subset;

    public Background(Bitmap img, Rect subset) {
        this.bgImg = img;
        this.subset = subset;

        // Только для отладки
        Bitmap newBitmap = Bitmap.createScaledBitmap(img, 1, 1, true);
        this.color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
    }

    @Override
    public void update(Point center, float scale) {
        subset.set(center.x
                ,center.y
                ,center.x + Constants.screenWight
                ,center.y + Constants.screenHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        if(BuildConfig.BUILD_TYPE == "debug_no_textures") draw_debug(canvas);
        else {
            canvas.drawBitmap(bgImg, subset
                    , new Rect(0, 0, Constants.screenWight, Constants.screenHeight)
                    , new Paint());
        }
    }

    private void draw_debug(Canvas canvas) {
        canvas.drawColor(color);
    }

}
