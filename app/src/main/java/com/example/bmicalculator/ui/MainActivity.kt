package com.example.bmicalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bmicalculator.ui.navigation.BmiRootNavHost
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMICalculatorTheme {
                BmiRootNavHost()
            }
        }
    }
}
