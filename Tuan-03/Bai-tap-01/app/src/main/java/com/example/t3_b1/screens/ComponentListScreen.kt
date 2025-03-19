package com.example.t3_b1.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*  // Import các thành phần bố cục
import androidx.compose.foundation.lazy.LazyColumn  // Import danh sách cuộn dọc
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*  // Import Material 3
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color  // Import để sử dụng màu sắc
import androidx.compose.ui.text.font.FontWeight  // Import để thiết lập độ đậm của chữ
import androidx.compose.ui.unit.dp  // Đơn vị đo kích thước (margin, padding)
import androidx.compose.ui.unit.sp  // Đơn vị đo kích thước chữ
import androidx.navigation.NavController  // Dùng để điều hướng giữa các màn hình

@OptIn(ExperimentalMaterial3Api::class)  // Kích hoạt API thử nghiệm của Material 3
@Composable
fun ComponentListScreen(navController: NavController) {
    // Danh sách các thành phần UI được phân thành từng danh mục
    val categories = mapOf(
        "Display" to listOf("Text", "Image"),  // Nhóm hiển thị
        "Input" to listOf("TextField", "PasswordField"),  // Nhóm nhập liệu
        "Layout" to listOf("Column", "Row")  // Nhóm bố cục
    )

    Scaffold(
        topBar = {
            // Thanh tiêu đề trên cùng của màn hình
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "UI Components List",  // Tiêu đề
                        fontSize = 20.sp,  // Kích thước chữ
                        fontWeight = FontWeight.Bold,  // Chữ đậm
                        color = Color(0xFF1976D2),  // Đổi màu tiêu đề thành xanh
                        modifier = Modifier.padding(top = 8.dp)  // Di chuyển xuống một chút
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()  // Màu mặc định của AppBar
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()  // Chiếm toàn bộ màn hình
                .padding(innerPadding)  // Khoảng cách với AppBar
                .padding(16.dp)  // Khoảng cách lề ngoài cùng
        ) {
            categories.forEach { (category, components) ->
                item {
                    // Tiêu đề của từng nhóm (Display, Input, Layout)
                    Text(
                        text = category,
                        fontSize = 18.sp,  // Kích thước chữ lớn hơn
                        fontWeight = FontWeight.Bold,  // Chữ đậm
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)  // Khoảng cách trên & dưới
                    )
                }
                // Danh sách các thành phần trong từng nhóm
                items(components) { component ->
                    ComponentItem(component) {
                        navController.navigate("detail/$component")  // Khi nhấn vào sẽ điều hướng sang trang chi tiết
                    }
                }
            }

        }
    }
}

// Composable riêng để tạo từng mục thành phần UI
@Composable
fun ComponentItem(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()  // Chiếm toàn bộ chiều rộng
            .padding(vertical = 4.dp)  // Khoảng cách giữa các Card
            .clickable { onClick() },  // Cho phép nhấn vào Card
        colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))  // Màu nền của Card (xanh nhạt)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = name,  // Tên thành phần UI
                fontSize = 16.sp,  // Kích thước chữ lớn
                fontWeight = FontWeight.Bold  // Chữ đậm
            )
            Text(
                text = "Description of $name",  // Mô tả ngắn của thành phần UI
                fontSize = 14.sp,  // Kích thước chữ nhỏ hơn
                color = Color.Gray  // Màu chữ xám
            )
        }
    }
}