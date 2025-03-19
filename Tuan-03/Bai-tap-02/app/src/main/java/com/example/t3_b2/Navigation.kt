

package com.example.t3_b2.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier


@Composable
fun Navigation(navController: NavHostController,
               modifier: Modifier = Modifier) {
    NavHost(navController = navController,
            startDestination = "screen1",
        modifier = modifier) {
        composable("screen1") { Screen1(navController) }
        composable("screen2") { Screen2(navController) }
        composable("screen3") { Screen3(navController) }
        composable("screen4") { Screen4(navController) }
    }
}
