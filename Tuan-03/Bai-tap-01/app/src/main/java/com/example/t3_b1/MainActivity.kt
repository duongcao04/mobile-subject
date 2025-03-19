package com.example.t3_b1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.t3_b1.ui.theme.T3_B1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            T3_B1Theme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
