package com.ud.mysliderapp.ui

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ud.mysliderapp.ui.core.Constants

class GameViewModel : ViewModel(){
    val board = mutableStateOf(Constants.GAME_SOLVED.shuffled())
    val moves = mutableIntStateOf(0)
    val isSolved = mutableStateOf(false)
    val watch = mutableLongStateOf(0)
    val selectIndex = mutableStateOf<Int?>(null)

    fun newGame(){
        board.value = Constants.GAME_SOLVED.shuffled()
        moves.intValue = 0
        isSolved.value = false
        watch.longValue = 0
        selectIndex.value = null
    }

    fun isPuzzleSolved(): Boolean{
        return board.value == Constants.GAME_SOLVED
    }

    fun selectCell(index:Int){
        val field = selectIndex.value

        if(field == null){
            selectIndex.value = index
        }else{
            if(isAdjacent(field,index)){
                swap(field,index)
                moves.intValue++
            }
            selectIndex.value = null
        }
    }

    private fun swap(a:Int, b:Int){
        val newBoard = board.value.toMutableList()
        val aux = newBoard[a]

        newBoard[a] = newBoard[b]
        newBoard[b] = aux

        board.value = newBoard

    }

    private fun isAdjacent(i:Int,j:Int): Boolean{
        val row1 = i / Constants.BOARD_SIZE
        val col1 = i % Constants.BOARD_SIZE

        val row2 = j / Constants.BOARD_SIZE
        val col2 = j % Constants.BOARD_SIZE

        val sameRow = row1 == row2 && kotlin.math.abs(col1 - col2) == 1
        val sameCol = col1 == col2 && kotlin.math.abs(row1 - row2) == 1

        return sameRow || sameCol
    }

    fun startWatch(){

    }


}