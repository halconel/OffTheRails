package com.example.halconel.offtherails

import android.graphics.Canvas
import android.graphics.Rect

/**
 * Created by divin on 17.02.2018.
 * Класс управления активной анимацией игрового объекта
 */
class AnimationManager(val animations: List<Animation>) {
    // Индекс отображаемой анимации
    private var animationIndex: Int = 0

    fun playAnim(index: Int) {
        animations.forEach {
            if(animations.indexOf(it) == index) {
                if (!it.isPlaying) it.play()
            }
            else
                it.stop()
        }
        animationIndex = index
    }

    fun draw(canvas: Canvas, rect: Rect) {
        if(animations[animationIndex].isPlaying)
            animations[animationIndex].draw(canvas, rect)
    }

    fun update() {
        if(animations[animationIndex].isPlaying)
            animations[animationIndex].update()
    }
}