package com.example.t3_b1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.t3_b1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDetailScreen(componentName: String) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Component Detail") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Component: $componentName", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))

            when (componentName) {
                "Text" -> Text("This is a Text component", fontSize = 20.sp)
                "Image" -> Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Sample Image"
                )
                "Button" -> Button(onClick = {}) { Text("This is a Button") }
                else -> Text("Unknown component")
            }
        }
    }
}