package com.example.halconel.offtherails

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

/**
 * Created by divin on 17.02.2018.
 * Класс вывода анимации игрового объекта
 */
class Animation(private val frames: List<Bitmap>, animationTime: Float) {
    // Скорость анимации
    private var frameTime: Float = animationTime / frames.size
    private var lastFrame: Long = System.currentTimeMillis()
    // Индекс текущего кадра анимации
    private var frameIndex: Int = 0
    // Флаг воспроизведения анимации; истина - анимация отображается
    var isPlaying: Boolean = false

    fun play() {
        isPlaying = true
        frameIndex = 0
        lastFrame = System.currentTimeMillis()
    }
    fun stop() { isPlaying = false; }

    fun draw(canvas: Canvas, destination: Rect) {
        if(!isPlaying) return

        val paint = Paint()
        paint.isAntiAlias = true
        canvas.drawBitmap(frames[frameIndex], null, destination, Paint())
    }

    fun update() {
        if(!isPlaying) return

        if(System.currentTimeMillis() - lastFrame > frameTime * 1000) {
            frameIndex++
            frameIndex = if(frameIndex >= frames.size) 0 else frameIndex
            lastFrame = System.currentTimeMillis()
        }
    }
}