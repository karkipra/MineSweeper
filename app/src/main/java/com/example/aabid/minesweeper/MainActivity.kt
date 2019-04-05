package com.example.aabid.minesweeper

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import android.widget.ToggleButton
import com.example.aabid.minesweeper.model.MinesweeperModel
import com.example.aabid.minesweeper.ui.MinesweeperView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.CompoundButton
import android.widget.SeekBar
import kotlin.math.min


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCont.setOnClickListener {
            when {
                rows.text.toString().toInt() < 2 -> rows.error = "You must have at least 3 rows"
                rows.text.toString().toInt() > 10 -> rows.error = "You can not have more than 10 rows"
                columns.text.toString().toInt() < 2 -> columns.error = "You must have at least 3 columns"
                columns.text.toString().toInt() > 10 -> columns.error = "You can not have more than 10 columns"
                mines.text.toString().toInt() > columns.text.toString().toInt() * rows.text.toString().toInt() -> mines.error = "Too many mines. Please enter a smaller number"
                else -> {
                    val intentGame = Intent()
                    intentGame.setClass(MainActivity@this,
                            GameActivity::class.java)
                    intentGame.putExtra("nRows", rows.text.toString())
                    intentGame.putExtra("nCols", columns.text.toString())
                    intentGame.putExtra("nMines", mines.text.toString())
                    startActivity(intentGame)
                }
            }
        }

    }

}
