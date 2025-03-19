

package com.example.t3_b2.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.t3_b2.R

@Composable
fun Screen4(navController: NavController) {
    OnBoardingScreen(
        imageRes = R.drawable.image4,
        title = "Reminder Notification",
        description = "This app provides reminders so you don't forget your tasks...",
        buttonText = "Get Started",
        navController = navController,
        nextScreen = "screen1" // Hoặc trang chính của app nếu có
    )
}
