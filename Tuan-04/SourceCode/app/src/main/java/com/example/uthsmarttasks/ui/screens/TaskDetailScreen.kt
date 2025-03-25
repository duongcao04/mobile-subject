package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.uthsmarttasks.data.model.Attachment
import com.example.uthsmarttasks.data.model.Subtask
import com.example.uthsmarttasks.ui.theme.Blue400
import com.example.uthsmarttasks.ui.theme.DangerColor
import com.example.uthsmarttasks.ui.theme.GreyColor
import com.example.uthsmarttasks.ui.theme.SafeColor
import com.example.uthsmarttasks.ui.viewmodel.TaskViewModel

@Composable
fun TaskDetailScreen(
    navController: NavController, taskId: Int, viewModel: TaskViewModel = viewModel()
) {
    val selectedTask by viewModel.selectedTask.collectAsStateWithLifecycle()

    LaunchedEffect(taskId) {
        viewModel.getTaskById(taskId)
    }

    Scaffold(topBar = {
        TopBar(navController)
    }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            selectedTask?.let { task ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "${task.title}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${task.description ?: "No description"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(getStatusColor(task.priority ?: "Low"))
                            .padding(16.dp), // Padding 16dp xung quanh
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Star, contentDescription = "Category")
                            Column(
                                modifier = Modifier.offset(x = 8.dp)
                            ) {
                                Text(
                                    text = "Category",
                                )
                                Text(
                                    text = "${task.category ?: "No category"}",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.DateRange, contentDescription = "Delete")
                            Column(
                                modifier = Modifier.offset(x = 8.dp)
                            ) {
                                Text(
                                    text = "Status",
                                )
                                Text(
                                    text = "${task.status ?: "Pending"}",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Favorite, contentDescription = "Delete")
                            Column(
                                modifier = Modifier.offset(x = 8.dp)
                            ) {
                                Text(
                                    text = "Priority",
                                )
                                Text(
                                    text = "${task.priority ?: "Low"}", fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Text(
                        text = "Subtasks",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            top = 10.dp
                        )
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        task.subtasks.forEach { subtask ->
                            SubtaskItem(subtask)
                        }

                    }
                    Text(
                        text = "Attachments",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            top = 10.dp
                        )
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        task.attachments.forEach { attachment ->
                            AttachmentItem(attachment)
                        }
                    }
                }
            } ?: run {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun SubtaskItem(subtask: Subtask) {
    var checked by remember { mutableStateOf(subtask.isCompleted) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(GreyColor, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 10.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically, // Căn giữa theo chiều dọc
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        Spacer(modifier = Modifier.width(8.dp)) // Tạo khoảng cách giữa Checkbox và Text
        Text(
            text = subtask.title,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}


@Composable
fun AttachmentItem(attachment: Attachment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(GreyColor, shape = RoundedCornerShape(12.dp))
            .padding(top = 10.dp, bottom = 10.dp, start = 8.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.Default.Share,
            contentDescription = "Attachment Icon",
            modifier = Modifier.size(25.dp)
        )
        Text(
            attachment.fileName,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.offset(x = 15.dp)
        )
    }
}

@Composable
fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        IconButton(modifier = Modifier.padding(16.dp), colors = IconButtonDefaults.iconButtonColors(
            containerColor = Blue400, contentColor = Color(0xffffffff)
        ), onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = "Detail",
            style = MaterialTheme.typography.titleLarge,
            color = Blue400,
            fontWeight = FontWeight.Bold
        )
        IconButton(modifier = Modifier.padding(16.dp), colors = IconButtonDefaults.iconButtonColors(
            containerColor = DangerColor, contentColor = Color(0xffffffff)
        ), onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
