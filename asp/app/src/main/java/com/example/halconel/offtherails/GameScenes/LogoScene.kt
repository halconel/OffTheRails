package com.example.halconel.offtherails.GameScenes

import android.graphics.*
import android.view.MotionEvent
import com.example.halconel.offtherails.Constants
import com.example.halconel.offtherails.GameObjects.GameObject
import com.example.halconel.offtherails.GameObjects.Moon
import java.util.ArrayList


/**
 * Created by divin on 17.02.2018.
 */
class LogoScene(manager: SceneManager): Scene(manager) {
    // Луна
    private val moonPoint: Point = Point(
            Constants.screenWight / 2,
            Constants.screenHeight - Constants.screenWight / 2 - 140
    )
    private val moon: Moon = Moon(
            Point()
            , 100f
            , Color.parseColor("#98CCD2"))
    private val orbitCenter: Point = Point(Constants.screenWight / 2, Constants.screenHeight)
    private var orbitSpeed: Double = Math.PI / 16  // Угловая скорость движения по орбите
    private var orbitInclination: Float = 0.toFloat() // Угол наклона орбиты
    private var orbitRadius = 0
    private var angle: Double = Math.PI // В начале луна находится над планетой
    // Планета
    private val planet: Moon = Moon(
            Point(Constants.screenWight / 2, Constants.screenHeight)
            , Constants.screenWight / 2 - 50f
            , Color.parseColor("#79718F"))
    // Прямоугольник вывода текста
    private val textRect : Rect = Rect(
            250,
            Constants.screenHeight - 170,
            Constants.screenWight - 250,
            Constants.screenHeight - 100
    )
    private val textRectColor = Color.parseColor("#A098B6")
    // Массив объектов сцены
    override var objects: ArrayList<GameObject>? = ArrayList()

    init {
        planet.addStroke(15, Color.parseColor("#A098B6"))
        objects?.add(moon)
        objects?.add(planet)

        orbitRadius = Math.abs(Math.sqrt(
                ((moonPoint.x - orbitCenter.x)*(moonPoint.x - orbitCenter.x) +
                        (moonPoint.y - orbitCenter.y)*(moonPoint.y - orbitCenter.y)).toDouble())).toInt()
    }

    override fun update(){
        super.update()
        //Движение по окружности задается формулой
        //x = x0 + R * cos(angle),
        //y = y0 + R * sin(angle),
        //
        //angle = orbitSpeet * t
        //t - время (помним, что мы задали ограничение 30 кадров в секунду)
        //Расчитаем новое положение луны
        angle += orbitSpeed / 30
        if (angle > 2 * Math.PI) angle = 0.0
        moonPoint.x = (orbitCenter.x + Math.cos(angle) * orbitRadius).toInt()
        moonPoint.y = (orbitCenter.y + Math.sin(angle) * orbitRadius).toInt()
        moon.update(moonPoint)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawTextBox(canvas)
    }

    override fun receiveTouch(event: MotionEvent) {

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {}
            MotionEvent.ACTION_MOVE -> {}
            MotionEvent.ACTION_UP -> {}
        }

        super.receiveTouch(event)
    }

    private fun drawTextBox(canvas: Canvas) {
        val paintR = Paint() // Отвечает за отрисовку Прямоугольника
        val paintT = Paint() // Отвечает за отрисовку Теста

        // Прямоугольник вокруг надписи
        paintR.color = textRectColor
        paintR.style = Paint.Style.STROKE
        paintR.strokeWidth = 4f
        canvas.drawRect(textRect, paintR)
        // Надпись "Loading.."
        paintT.color = Color.parseColor("#C3BBD9")
        paintT.textSize = 50f
        canvas.drawText("Loading..."
                , (Constants.screenWight / 4).toFloat()
                , (Constants.screenHeight - 120).toFloat()
                , paintT)
        // Надпись "By Moon Labs (c)"
        paintT.color = Color.WHITE
        paintT.textSize = 30f
        canvas.drawText("By Moon Labs (c)"
                , (1320 / 3).toFloat()
                , (Constants.screenHeight - 60).toFloat(), paintT)

    }

}