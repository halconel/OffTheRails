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
    private val planetPoint: Point = Point(Constants.screenWight / 2, Constants.screenHeight)
    // Прямоугольник вывода текста
    private val textRect : Rect = Rect(
            250,
            Constants.screenHeight - 170,
            Constants.screenWight - 250,
            Constants.screenHeight - 100
    )
    // Камера
    private val cameraPoint: Point = Point(0, Constants.screenWight / 2)
    private var cameraSpeed: Int = 3;
    private val textRectColor = Color.parseColor("#A098B6")
    // Массив объектов сцены
    override var objects: ArrayList<GameObject>? = ArrayList()

    init {
        // Картинка со звездами для задника
        starsImg = BitmapFactory.decodeResource(Constants.curenContext!!.resources, R.drawable.stars)

        // Луна и ее текстура
        val moonImg = BitmapFactory.decodeResource(Constants.curenContext!!.resources, R.drawable.moon)
        moon = Moon(moonPoint,100f, moonImg)

        // планета и ее текстура
        val planetImg = BitmapFactory.decodeResource(Constants.curenContext!!.resources, R.drawable.planet)
        planet = Moon(planetPoint,Constants.screenWight / 2 - 50f, planetImg)

        // Добавим объекты в менеджер сцены для отрисовки
        objects?.add(moon)
        objects?.add(planet)

        // Расчитаем радиус орбиты луны
        orbitRadius = Math.abs(Math.sqrt(
                ((moonPoint.x - orbitCenter.x)*(moonPoint.x - orbitCenter.x) +
                        (moonPoint.y - orbitCenter.y)*(moonPoint.y - orbitCenter.y)).toDouble())).toInt()

    }

    override fun update(){
        super.update()
        // Сдвиним камеру, пока планета не окажется в центре экрана
        if(cameraPoint.y > -1 * (Constants.screenHeight / 2)) {
            cameraPoint.y -= cameraSpeed
        }
        //Движение по окружности задается формулой
        //x = x0 + R * cos(angle),
        //y = y0 + R * sin(angle),
        //
        //angle = orbitSpeet * t
        //t - время (помним, что мы задали ограничение 30 кадров в секунду)
        //Расчитаем новое положение луны при движении по орбите
        val oldAngle = angle;
        angle += orbitSpeed / 30
        if((Math.cos(angle) > 0 && Math.cos(oldAngle) <= 0)
            || (Math.cos(angle) <= 0 && Math.cos(oldAngle) > 0)) { // Луна прошла апоцентр или перицентр орбиты
            Collections.swap(objects, 0 , 1);
        }
        if (angle > 2 * Math.PI) angle = 0.0
        moonPoint.x = (orbitCenter.x + Math.cos(angle) * orbitRadius * Math.cos(orbitInclination)).toInt()
        moonPoint.y = (orbitCenter.y + Math.sin(angle) * orbitRadius).toInt()
        // Планету оставляем на месте
        planetPoint.x = Constants.screenWight / 2
        planetPoint.y = Constants.screenHeight
        // Обновим игровые объекты
        val moonScenePoint = Point(moonPoint.x + cameraPoint.x, moonPoint.y + cameraPoint.y)
        val moonSceneScale = (Math.cos(angle) * Math.sin(orbitInclination) * 60).toInt()
        moon.update(moonScenePoint, moonSceneScale)
        val planetScenePoint = Point(planetPoint.x + cameraPoint.x, planetPoint.y + cameraPoint.y)
        planet.update(planetScenePoint, 0)
    }

    override fun draw(canvas: Canvas) {
        val starsRect = Rect(0 - cameraPoint.x
                , Constants.screenHeight / 2 - cameraPoint.y
                , Constants.screenWight - cameraPoint.x
                , Constants.screenHeight + Constants.screenHeight / 2 - cameraPoint.y)
        canvas.drawBitmap(starsImg
                , starsRect
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