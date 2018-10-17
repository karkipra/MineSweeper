package com.pratikkarki.minesweeper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Chronometer
import android.widget.Toast
import com.pratikkarki.minesweeper.R.id.time
import com.pratikkarki.minesweeper.model.MineSweeperModel
import com.pratikkarki.minesweeper.ui.MineSweeperView
import kotlinx.android.synthetic.main.activity_main.*

// http://18fall.web-tools.hu/pluginfile.php/1204/mod_resource/content/1/07-ait-Android.pdf Very useful resource with good links

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shimmer.startShimmer()
        MineSweeperModel.setupGame()
        reset()
        toggler()

    }

    fun reset(){
        btnReset.setOnClickListener{
            if(btnToggle.isChecked){
                btnToggle.toggle()
            }
            mineSweeperView.restart()

        }
    }

    fun toggler() {
        btnToggle.setOnCheckedChangeListener { _, isChecked ->
            MineSweeperModel.flagger = isChecked
        }
    }

    fun winner(){

        Snackbar.make(mineSweeperView, getString(R.string.winner), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.reset)){
                    if(btnToggle.isChecked){
                        btnToggle.toggle()
                    }
                    mineSweeperView.restart()
                }.show()
    }

    fun loser(){
        Snackbar.make(mineSweeperView, getString(R.string.loser), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.reset)){
                    if(btnToggle.isChecked){
                        btnToggle.toggle()
                    }
                    mineSweeperView.restart()
                }.show()
    }

    fun flagsLeft(){
        if(MineSweeperModel.BOMBS - MineSweeperModel.FLAGS  == 1) {
            Toast.makeText(this, getString(R.string.youhave) + " ${MineSweeperModel.BOMBS - MineSweeperModel.FLAGS} " + getString(R.string.unoflagleft), Toast.LENGTH_SHORT).show()
        }   else if(MineSweeperModel.FLAGS < MineSweeperModel.BOMBS) {
            Toast.makeText(this, getString(R.string.youhave) + " ${MineSweeperModel.BOMBS - MineSweeperModel.FLAGS} " + getString(R.string.flagsleft), Toast.LENGTH_SHORT).show()
        }
    }


}
