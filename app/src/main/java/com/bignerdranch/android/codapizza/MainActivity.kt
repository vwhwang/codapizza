package com.bignerdranch.android.codapizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bignerdranch.android.codapizza.ui.theme.AppTheme
import com.bignerdranch.android.codapizza.ui.theme.PizzaBuilderScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                PizzaBuilderScreen()
            }
        }
    }
}