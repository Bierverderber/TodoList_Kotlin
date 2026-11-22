package com.example.todolist.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.domain.model.TodoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todo: TodoItem?,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали задачи") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (todo != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "Заголовок: ${todo.title}", style = MaterialTheme.typography.headlineSmall)
                Text(text = "Описание: ${todo.description}", style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = "Статус: ${if (todo.isCompleted) "Выполнено" else "Не выполнено"}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (todo.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Задача не найдена")
            }
        }
    }
}