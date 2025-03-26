package com.example.navigationapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navigationapp.ui.model.Quote
import com.example.navigationapp.ui.model.quotes

@Composable
fun ListScreen(
    navController: NavController
) {
    Scaffold(topBar = {
        TopBar(navController)
    }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(quotes) { quote ->
                    QuoteItem(quote, { navController.navigate("detail/${quote.id}") })
                }
            }
        }
    }
}

@Composable
fun QuoteItem(quote: Quote, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .background(Color(0xffb3e0ff), shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "${quote.id} | \"${quote.text}\"",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TopBar(navController: NavController) {
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