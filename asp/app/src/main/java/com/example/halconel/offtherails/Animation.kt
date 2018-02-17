package com.example.halconel.offtherails

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

/**
 * Created by divin on 17.02.2018.
 */
class Animation(val frames: List<Bitmap>, animationTime: Float) {
    var frameTime: Float = animationTime / frames.size
    var frameIndex: Int = 0

    var isPlaying: Boolean = false
    var lastFrame: Long = System.currentTimeMillis()

    fun play() {
        isPlaying = true
        frameIndex = 0
        lastFrame = System.currentTimeMillis()
    }
    fun stop() { isPlaying = false; }

    fun draw(canvas: Canvas, destination: Rect) {
        if(!isPlaying) return

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