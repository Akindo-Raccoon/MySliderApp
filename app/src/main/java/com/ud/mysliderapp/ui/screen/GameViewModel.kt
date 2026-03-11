package com.ud.mysliderapp.ui.screen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ud.mysliderapp.ui.core.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

class GameViewModel : ViewModel(){
    val board = mutableStateOf(Constants.GAME_SOLVED.shuffled())
    val moves = mutableIntStateOf(0)
    val isSolved = mutableStateOf(false)
    val watch = mutableLongStateOf(0)
    val selectIndex = mutableStateOf<Int?>(null)
    val gameStarted = mutableStateOf(false)
    val timerRunning = mutableStateOf(false)

    init {
        gameStarted.value = false
        startWatch()
    }

    fun newGame(){
        board.value = Constants.GAME_SOLVED.shuffled()
        moves.intValue = 0
        isSolved.value = false
        watch.longValue = 0
        selectIndex.value = null

        gameStarted.value = true
        startWatch()
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

            if(isPuzzleSolved()){
                isSolved.value = true
                stopWatch()
            }
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

        val sameRow = row1 == row2 && abs(col1 - col2) == 1
        val sameCol = col1 == col2 && abs(row1 - row2) == 1

        return sameRow || sameCol
    }

    fun startWatch(){
        timerRunning.value = true
        viewModelScope.launch {
            while(timerRunning.value){
                delay(1000)
                watch.longValue++
            }
        }
    }

    fun stopWatch(){
        timerRunning.value = false
    }


}