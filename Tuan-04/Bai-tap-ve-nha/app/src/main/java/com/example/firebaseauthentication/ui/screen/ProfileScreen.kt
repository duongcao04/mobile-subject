package com.example.firebaseauthentication.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen(firebaseUser: FirebaseUser?, signOut: Unit, navController: NavController) {
    val avatar = remember {
        mutableStateOf(
            firebaseUser?.photoUrl
                ?: "https://res.cloudinary.com/dqx1guyc0/image/upload/v1743553174/uth-icon_mmdttl.png"
        )
    }
    val nameTextState = remember { mutableStateOf(firebaseUser?.displayName ?: "") }
    val emailTextState = remember { mutableStateOf(firebaseUser?.email ?: "") }

    Scaffold(topBar = { TopBar(navController) },
        bottomBar = { BottomBar(navController) }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = firebaseUser?.photoUrl,
                contentDescription = "UTH Logo",
                modifier = Modifier
                    .padding(16.dp)
                    .width(200.dp)
                    .height(200.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(44.dp))
            Column() {
                Text("Name")
                TextField(value = nameTextState.value, // Hiển thị giá trị nhập vào
                    onValueChange = {
                        nameTextState.value = it
                    }, // Cập nhật giá trị khi người dùng thay đổi
                    placeholder = { Text("Enter your name") } // Thêm nhãn cho trường nhập
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column() {
                Text("Email")
                TextField(value = emailTextState.value, // Hiển thị giá trị nhập vào
                    onValueChange = {
                        emailTextState.value = it
                    }, // Cập nhật giá trị khi người dùng thay đổi
                    placeholder = { Text("Enter your email") } // Thêm nhãn cho trường nhập
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column() {
                Text("Date of Birth")
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { signOut }) {
                Text(text = "Sign out")
            }
        }
    }
}

@Composable
fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        IconButton(
            modifier = Modifier.padding(16.dp),
            onClick = { navController.navigate("authScreen") }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = "Detail",
            style = TextStyle(
                fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xff0eb6ff)
            ),
        )
    }
}

@Composable
fun BottomBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("authScreen") }) {
            Text(text = "Back")
        }
    }
}

