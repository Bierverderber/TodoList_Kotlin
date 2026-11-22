package com.example.todolist.data.repository

import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository

class TodoRepositoryImpl(private val dataSource: TodoJsonDataSource) : TodoRepository {

    private var cachedTodos: MutableList<TodoItem> = dataSource.getTodos().map {
        TodoItem(it.id, it.title, it.description, it.isCompleted)
    }.toMutableList()

    override suspend fun getTodos(): List<TodoItem> {
        return cachedTodos.toList()
    }

    override suspend fun toggleTodo(id: Int) {
        val index = cachedTodos.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = cachedTodos[index]
            cachedTodos[index] = item.copy(isCompleted = !item.isCompleted)
        }
    }
}