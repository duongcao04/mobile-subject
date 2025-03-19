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
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight

import com.example.t3_b1.R

@Composable
fun MainScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Để nội dung tiêu đề ở trung tâm
        ) {
            Spacer(modifier = Modifier.height(40.dp)) // Đẩy logo lên cao hơn

            // Hình ảnh logo lớn hơn
            Image(
                painter = painterResource(id = R.drawable.jetpack_logo), // Đảm bảo có ảnh trong res/drawable
                contentDescription = "Jetpack Compose Logo",
                modifier = Modifier.size(250.dp) // Tăng kích thước logo
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Tiêu đề
            Text(text = "Jetpack Compose", fontSize = 22.sp ,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp)) // Hoặc 4.dp nếu muốn khoảng cách nhỏ hơn
            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 16.sp
            )

            // Đẩy nút "I'm Ready" xuống gần mép dưới
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("list") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(50.dp) // Giữ chiều cao lớn hơn
            ) {
                Text(text = "I'm Ready", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp)) // Tạo khoảng cách sát mép dưới
        }
    }
}
