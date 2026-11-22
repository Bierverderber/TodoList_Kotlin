package com.example.todolist.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    var todoList = mutableStateOf<List<TodoItem>>(emptyList())
        private set

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            todoList.value = getTodosUseCase()
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
            loadTodos()
        }
    }
}