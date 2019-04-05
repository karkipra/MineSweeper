package com.example.aabid.minesweeper.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.aabid.minesweeper.GameActivity
import com.example.aabid.minesweeper.MainActivity
import com.example.aabid.minesweeper.R
import com.example.aabid.minesweeper.model.MinesweeperModel
import kotlinx.android.synthetic.main.activity_game.*

class MinesweeperView (context: Context?, attrs: AttributeSet?) : View(context, attrs)
{
    private val paintLine = Paint()
    private val paintWhiteLine = Paint()


    public var WIDTH = MinesweeperModel.WIDTH
    public var HEIGHT = MinesweeperModel.HEIGHT
    public var NumberOfBombs = MinesweeperModel.NumberOfBombs

    var endGame = true

    private var bmZero = BitmapFactory.decodeResource(resources, R.drawable.zero)
    private var bmOne = BitmapFactory.decodeResource(resources, R.drawable.one)
    private var bmTwo = BitmapFactory.decodeResource(resources, R.drawable.two)
    private var bmThree = BitmapFactory.decodeResource(resources, R.drawable.three)
    private var bmFour = BitmapFactory.decodeResource(resources, R.drawable.four)
    private var bmFive = BitmapFactory.decodeResource(resources, R.drawable.five)
    private var bmSix = BitmapFactory.decodeResource(resources, R.drawable.six)
    private var bmSeven = BitmapFactory.decodeResource(resources, R.drawable.seven)
    private var bmEight = BitmapFactory.decodeResource(resources, R.drawable.eight)
    private var bmFlag = BitmapFactory.decodeResource(resources, R.drawable.flag)
    private var bmEmpty = BitmapFactory.decodeResource(resources, R.drawable.empty)
    private var bmMine = BitmapFactory.decodeResource(resources, R.drawable.mine)
    private var bmHappy = BitmapFactory.decodeResource(resources, R.drawable.happy)
    private var bmSad = BitmapFactory.decodeResource(resources, R.drawable.sad)


    init {

        Log.i("Init Width: ", WIDTH.toString())
        Log.i("Init HEIGHT: ", HEIGHT.toString())
        Log.i("Init NumberOfBombs: ", NumberOfBombs.toString())

        paintLine.color = Color.BLACK
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5F

        paintWhiteLine.color = Color.WHITE
        paintWhiteLine.style = Paint.Style.STROKE
        paintWhiteLine.strokeWidth = 5F


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        bmZero = Bitmap.createScaledBitmap(bmZero, width/WIDTH, height/HEIGHT, false)
        bmOne = Bitmap.createScaledBitmap(bmOne, width/WIDTH, height/HEIGHT, false)
        bmTwo = Bitmap.createScaledBitmap(bmTwo, width/WIDTH, height/HEIGHT, false)
        bmThree = Bitmap.createScaledBitmap(bmThree, width/WIDTH, height/HEIGHT, false)
        bmFour = Bitmap.createScaledBitmap(bmFour, width/WIDTH, height/HEIGHT, false)
        bmFive = Bitmap.createScaledBitmap(bmFive, width/WIDTH, height/HEIGHT, false)
        bmSix = Bitmap.createScaledBitmap(bmSix, width/WIDTH, height/HEIGHT, false)
        bmSeven = Bitmap.createScaledBitmap(bmSeven, width/WIDTH, height/HEIGHT, false)
        bmEight = Bitmap.createScaledBitmap(bmEight, width/WIDTH, height/HEIGHT, false)
        bmFlag = Bitmap.createScaledBitmap(bmFlag, width/WIDTH, height/HEIGHT, false)
        bmEmpty = Bitmap.createScaledBitmap(bmEmpty, width/WIDTH, height/HEIGHT, false)
        bmMine = Bitmap.createScaledBitmap(bmMine, width/WIDTH, height/HEIGHT, false)
        bmSad = Bitmap.createScaledBitmap(bmSad, 70, 70, false)
        bmHappy = Bitmap.createScaledBitmap(bmHappy, 70, 70, false)


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRect(0f, 0f,
                width.toFloat(), height.toFloat(), paintLine)

        drawGameArea(canvas)
        drawPlayers(canvas)
        showBombs(canvas)

    }

    fun drawGameArea(canvas: Canvas) {

        Log.i("drawGameArea Width: ", WIDTH.toString())
        Log.i("drawGameArea HEIGHT: ", HEIGHT.toString())
        Log.i("drawGameArea NumberOfBombs: ", NumberOfBombs.toString())

        MinesweeperModel.HEIGHT = HEIGHT
        MinesweeperModel.WIDTH = WIDTH
        MinesweeperModel.NumberOfBombs = NumberOfBombs

        Log.i("MinesweeperModel.WIDTH Width: ", MinesweeperModel.WIDTH.toString())
        Log.i("MinesweeperModel.HEIGHT HEIGHT: ", MinesweeperModel.HEIGHT.toString())
        Log.i("MinesweeperModel.NumberOfBombs NumberOfBombs: ", MinesweeperModel.NumberOfBombs.toString())

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)

        for (i in 0..WIDTH) {
            canvas.drawLine((width.toFloat()/WIDTH.toFloat())*i, 0f, width.toFloat()/WIDTH.toFloat()*i, height.toFloat(), paintWhiteLine)
        }
        for (i in 0..HEIGHT) {
            canvas.drawLine(0f,(height.toFloat()/HEIGHT.toFloat())*i, width.toFloat(), height.toFloat()/HEIGHT.toFloat()*i, paintWhiteLine)
        }

    }

    private fun drawPlayers(canvas: Canvas) {
        for (i in 0..WIDTH) {
            for (j in 0..HEIGHT) {
                var x = (width.toFloat()/WIDTH.toFloat())*(i)
                var y = (height.toFloat()/HEIGHT.toFloat())*(j)
                when {
                    MinesweeperModel.checkTapped(i,j) -> when (MinesweeperModel.getFieldContent(i, j).numberOfBombsAround) {
                        0 -> canvas.drawBitmap(bmZero, x, y, null)
                        1 -> canvas.drawBitmap(bmOne, x, y, null)
                        2 -> canvas.drawBitmap(bmTwo, x, y, null)
                        3 -> canvas.drawBitmap(bmThree, x, y, null)
                        4 -> canvas.drawBitmap(bmFour, x, y, null)
                        5 -> canvas.drawBitmap(bmFive, x, y, null)
                        6 -> canvas.drawBitmap(bmSix, x, y, null)
                        7 -> canvas.drawBitmap(bmSeven, x, y, null)
                        8 -> canvas.drawBitmap(bmEight, x, y, null)
                    }
                    MinesweeperModel.checkFlag(i,j) -> canvas.drawBitmap(bmFlag, x, y, null)
                    else -> canvas.drawBitmap(bmEmpty, x, y, null)
                }
            }
        }
    }

    fun showBombs(canvas: Canvas) {
        if (!endGame) {
            (context as GameActivity).imgBtn.setImageBitmap(bmSad)
            for (i in 0..WIDTH) {
                for (j in 0..HEIGHT) {
                    if (MinesweeperModel.CheckBomb(i, j)) {
                        canvas.drawBitmap(bmMine, (width.toFloat()/WIDTH.toFloat())*(i), (height.toFloat()/HEIGHT.toFloat())*(j), null)
                        //canvas.drawText("B", (width.toFloat()/WIDTH.toFloat())*(i), (height.toFloat()/HEIGHT.toFloat())*(j+1), paintBomb)
                    }
                }
            }
        }
    }

    fun resetGame() {
        MinesweeperModel.resetModel()
        endGame = true
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val tX = event.x.toInt() / (width / WIDTH)
        val tY = event.y.toInt() / (height / HEIGHT)

        if (endGame) {
            if (MinesweeperModel.flagMode) {
                if (tX < WIDTH && tY < HEIGHT && !MinesweeperModel.checkTapped(tX, tY)) {
                    MinesweeperModel.setFlag(tX, tY)
                    invalidate()
                    if (MinesweeperModel.checkPlayerWin()) {
                        (context as GameActivity).showMessage(context.getString(R.string.winMessage))
                        endGame = false
                    }
                }
            } else {
                if (tX < WIDTH && tY < HEIGHT && !MinesweeperModel.checkTapped(tX, tY)) {
                    if (MinesweeperModel.CheckBomb(tX, tY)) {
                        // game over
                        endGame = false
                        (context as GameActivity).showMessage(context.getString(R.string.gameOver))
                    } else {
                        MinesweeperModel.setTapped(tX, tY)
                    }
                    invalidate()
                    if (MinesweeperModel.checkPlayerWin()) {
                        (context as GameActivity).showMessage(context.getString(R.string.winMessage))
                        endGame = false
                    }
                }
            }
        }

        invalidate()
        return super.onTouchEvent(event)
    }




}