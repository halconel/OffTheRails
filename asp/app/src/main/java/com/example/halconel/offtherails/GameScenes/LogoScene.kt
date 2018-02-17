package com.example.halconel.offtherails.GameScenes

import android.graphics.*
import android.view.MotionEvent
import com.example.halconel.offtherails.Constants
import com.example.halconel.offtherails.GameObjects.GameObject
import com.example.halconel.offtherails.GameObjects.Moon
import com.example.halconel.offtherails.R
import java.util.*


/**
 * Created by divin on 17.02.2018.
 */
class LogoScene(manager: SceneManager): Scene(manager) {
    // Звезды
    val starsImg: Bitmap
    // Луна
    private val moonPoint: Point = Point(
            Constants.screenWight / 2,
            Constants.screenHeight - Constants.screenWight / 2 - 140
    )
    private val moon: Moon
    private val orbitCenter: Point = Point(Constants.screenWight / 2, Constants.screenHeight)
    private var orbitSpeed: Double = Math.PI / 16  // Угловая скорость движения по орбите
    private var orbitInclination: Double = Math.PI / 3 // Угол наклона плоскости орбиты
    private var orbitRadius = 0
    private var angle: Double = Math.PI // В начале луна находится над планетой
    // Планета
    private val planet: Moon
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
        starsImg = BitmapFactory.decodeResource(Constants.curenContext!!.resources, R.drawable.stars)
        val moonImg = BitmapFactory.decodeResource(Constants.curenContext!!.resources, R.drawable.moon)
        moon = Moon(moonPoint,100f,moonImg)
        val planetImg = BitmapFactory.decodeResource(Constants.curenContext!!.resources, R.drawable.planet)
        planet = Moon(
                Point(Constants.screenWight / 2, Constants.screenHeight)
                , Constants.screenWight / 2 - 50f
                , planetImg)
        //planet.addStroke(15, Color.parseColor("#A098B6"))
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
        val oldAngle = angle;
        angle += orbitSpeed / 30
        if((Math.cos(angle) > 0 && Math.cos(oldAngle) <= 0)
            || (Math.cos(angle) <= 0 && Math.cos(oldAngle) > 0)) { // Луна прошла апоцентр или перицентр орбиты
            Collections.swap(objects, 0 , 1);
        }
        if (angle > 2 * Math.PI) angle = 0.0
        moonPoint.x = (orbitCenter.x + Math.cos(angle) * orbitRadius * Math.cos(orbitInclination)).toInt()
        moonPoint.y = (orbitCenter.y + Math.sin(angle) * orbitRadius).toInt()
        moon.update(moonPoint, (Math.cos(angle) * Math.sin(orbitInclination) * 60).toInt())
    }

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(starsImg
                , Rect(0, 0, Constants.screenWight, Constants.screenHeight)
                , Rect(0, 0, Constants.screenWight, Constants.screenHeight)
                , Paint())
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
        val paintT = Paint() // Отвечает за отрисовку Текта

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