package com.example.todo.data

import kotlinx.coroutines.flow.Flow

class TaskRepository (private val taskDao: TaskDao){

    suspend fun addTask(task: Task){
        taskDao.insertTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    fun allTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }


}