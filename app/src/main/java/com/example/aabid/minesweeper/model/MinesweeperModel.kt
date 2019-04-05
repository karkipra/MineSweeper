package com.example.aabid.minesweeper.model

import android.util.Log
import java.lang.reflect.WildcardType
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

object MinesweeperModel {

    class Cell {
        var isBomb: Boolean = false
        var numberOfBombsAround: Int = 0
        var isFlag: Boolean = false
        var isTapped: Boolean = false
    }

    var WIDTH = 11
    var HEIGHT = 11
    var NumberOfBombs = 3
    var numberOfOpenCells = (WIDTH * HEIGHT) - NumberOfBombs
    private var bombSet = mutableSetOf<Pair<Int, Int>>()
    var flagMode = false
    var firstTapped = true

    private var model = ArrayList<ArrayList<Cell>>()

    init {
        Log.i("WIDTH", WIDTH.toString())
        Log.i("HEIGHT", HEIGHT.toString())
        Log.i("NumberOfBombs", NumberOfBombs.toString())
        for (i in 0..WIDTH) {

            var newRow = ArrayList<Cell>()

            for (j in 0..HEIGHT) {
                newRow.add(Cell())
            }

            model.add(newRow)

        }

        NumberOfBombs = min(WIDTH * HEIGHT - 1, NumberOfBombs)
        numberOfOpenCells = (WIDTH * HEIGHT) - NumberOfBombs
        generateBombs()
        addBombs()
    }

    fun generateBombs() {

        bombSet.clear()

        while (bombSet.size < NumberOfBombs) {
            var randGen = Random()
            var x = randGen.nextInt(WIDTH)
            var y = randGen.nextInt(HEIGHT)
            bombSet.add(Pair(x,y))
        }
    }

    fun addBombs() {
        for (bomb in bombSet) {
            makeBomb(bomb.first, bomb.second)

            for (i in -1..1) {
                for (j in -1..1) {
                    var cX = bomb.first + i
                    var cY = bomb.second + j
                    if (cX >= 0 && cY >= 0 && cX <= WIDTH && cY <= HEIGHT) {
                        model[cX][cY].numberOfBombsAround += 1
                    }
                }
            }
        }
    }

    fun makeBomb(x: Int, y: Int) {
        model[x][y].isBomb = true
    }

    fun CheckBomb(x: Int, y: Int) : Boolean {
        return model[x][y].isBomb
    }

    fun setFlag(x: Int, y: Int) {
        model[x][y].isFlag = true
    }

    fun checkFlag(x: Int, y: Int) : Boolean {
        return model[x][y].isFlag
    }

    fun setTapped(x: Int, y: Int) {
        if (firstTapped) {
            model[x][y].isTapped = true
            numberOfOpenCells--
            Log.i("firstTapped setTapped Width: ", WIDTH.toString())
            Log.i("firstTapped setTapped HEIGHT: ", HEIGHT.toString())
            Log.i("firstTapped setTapped NumberOfBombs: ", NumberOfBombs.toString())
            resetModel()
            firstTapped = false
            model[x][y].isTapped = true
            numberOfOpenCells--
        } else {
            model[x][y].isTapped = true
            numberOfOpenCells--
            Log.i("setTapped Width: ", WIDTH.toString())
            Log.i("setTapped HEIGHT: ", HEIGHT.toString())
            Log.i("setTapped NumberOfBombs: ", NumberOfBombs.toString())
        }

    }

    fun checkTapped(x: Int, y: Int) : Boolean {
        return model[x][y].isTapped
    }

    fun checkPlayerWin(): Boolean {
        return numberOfOpenCells == 0
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun resetModel() {
        Log.i("RESET WIDTH", WIDTH.toString())
        Log.i("RESET HEIGHT", HEIGHT.toString())

        var newModel = ArrayList<ArrayList<Cell>>()

        for (i in 0..WIDTH) {

            var newRow = ArrayList<Cell>()

            for (j in 0..HEIGHT) {
                newRow.add(Cell())
            }
            newModel.add(newRow)

        }

        model = newModel
        /*for (i in 0..WIDTH) {
            for (j in 0..HEIGHT) {
                model[i][j] = Cell()
            }
        } */
        numberOfOpenCells = (WIDTH * HEIGHT) - NumberOfBombs
        generateBombs()
        addBombs()
    }



}