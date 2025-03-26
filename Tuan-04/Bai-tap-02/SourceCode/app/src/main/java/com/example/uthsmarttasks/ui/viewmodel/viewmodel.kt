package com.example.uthsmarttasks.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.data.model.Task
import com.example.uthsmarttasks.di.NetworkModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = NetworkModule.taskApiService.getTasks()

                Log.d("API", response.toString())

                // Kiểm tra nếu response hợp lệ
                if (response.isSuccess) {
                    _tasks.value = response.tasks ?: emptyList()
                } else {
                    _tasks.value = emptyList()
                }
            } catch (e: Exception) {
                _tasks.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getTaskById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val task = NetworkModule.taskApiService.getTaskById(id)
                _selectedTask.value = task.tasks
                Log.d("TaskViewModel", "Selected Task: $task")
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Error fetching task: ${e.message}")
                _selectedTask.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                NetworkModule.taskApiService.deleteTask(id)
                _tasks.value = _tasks.value.filter { it.id != id }
                Log.d("TaskViewModel", "Deleted Task ID: $id")
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Error deleting task: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
