package com.example.firebaseauthentication.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen(firebaseUser: FirebaseUser?) {
    val nameTextState = remember { mutableStateOf("") }
    val emailTextState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Button(onClick = {}) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text("Profile")
        }
        AsyncImage(
            model = "https://res.cloudinary.com/dqx1guyc0/image/upload/v1743553174/uth-icon_mmdttl.png",
            contentDescription = "UTH Logo"
        )

        Text("Name")
        TextField(
            value = nameTextState.value, // Hiển thị giá trị nhập vào
            onValueChange = {
                nameTextState.value = it
            }, // Cập nhật giá trị khi người dùng thay đổi
            label = { Text("Enter your name") } // Thêm nhãn cho trường nhập
        )
        Text("Email")
        TextField(
            value = emailTextState.value, // Hiển thị giá trị nhập vào
            onValueChange = {
                emailTextState.value = it
            }, // Cập nhật giá trị khi người dùng thay đổi
            label = { Text("Enter your email") } // Thêm nhãn cho trường nhập
        )
        Text("Date of Birth")
        Button(onClick = { }) {
            Text("Back")
        }
    }
}