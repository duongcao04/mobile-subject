package com.example.uthsmarttasks.data.api

import com.example.uthsmarttasks.data.model.ApiItemResponse
import com.example.uthsmarttasks.data.model.ApiResponse
import com.example.uthsmarttasks.data.model.Task
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskApiService {
    @GET("tasks")
    suspend fun getTasks(): ApiResponse

    @GET("task/{id}")
    suspend fun getTaskById(@Path("id") id: Int): ApiItemResponse

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int)
}