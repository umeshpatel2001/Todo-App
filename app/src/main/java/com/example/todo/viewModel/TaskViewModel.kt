package com.example.todo.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todo.view.Todo

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateOf(
        listOf(
            Todo(1, "Learn Kotlin", false),
            Todo(2, "Learn Jetpack Compose", false),
            Todo(3, "Build To-Do App", false)
        )
    )
    val tasks: State<List<Todo>> = _tasks

    fun addTask(title: String) {
        val task = Todo(_tasks.value.size + 1, title, false)
        _tasks.value = _tasks.value + task
    }

    fun toggleTask(isDone: Boolean, id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(isDone = isDone)
            else it
        }
    }

}