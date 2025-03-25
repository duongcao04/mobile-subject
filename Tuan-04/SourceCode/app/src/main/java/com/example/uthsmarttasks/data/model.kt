package com.example.uthsmarttasks.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val tasks: List<Task>
)
data class ApiItemResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val tasks: Task
)

data class Task(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("priority") val priority: String? = null,
    @SerializedName("category") val category: String? = null,
    @SerializedName("dueDate") val dueDate: String? = null,
    @SerializedName("createdAt") val createdAt: String? = null,
    @SerializedName("updatedAt") val updatedAt: String? = null,
    @SerializedName("subtasks") val subtasks: List<Subtask> = emptyList(),
    @SerializedName("attachments") val attachments: List<Attachment> = emptyList(),
    @SerializedName("reminders") val reminders: List<Reminder> = emptyList()
)

data class Subtask(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("isCompleted") val isCompleted: Boolean
)

data class Attachment(
    @SerializedName("id") val id: Int,
    @SerializedName("fileName") val fileName: String,
    @SerializedName("fileUrl") val fileUrl: String
)

data class Reminder(
    @SerializedName("id") val id: Int,
    @SerializedName("time") val time: String,
    @SerializedName("type") val type: String
)