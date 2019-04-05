package com.example.aabid.minesweeper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Toast
import com.example.aabid.minesweeper.model.MinesweeperModel
import com.example.aabid.minesweeper.ui.MinesweeperView
import kotlinx.android.synthetic.main.activity_game.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val WIDTH = intent.extras["nRows"].toString().toInt()
        val HEIGHT = intent.extras["nCols"].toString().toInt()
        val NumberOfBombs = intent.extras["nMines"].toString().toInt()

        MinesweeperModel.WIDTH = WIDTH
        MinesweeperModel.HEIGHT = HEIGHT
        MinesweeperModel.NumberOfBombs = NumberOfBombs

        minesweeperGame.WIDTH = WIDTH
        minesweeperGame.HEIGHT = HEIGHT
        minesweeperGame.NumberOfBombs = NumberOfBombs
        minesweeperGame.resetGame()
        MinesweeperModel.firstTapped = true

        minesweeperGame.invalidate()

        timer.start()

        var bmHappy = BitmapFactory.decodeResource(resources, R.drawable.happy)
        var bmSad = BitmapFactory.decodeResource(resources, R.drawable.sad)

        bmSad = Bitmap.createScaledBitmap(bmSad, 70, 70, false)
        bmHappy = Bitmap.createScaledBitmap(bmHappy, 70, 70, false)

        imgBtn.setImageBitmap(bmHappy)

        imgBtn.setOnClickListener {
            minesweeperGame.resetGame()
            timer.base = SystemClock.elapsedRealtime()
            txtMessage.text = ""
            imgBtn.setImageBitmap(bmHappy)
        }


        btnReset.setOnClickListener {
            imgBtn.setImageBitmap(bmHappy)
            minesweeperGame.resetGame()
            timer.base = SystemClock.elapsedRealtime()
            txtMessage.text = ""
        }

        switchFlag.setOnCheckedChangeListener { _, isChecked ->
            MinesweeperModel.flagMode = isChecked
        }


        nBombs.text = "Mines: $NumberOfBombs"
    }

    fun showMessage(msg: String) {

        var bmHappy = BitmapFactory.decodeResource(resources, R.drawable.happy)
        bmHappy = Bitmap.createScaledBitmap(bmHappy, 70, 70, false)

        txtMessage.text = msg

        Snackbar.make(minesweeperGame, msg, Snackbar.LENGTH_LONG)
                .setAction("Reset") {
                    minesweeperGame.resetGame()
                    timer.base = SystemClock.elapsedRealtime()
                    txtMessage.text = ""
                    imgBtn.setImageBitmap(bmHappy)
                }
                .show()

        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

    }
}
