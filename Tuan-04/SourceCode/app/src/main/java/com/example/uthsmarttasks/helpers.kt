package com.example.uthsmarttasks

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DateTimeHelper {
    companion object {
        fun formatDueDate(dueDate: String): String {
            return try {
                val formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")
                val parsedDate = ZonedDateTime.parse(dueDate)
                parsedDate.format(formatter)
            } catch (e: Exception) {
                "Invalid Date" // Trả về lỗi nếu định dạng sai
            }
        }
    }
}
