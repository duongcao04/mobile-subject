package com.example.t3_b1.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun T3_B1Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography(),
        content = content
    )
}
