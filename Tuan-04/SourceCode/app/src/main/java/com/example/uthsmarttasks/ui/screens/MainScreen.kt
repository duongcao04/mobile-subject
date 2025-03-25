import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        floatingActionButtonPosition = FabPosition.Center // Đưa FAB ra giữa
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            AppNavGraph(navController)
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf("home", "calendar", "add", "notes", "settings")
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.DateRange,
        Icons.Filled.Add,
        Icons.Default.Edit,
        Icons.Default.Settings
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items.forEachIndexed { index, screen ->
            if (index === 2) {
                FloatingActionButton(
                    onClick = { navController.navigate("add_task") },
                    containerColor = Color(0xFF3B82F6), // Fix lỗi M3
                    modifier = Modifier.offset(y = (-16).dp) // Đưa FAB lên một chút
                ) {
                    Icon(icons[index], contentDescription = "Add Task", tint = Color.White)
                }
            } else {
                IconButton(onClick = {
                    if (navController.currentDestination?.route != screen) {
                        navController.navigate(screen)
                    }
                }) {
                    Icon(imageVector = icons[index], contentDescription = screen)
                }
            }
        }
    }
}
