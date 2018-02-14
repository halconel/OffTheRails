package com.example.halconel.offtherails.GameScenes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import com.example.halconel.offtherails.GameObjects.GameObject
import java.util.ArrayList

/**
 * Created by halconel on 13.02.2018.
 * Обстрактный класс описания отображаемой в игре сцены
 */
abstract class Scene(val manager: SceneManager){

    private val r = Rect()

    // Все игровые объекты сцены. Некоторые сцены не содержат игровых обектов, такие сцены статичны
    open var objects: ArrayList<GameObject>? = null

    open fun update() {}

    open fun draw(canvas: Canvas) {
        objects?.forEach {
            it.draw(canvas)
        }
    }

    open fun terminate() { manager.activeScene = 0 }

    open fun receiveTouch(event: MotionEvent) {}

    open fun drawCenterText(canvas: Canvas, paint: Paint, text: String) {
        paint.textAlign = Paint.Align.LEFT
        canvas.getClipBounds(r)
        val cHeight = r.height()
        val cWidth = r.width()
        paint.getTextBounds(text, 0, text.length, r)
        val x = cWidth / 2f - r.width() / 2f - r.left
        val y = cHeight / 2f + r.height() / 2f - r.bottom
        canvas.drawText(text, x, y, paint)
    }
}