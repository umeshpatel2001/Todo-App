package com.example.todo.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.ui.theme.TodoTheme
import com.example.todo.viewModel.TaskViewModel

@Composable
fun TodoScreenView(viewModel: TaskViewModel = hiltViewModel()) {
    val todolist by viewModel.tasks.collectAsState()

    var newTodoTask by remember { mutableStateOf("") }


    Text(text = "Todo List", modifier = Modifier.padding(13.dp))

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(13.dp)) {
        TextField(
            value = newTodoTask, onValueChange = { newTodoTask = it },
            placeholder = {
                Text(text = "Enter new task")
            },
        )
        Spacer(modifier = Modifier.width(13.dp))
        Button(onClick = {
            if (newTodoTask.isNotEmpty()) {
                viewModel.addTask(newTodoTask)
                newTodoTask = ""
            }
        }, modifier = Modifier.weight(1f)) {
            Text(text = "Add")
        }
    }

    Spacer(modifier = Modifier.height(13.dp))

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(13.dp),
        verticalArrangement = Arrangement.Top
    ) {
        items(todolist) { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(13.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.isDone,
                    onCheckedChange = { isChecked ->
                        viewModel.toggleTask(task)
                    }
                )
                Text(
                    text = task.title,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { viewModel.deleteTask(task) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Task")
                }
            }
            Spacer(modifier = Modifier.height(13.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    TodoTheme {
        TodoScreenView()
    }
}