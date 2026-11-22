package com.example.todolist.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.presentation.ui.component.TodoItemCard
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    todos: List<TodoItem>,
    onItemClick: (Int) -> Unit,
    onCheckboxChange: (Int) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Todo List") }) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(todos) { todo ->
                TodoItemCard(
                    todo = todo,
                    onItemClick = { onItemClick(todo.id) },
                    onCheckboxChange = { onCheckboxChange(todo.id) }
                )
            }
        }
    }
}