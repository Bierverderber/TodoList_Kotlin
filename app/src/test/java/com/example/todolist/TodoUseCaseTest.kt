package com.example.todolist

import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TodoUseCaseTest {

    @Test
    fun getTodosUseCase_returnsThreeTasks() = runTest {
        val mockRepository = mock<TodoRepository>()
        val fakeTasks = listOf(
            TodoItem(1, "Task 1", "Desc 1", false),
            TodoItem(2, "Task 2", "Desc 2", true),
            TodoItem(3, "Task 3", "Desc 3", false)
        )
        whenever(mockRepository.getTodos()).thenReturn(fakeTasks)

        val useCase = GetTodosUseCase(mockRepository)
        val result = useCase()

        assertEquals(3, result.size)
    }

    @Test
    fun toggleTodoUseCase_callsRepositoryToggle() = runTest {
        val mockRepository = mock<TodoRepository>()
        val useCase = ToggleTodoUseCase(mockRepository)

        useCase(1)

        verify(mockRepository).toggleTodo(1)
    }
}