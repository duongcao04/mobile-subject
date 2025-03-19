package com.example.t3_b2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.t3_b2.R

@Composable
fun OnBoardingScreen(
    imageRes: Int,
    title: String,
    description: String,
    buttonText: String,
    navController: NavController,
    nextScreen: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "OnBoarding Image",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = description,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { navController.navigate(nextScreen) },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = buttonText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnBoardingScreen() {
    val navController = rememberNavController() //  Sử dụng NavController an toàn cho preview
    OnBoardingScreen(
        imageRes = R.drawable.ic_launcher_foreground, // Thay bằng hình ảnh thật
        title = "Easy Time Management",
        description = "Manage your tasks efficiently and prioritize them wisely.",
        buttonText = "Next",
        navController = navController,
        nextScreen = "screen3"
    )
}
