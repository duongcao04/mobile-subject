package com.example.firebaseauthentication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun AuthScreen(onLoginClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://res.cloudinary.com/dqx1guyc0/image/upload/v1743553174/uth-icon_mmdttl.png",
            contentDescription = "UTH Logo"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "SmartTasks",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            color = Color(0xff299bf5)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text("A simple and efficient to-do app", color = Color(0xff299bf5))

        Spacer(modifier = Modifier.height(64.dp))
        Text("Welcome", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold))

        Spacer(modifier = Modifier.height(2.dp))
        Text("Ready to explore? Log in to get started")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onLoginClick, modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
            Text(text = "Sign in with Google".uppercase())
        }
    }
}