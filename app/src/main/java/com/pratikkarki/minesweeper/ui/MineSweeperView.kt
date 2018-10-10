package com.pratikkarki.minesweeper.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.pratikkarki.minesweeper.MainActivity
import com.pratikkarki.minesweeper.R
import com.pratikkarki.minesweeper.model.MineSweeperModel

class MineSweeperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paintBackGround: Paint = Paint()
    private val paintLine: Paint = Paint()
    private val paintLine1: Paint = Paint()
    private val paintLine2: Paint = Paint()
    private val paintText: Paint = Paint()

    var canTouch = true


    private var bitMapBomb =
            BitmapFactory.decodeResource(resources, R.drawable.bomb)

    private var bitMapFlag =
            BitmapFactory.decodeResource(resources, R.drawable.flag)


    init {
        paintBackGround.color = Color.WHITE
        paintBackGround.strokeWidth = 10F
        paintBackGround.style = Paint.Style.FILL

        paintLine.color = Color.GRAY
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 10F

        paintLine1.color = Color.RED
        paintLine1.style = Paint.Style.STROKE
        paintLine1.strokeWidth = 10F

        paintLine2.color = Color.GREEN
        paintLine2.style = Paint.Style.STROKE
        paintLine2.strokeWidth = 10F

        paintText.color = Color.BLACK
        paintText.textSize = 100F

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //Rectangle with border

        canvas.drawRect(0F, 0F,
                width.toFloat(), height.toFloat(), paintBackGround)
        canvas.drawRect(0F, 0F,
                width.toFloat(), height.toFloat(), paintLine)



        drawGameArea(canvas)

        if (!MineSweeperModel.lost && !MineSweeperModel.win) {
            drawOpened(canvas)
        } else if(MineSweeperModel.lost) {
            drawLost(canvas)
            !canTouch
            (context as MainActivity).loser()
            MineSweeperModel.FLAGS = 0
        } else {
            drawOpened(canvas)
            MineSweeperModel.win = false
            canTouch = false
            (context as MainActivity).winner()
        }

    }

    fun drawGameArea(canvas: Canvas) {

        //Horizontal
        for (i in 1..5) {
            canvas.drawLine(width.toFloat() * i / 5, 0F,
                    width.toFloat() * i / 5, height.toFloat(), paintLine)
        }


        //Vertical

        for (j in 1..5) {
            canvas.drawLine(0F, height.toFloat() * j / 5,
                    width.toFloat(), height.toFloat() * j / 5, paintLine)
        }

    }

    private fun drawOpened(canvas: Canvas) {

        for (i in 0..4) {
            for (j in 0..4) {
                if (MineSweeperModel.isClicked(i, j)) {
                    if (MineSweeperModel.getFieldContent(i, j) == MineSweeperModel.BOMB) {
                        canvas.drawBitmap(bitMapBomb, i * width / 5.toFloat(), j * height / 5.toFloat(), null)
                    } else if (MineSweeperModel.getFieldContent(i, j) == MineSweeperModel.FLAG) {
                        canvas.drawBitmap(bitMapFlag, i * width / 5.toFloat(), j * height / 5.toFloat(), null)
                    } else {//if (MineSweeperModel.getFieldContent(i, j) != MineSweeperModel.EMPTY) {
                        val t = MineSweeperModel.model[i][j].toString()
                        canvas.drawText(t, i * width / 5.toFloat() + 40F, (j + 1) * height / 5.toFloat() - 40F, paintText)
                    }
                }
            }
        }

    }

    private fun drawLost(canvas: Canvas) {
        for (i in 0..4) {
            for (j in 0..4) {
                if (MineSweeperModel.getFieldContent(i, j) == MineSweeperModel.BOMB) {
                    canvas.drawBitmap(bitMapBomb, i * width / 5.toFloat(), j * height / 5.toFloat(), null)

                } else if (MineSweeperModel.isClicked(i, j)
                        && MineSweeperModel.getFieldContent(i, j) == MineSweeperModel.FLAG) {
                    canvas.drawBitmap(bitMapFlag, i * width / 5.toFloat(), j * height / 5.toFloat(), null)
                } else if (MineSweeperModel.isClicked(i, j)){
                        //&& MineSweeperModel.getFieldContent(i, j) != MineSweeperModel.EMPTY) {
                    val t = MineSweeperModel.model[i][j].toString()
                    canvas.drawText(t, i * width / 5.toFloat() + 40F, (j + 1) * height / 5.toFloat() - 40F, paintText)
                }
            }
        }
        MineSweeperModel.lost = false
        canTouch = false
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paintText.textSize = height.toFloat() / 7

        bitMapBomb = Bitmap.createScaledBitmap(bitMapBomb,
                width / 5, height / 5, false)

        bitMapFlag = Bitmap.createScaledBitmap(bitMapFlag,
                width / 5, height / 5, false)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(canTouch) {
            val tX = event.x.toInt() / (width / 5)
            val tY = event.y.toInt() / (height / 5)

            if (tX < 5 && tY < 5) {
                MineSweeperModel.getClicked(tX, tY)
                invalidate()
            }

            if(MineSweeperModel.flagger && !MineSweeperModel.lost){
                (context as MainActivity).flagsLeft()
            }

        }



        return super.onTouchEvent(event)
    }


    fun restart() {
        MineSweeperModel.setupGame()
        canTouch = true
        invalidate()
    }



}



