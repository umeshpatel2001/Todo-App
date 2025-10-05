package com.example.todo.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDatabase: TaskDatabase
) {

    suspend fun addTask(task: Task) {
        taskDatabase.taskDao().insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDatabase.taskDao().deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDatabase.taskDao().updateTask(task)
    }

    fun allTasks(): Flow<List<Task>> {
        return taskDatabase.taskDao().getAllTasks()
    }
}