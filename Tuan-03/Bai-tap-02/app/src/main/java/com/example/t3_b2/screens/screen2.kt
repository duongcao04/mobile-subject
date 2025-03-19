

package com.example.t3_b2.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.t3_b2.R

@Composable
fun Screen2(navController: NavController) {
    OnBoardingScreen(
        imageRes = R.drawable.image2, // Thay bằng hình thật
        title = "Easy Time Management",
        description = "Manage your tasks efficiently and prioritize them wisely.",
        buttonText = "Next",
        navController = navController,
        nextScreen = "screen3"
    )
}
