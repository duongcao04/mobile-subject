package com.example.t3_b1

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.t3_b1.screens.ComponentDetailScreen
import com.example.t3_b1.screens.ComponentListScreen
import com.example.t3_b1.screens.MainScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("list") { ComponentListScreen(navController) }
        composable("detail/{componentName}") { backStackEntry ->
            val componentName = backStackEntry.arguments?.getString("componentName") ?: "Unknown"
            ComponentDetailScreen(componentName)
        }
    }
}