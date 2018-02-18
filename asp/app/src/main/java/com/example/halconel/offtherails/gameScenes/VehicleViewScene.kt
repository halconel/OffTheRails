package com.example.halconel.offtherails.gameScenes

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.Rect
import android.view.MotionEvent
import com.example.halconel.offtherails.Constants
import com.example.halconel.offtherails.gameObjects.GameObject
import com.example.halconel.offtherails.gameObjects.Vehicle
import com.example.halconel.offtherails.R
import java.util.ArrayList

/**
 * Created by halconel on 13.02.2018.
 * Сцена отображения движения планетохода
 */
class VehicleViewScene(manager: SceneManager): Scene(manager) {

    // Планетоход
    private val vehicle: Vehicle
    private val vehiclePoint: Point = Point(210, 700)
    // Массив объектов сцены
    override var objects: ArrayList<GameObject>? = ArrayList()

    init {
        // Картинка со звездами для задника
        val truckImg = BitmapFactory.decodeResource(Constants.curenContext!!.resources, R.drawable.truck)
        vehicle = Vehicle(Rect(0, 0, 400, 200), truckImg)
        // Добавим объекты в менеджер сцены для отрисовки
        objects?.add(vehicle)
    }

    override fun update(){
        super.update()
        vehicle.update(vehiclePoint, 1f)
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