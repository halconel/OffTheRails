package com.example.halconel.offtherails.gameScenes

import android.graphics.Canvas
import android.view.MotionEvent

/**
 * Created by halconel on 13.02.2018.
 * Менеджер, для переключения между сценами игры
 */
class SceneManager(var activeScene: Int = 0) {
    private val scenes : ArrayList<Scene> = ArrayList()

    init {
        scenes.add(LogoScene(this))
        scenes.add(VehicleViewScene(this))
    }

    fun reciveTouch(event: MotionEvent) {
        scenes[activeScene].receiveTouch(event)
    }

    fun update() {
        scenes[activeScene].update()
    }

    fun draw(canvas: Canvas) {
        scenes[activeScene].draw(canvas)
    }
}