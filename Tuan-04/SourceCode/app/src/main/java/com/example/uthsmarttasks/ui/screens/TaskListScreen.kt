package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.uthsmarttasks.data.model.Task
import com.example.uthsmarttasks.ui.viewmodel.TaskViewModel
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.uthsmarttasks.DateTimeHelper
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.ui.theme.Blue400
import com.example.uthsmarttasks.ui.theme.DangerColor
import com.example.uthsmarttasks.ui.theme.SafeColor
import com.example.uthsmarttasks.ui.theme.WarningColor

@Composable
fun TaskListScreen(
    navController: NavController, viewModel: TaskViewModel = viewModel()
) {
    val tasks by viewModel.tasks.collectAsStateWithLifecycle(initialValue = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(topBar = {
        TopAppBar()
    }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                tasks.isEmpty() -> {
                    EmptyTaskScreen()
                }

                else -> {
                    LazyColumn {
                        items(tasks) { task ->
                            TaskItemCard(task,
                                onClickCard = { navController.navigate("task_detail/${task.id}") },
                                onDeleteClick = { viewModel.deleteTask(task.id) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getStatusColor(status: String): Color {
    return when (status) {
        "Low" -> SafeColor // Xanh dương
        "Medium" -> WarningColor // Vàng
        "High" -> DangerColor // Đỏ
        else -> WarningColor // Xám (Mặc định)
    }
}

@Composable
fun TaskItemCard(task: Task, onClickCard: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = onClickCard,
        colors = CardDefaults.cardColors(
            containerColor = getStatusColor(task.priority ?: "Low")
        )
    ) {
        var checked by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.Center,  // Căn giữa theo chiều ngang
                    verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                    Column {
                        Text(text = task.title, style = MaterialTheme.typography.titleMedium)
                        task.description?.let {
                            Text(
                                modifier = Modifier.padding(top = 3.dp),
                                text = it,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, start = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Status: ${task.status ?: "Unknown"}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Due to: ${task.dueDate?.let { DateTimeHelper.formatDueDate(it) } ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }
    }
}

@Composable
fun EmptyTaskScreen() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "No Tasks Yet!", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = "Logo UTH",
            modifier = Modifier.size(80.dp)
        )
        Column(modifier = Modifier.offset(x = 16.dp)) {
            Text(
                text = "SmartTasks", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Blue400
            )
            Text(
                modifier = Modifier.offset(y = 2.dp),
                text = "A simple and efficient to-do app",
                fontSize = 10.sp,
                color = Blue400
            )
        }
    }
}
