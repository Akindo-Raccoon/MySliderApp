package com.ud.mysliderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ud.mysliderapp.ui.screen.GameViewModel
import com.ud.mysliderapp.ui.screen.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ud.mysliderapp.ui.theme.MySliderAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val puzzleViewModel: GameViewModel = viewModel()
            HomeScreen(puzzleViewModel)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}