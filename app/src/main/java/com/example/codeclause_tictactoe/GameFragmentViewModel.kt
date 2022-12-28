package com.example.codeclause_tictactoe

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

class GameFragmentViewModel: ViewModel() {
    var playerTurn = 1
    var scoreX = MutableLiveData<Int>()
    var scoreO = MutableLiveData<Int>()
    init {
        scoreO.value = 0
        scoreX.value = 0
    }
    var totalPlays = 0
    private var btns = Array(9){0}

    fun plays(it: View){
        val btn = it as TextView
        if(!btn.text.isNullOrEmpty())return
        btn.text = if (playerTurn == 1) "X" else "O"
        when(btn.id){
            R.id.btn1 -> btns[0] = playerTurn
            R.id.btn2 -> btns[1] = playerTurn
            R.id.btn3 -> btns[2] = playerTurn
            R.id.btn4 -> btns[3] = playerTurn
            R.id.btn5 -> btns[4] = playerTurn
            R.id.btn6 -> btns[5] = playerTurn
            R.id.btn7 -> btns[6] = playerTurn
            R.id.btn8 -> btns[7] = playerTurn
            R.id.btn9 -> btns[8] = playerTurn
        }
        totalPlays++
        if(finalResult() == 0 && totalPlays == 9){
            val builder = AlertDialog.Builder(it.rootView.context)
            builder.setTitle("Result")
            builder.setMessage("Draw")
            builder.setPositiveButton("Play again"){ _, _ ->
                playAgain(btn)
            }
            builder.setNegativeButton("Back"){ _, _ ->
                it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToMainScreenFragment())
            }
            builder.show()
        }else if(finalResult() == -1){
            scoreO.value = scoreO.value?.plus(1)
            val builder = AlertDialog.Builder(it.rootView.context)
            builder.setTitle("Result")
            builder.setMessage("O wins")
            builder.setPositiveButton("Play again"){ _, _ ->
                playAgain(btn)
            }
            builder.setNegativeButton("Back"){ _, _ ->
                it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToMainScreenFragment())
            }
            builder.show()
        }else if(finalResult() == 1){
            scoreX.value = scoreX.value?.plus(1)
            val builder = AlertDialog.Builder(it.rootView.context)
            builder.setTitle("Result")
            builder.setMessage("X Wins")
            builder.setPositiveButton("Play again"){ _, _ ->
                playAgain(btn)
            }
            builder.setNegativeButton("Back"){ _, _ ->
                it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToMainScreenFragment())
            }
            builder.show()
        }
        playerTurn *= -1

    }
    private fun playAgain(tv: TextView){
        playerTurn = 1
        totalPlays = 0
        btns = Array(9){0}
        val it = tv.rootView
        it.findViewById<TextView>(R.id.btn1).text = ""
        it.findViewById<TextView>(R.id.btn2).text = ""
        it.findViewById<TextView>(R.id.btn3).text = ""
        it.findViewById<TextView>(R.id.btn4).text = ""
        it.findViewById<TextView>(R.id.btn5).text = ""
        it.findViewById<TextView>(R.id.btn6).text = ""
        it.findViewById<TextView>(R.id.btn7).text = ""
        it.findViewById<TextView>(R.id.btn8).text = ""
        it.findViewById<TextView>(R.id.btn9).text = ""
    }
    private fun finalResult():Int{
        if(btns[0] == btns[1] && btns[1] == btns[2] && btns[0]  != 0){
            return playerTurn
        }
        if(btns[3] == btns[4] && btns[4] == btns[5]&& btns[3]  != 0){
            return playerTurn
        }
        if(btns[6] == btns[7] && btns[7] == btns[8]&& btns[6]  != 0){
            return playerTurn
        }
        if(btns[0] == btns[3] && btns[3] == btns[6]&& btns[0]  != 0){
            return playerTurn
        }
        if(btns[1] == btns[4] && btns[4] == btns[7]&& btns[1]  != 0){
            return playerTurn
        }
        if(btns[2] == btns[5] && btns[5] == btns[8]&& btns[2]  != 0){
            return playerTurn
        }
        if(btns[0] == btns[4] && btns[4] == btns[8]&& btns[0]  != 0){
            return playerTurn
        }
        if(btns[2] == btns[4] && btns[4] == btns[6]&& btns[2]  != 0){
            return playerTurn
        }
        return 0
    }

}