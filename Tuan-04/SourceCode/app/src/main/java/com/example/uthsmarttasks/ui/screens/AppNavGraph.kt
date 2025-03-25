import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.ui.screens.TaskDetailScreen
import com.example.uthsmarttasks.ui.screens.TaskListScreen

@Composable
fun AppNavGraph(navController: NavController) {
    NavHost(navController as NavHostController, startDestination = "home") {
        composable("home") {  TaskListScreen(navController = navController) }
        composable("calendar") { Text("📅 Calendar Screen") }
        composable("notes") { Text("📝 Notes Screen") }
        composable("settings") { Text("⚙️ Settings Screen") }
        composable("add_task") { Text("➕ Add Task Screen") }

        composable("task_detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            if (taskId != null) {
                TaskDetailScreen(navController, taskId)
            } else {
                // Điều hướng về danh sách nếu taskId không hợp lệ
                navController.popBackStack()
            }
        }
    }
}
