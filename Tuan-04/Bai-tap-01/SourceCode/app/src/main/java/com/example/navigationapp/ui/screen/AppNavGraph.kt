import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigationapp.ui.screen.DetailScreen
import com.example.navigationapp.ui.screen.HomeScreen
import com.example.navigationapp.ui.screen.ListScreen

@Composable
fun AppNavGraph(navController: NavController) {
    NavHost(navController as NavHostController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController) }
        composable("list_screen") { ListScreen(navController = navController) }
        composable("detail/{quoteId}") { backStackEntry ->
            val quoteId = backStackEntry.arguments?.getString("quoteId")?.toIntOrNull()
            if (quoteId != null) {
                DetailScreen(navController, quoteId)
            } else {
                // Điều hướng về danh sách nếu taskId không hợp lệ
                navController.popBackStack()
            }
        }
    }
}