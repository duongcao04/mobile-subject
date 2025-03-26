package com.example.navigationapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navigationapp.ui.model.quotes

@Composable
fun DetailScreen(navController: NavController, quoteId: Number) {
    val selectedQuote = quotes.find { quote -> quote.id == quoteId }

    Scaffold(topBar = {
        TopAppBar(navController)
    }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column {
                if (selectedQuote != null) {
                    Text("${selectedQuote.text}")
                }
            }
            BackToRootButton({ navController.navigate("home") })
        }
    }
}

@Composable
fun BackToRootButton(onClickPush: () -> Unit) {
    Button(
        onClick = { onClickPush() },
        modifier = Modifier
            .fillMaxWidth(0.8f) // Nút rộng 80% màn hình
            .height(50.dp),
        shape = CircleShape, // Bo góc
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF7289DA), // Màu xanh nhạt
            contentColor = Color.White
        )
    ) {
        Text(
            text = "BACK TO ROOT",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TopAppBar(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        IconButton(modifier = Modifier.padding(16.dp), colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color(0xff6380c4), contentColor = Color(0xffffffff)
        ), onClick = { navController.navigate("home") }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = "Detail",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xff6380c4),
            fontWeight = FontWeight.Bold
        )
    }
}