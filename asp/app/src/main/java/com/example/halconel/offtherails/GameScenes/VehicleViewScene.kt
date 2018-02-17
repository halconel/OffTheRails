package com.example.halconel.offtherails.GameScenes

import android.graphics.Canvas
import android.graphics.Point
import android.graphics.Rect
import android.view.MotionEvent
import com.example.halconel.offtherails.GameObjects.GameObject
import com.example.halconel.offtherails.GameObjects.Vehicle
import java.util.ArrayList

/**
 * Created by halconel on 13.02.2018.
 * Сцена отображения движения планетохода
 */
class VehicleViewScene(manager: SceneManager): Scene(manager) {

    // Планетоход
    private val vehicle: Vehicle = Vehicle(Rect(0, 0, 400, 200))
    private val vehiclePoint: Point = Point(210, 700)
    // Массив объектов сцены
    override var objects: ArrayList<GameObject>? = ArrayList()

    init { objects?.add(vehicle) }

    override fun update(){
        super.update()
        vehicle.update(vehiclePoint, 0)
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
    }

    override fun terminate() {
        super.terminate()
    }

    override fun receiveTouch(event: MotionEvent) {

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {}
            MotionEvent.ACTION_MOVE -> {}
            MotionEvent.ACTION_UP -> {}
        }

        super.receiveTouch(event)
    }
}