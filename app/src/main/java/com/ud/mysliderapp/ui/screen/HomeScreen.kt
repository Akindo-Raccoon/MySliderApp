package com.ud.mysliderapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(viewModel: GameViewModel){
    val board = viewModel.board.value
    val moves = viewModel.moves.intValue

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0F14))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Column(
                modifier = Modifier.padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Slide Puzzle",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF5A54FF)
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Moves: $moves",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    onClick = { viewModel.newGame() },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Restart")
                }
                Spacer(modifier = Modifier.height(25.dp))

                // GRID_BOARD
                for (row in 0..2) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        for (col in 0..2) {
                            val index = row * 3 + col
                            val value = board[index]
                            Button(
                                modifier = Modifier.size(85.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF7E78D2)
                                ),

                                onClick = {
                                    viewModel.selectCell(index)
                                }
                            ) {
                                if(value != 0){
                                    Text(
                                        text = value.toString(),
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}