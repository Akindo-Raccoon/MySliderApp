package com.ud.mysliderapp.ui.screen

import android.R
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ud.mysliderapp.ui.theme.*

@Composable
fun HomeScreen(viewModel: GameViewModel = viewModel()){

    val board = viewModel.board.value
    val moves = viewModel.moves.intValue
    val time = viewModel.watch.longValue
    val selected = viewModel.selectIndex.value
    val solved = viewModel.isSolved.value
    val gameStarted = viewModel.gameStarted.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(!gameStarted){
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                        containerColor = CardBackground
                        )
            ) {
                Column(
                    modifier = Modifier.padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Slide Puzzle",
                        style = MaterialTheme.typography.headlineLarge,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Ordena los números del 1 al 8\n" +
                                "moviendo las piezas que esten continuas\n" +
                                "hasta completar el puzzle. " +
                                "\nTen en cuenta que NO se puede mover en Diagonal",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Cómo jugar:\n" +
                                "1. Selecciona una ficha\n" +
                                "2. Selecciona dos ficha continua \n" +
                                "(arriba, abajo, izquierda o derecha)\n" +
                                "3. Intercambia posiciones",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = { viewModel.newGame() }
                    ) {
                        Text(
                            "Iniciar juego",
                            color = PrimaryButton
                        )
                    }
                }
            }
        }
        else{
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CardBackground
                )
            ) {

                Column(
                    modifier = Modifier.padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "Slide Puzzle",
                        style = MaterialTheme.typography.headlineMedium,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        "\tSelecciona dos fichas continua para moverlas",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Moves: $moves",
                        color = TextSecondary
                    )
                    Text(
                        "Time: $time s",
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        onClick = { viewModel.newGame() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SecondaryButton
                        )
                    ) {
                        Text("Restart",)
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    if(solved){
                        Text(
                            "🎉 Puzzle Completed!",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    // GRID_BOARD
                    for (row in 0..2) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            for (col in 0..2) {
                                val index = row * 3 + col
                                val value = board[index]
                                val isSelected = selected == index

                                Button(
                                    modifier = Modifier.size(85.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor =
                                            if(isSelected)
                                                SelectedTile
                                            else
                                                TileColor
                                    ),
                                    onClick = {
                                        viewModel.selectCell(index)
                                    }
                                ) {
                                    if(value != null && value != 0){
                                        Text(
                                            text = value.toString(),
                                            style = MaterialTheme.typography.headlineSmall,
                                            color = TileTextColor
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
}