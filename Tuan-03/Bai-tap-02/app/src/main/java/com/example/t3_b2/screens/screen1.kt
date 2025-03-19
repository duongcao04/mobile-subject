package com.example.t3_b2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.t3_b2.R

@Composable
fun Screen1(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.uth_logo), // Thay bằng logo thật
            contentDescription = "UTH Logo",
            modifier = Modifier
                .size(200.dp)
                .offset(y = (-10).dp) // Di chuyển lên 30dp (tăng giá trị nếu muốn lên cao hơn)
                .clickable { navController.navigate("screen2") }
        )

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = "UTH SmartTasks",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue, // Đổi màu thành xanh nước biển
            modifier = Modifier.clickable { navController.navigate("screen2") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen1() {
    val navController = rememberNavController() //  Dùng rememberNavController để tránh lỗi null
    Screen1(navController = navController)
}
