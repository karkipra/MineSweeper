package com.pratikkarki.minesweeper.model

import android.util.Log
import com.pratikkarki.minesweeper.MainActivity
import com.pratikkarki.minesweeper.model.MineSweeperModel.model
import com.pratikkarki.minesweeper.ui.MineSweeperView
import java.lang.reflect.Field
import java.util.*
import kotlin.collections.RandomAccess

object MineSweeperModel {

    val BOMBS = 3
    var FLAGS = 0

    val BOMB: Short = -1
    val EMPTY: Short = 0
    val FLAG : Short = -2

    var flagger : Boolean = false

    val model = arrayOf(ShortArray(5), ShortArray(5),
            ShortArray(5), ShortArray(5), ShortArray(5))

    var clicked = mutableListOf<Pair<Int, Int>>()

    var lost = false
    var win = false


    fun getFieldContent(x: Int, y: Int) = model[x][y]


    fun setupGame() {

        restart()

        var bombNum = BOMBS

        while (bombNum > 0) {
            val x = Random(System.currentTimeMillis()).nextInt(5)
            val y = Random(System.currentTimeMillis()).nextInt(5)

            if (model[x][y] != BOMB) {
                model[x][y] = BOMB
                bombNum--
            }
        }

        calcNeighbours()

    }

    private fun calcNeighbours() {
        for(i in 0..4){
            for(j in 0..4){
                model[i][j] = getNeighbour(i, j)
            }
        }

    }

    fun getNeighbour(i : Int, j : Int) : Short{

        if(model[i][j] == BOMB){
            return BOMB
        }

        var count = 0

        if(bombInBounds(i-1, j-1)) count++
        if(bombInBounds(i-1, j)) count++
        if(bombInBounds(i-1, j+1)) count++
        if(bombInBounds(i, j-1)) count++
        if(bombInBounds(i, j+1)) count++
        if(bombInBounds(i+1, j-1)) count++
        if(bombInBounds(i+1, j)) count++
        if(bombInBounds(i+1, j+1)) count++


        return count.toShort()
    }

    fun bombInBounds(i : Int , j : Int) : Boolean{
        if(i >= 0 && j >= 0 && i < 5 && j < 5){
            if(model[i][j] == BOMB){
                return true
            }
        }
        return false
    }

    fun getClicked(x : Int, y : Int){
        if(!isClicked(x, y)){
            if(flagger && getFieldContent(x, y) == BOMB){
                model[x][y] = FLAG
                FLAGS++
                checkWin()

            } else if (flagger && getFieldContent(x, y) != BOMB || getFieldContent(x, y) == BOMB){
                lose()
            }

            clicked.add(Pair(x, y))
        }
    }

    fun isClicked(x : Int, y : Int) : Boolean{
        return clicked.contains(Pair(x,y))

    }

    fun restart(){
        for(i in 0..4){
            for(j in 0..4){
                model[i][j] = EMPTY
            }
        }
        clicked.clear()
    }

    fun lose(){
        lost = true
    }

    fun checkWin(){
        if(FLAGS == BOMBS){
            win = true
        }
    }

}

