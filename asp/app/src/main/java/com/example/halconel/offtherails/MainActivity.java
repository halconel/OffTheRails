package com.example.halconel.offtherails;

        import android.app.Activity;
//        import android.content.Context;
//        import android.graphics.Canvas;
//        import android.graphics.Color;
//        import android.graphics.Paint;
        import android.os.Bundle;
        import android.view.Window;
        import android.view.WindowManager;
//        import android.view.View;

public class MainActivity extends Activity {

//    private Paint mPaint; //Paint говорит нам как именно рисовать графические примитивы

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GamePanel(this));
    }

//    class DrawView extends View {
//
//        public DrawView(Context context) {
//            super(context);
//        }
//
////        @Override
//        protected void onDraw(Canvas canvas) {
//            // Что бы приложение выглядело не так скучно, зальем его цветом
//            canvas.drawColor(Color.parseColor("#ffbb6e"));
//
//            mPaint = new Paint();
//            mPaint.setAntiAlias(true); //включить сглаживание
//            mPaint.setColor(Color.BLACK); //цвет черный
//            mPaint.setStrokeWidth(2f); //ширина контура 2 пикселя
//            mPaint.setStyle(Paint.Style.FILL); //закрашивать примитивы черным цветом
//            mPaint.setTextSize(50f); //размер шрифта для canvas.drawText()
//
//            canvas.drawText("Это только начало", 510, 710, mPaint); //рисует повернутый текст в точке (310, 1010)
//        }
//
//    }

}
