package com.example.egci428_singletouchview


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View

class SingleTouchView(context: Context): View(context) {
    private val paintR = Paint()
    private val paintG = Paint()
    private val paintB = Paint()
    private val pathR = Path()
    private val pathG = Path()
    private val pathB = Path()
    private var eventX: Float = 0F
    private var eventY: Float = 0F
    private var fingerDown = false
    private var clInd = 0

    init {
        paintR.isAntiAlias = true
        paintR.strokeWidth = 6F
        paintR.strokeJoin = Paint.Join.ROUND
        paintR.style = Paint.Style.STROKE
        paintG.isAntiAlias = true
        paintG.strokeWidth = 6F
        paintG.strokeJoin = Paint.Join.ROUND
        paintG.style = Paint.Style.STROKE
        paintB.isAntiAlias = true
        paintB.strokeWidth = 6F
        paintB.strokeJoin = Paint.Join.ROUND
        paintB.style = Paint.Style.STROKE
        paintR.color = Color.RED
        paintG.color = Color.BLACK
        paintB.color = Color.BLUE
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        eventX = event!!.x
        eventY = event!!.y

        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                fingerDown = true

                    pathR.moveTo(eventX,eventY)

                    pathG.moveTo(eventX,eventY)

                    pathB.moveTo(eventX,eventY)

                clInd++
                return true
            }
            MotionEvent.ACTION_MOVE ->{
                if(clInd%3 == 0){
                    pathR.lineTo(eventX,eventY)
                }else if(clInd%3 == 1){
                    pathG.lineTo(eventX,eventY)
                }else if(clInd%3 == 2){
                    pathB.lineTo(eventX,eventY)
                }
            }
            MotionEvent.ACTION_UP ->{
                fingerDown = false
            }
            else -> return false
        }
        invalidate()
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawPath(pathR,paintR)
        canvas!!.drawPath(pathG,paintG)
        canvas!!.drawPath(pathB,paintB)
        if(clInd%3 == 0){
            if (fingerDown) {
                canvas.drawCircle(eventX,eventY, 10F, paintR)
            }
        }else if(clInd%3 == 1) {
            if (fingerDown) {
                canvas.drawCircle(eventX,eventY, 10F, paintG)
            }
        }else if(clInd%3 == 2) {
            if (fingerDown) {
                canvas.drawCircle(eventX,eventY, 10F, paintB)
            }
        }

    }
}