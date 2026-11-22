package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.todolist.presentation.viewmodel.TodoViewModel
import com.example.todolist.presentation.ui.screen.TodoListScreen
import com.example.todolist.presentation.ui.screen.TodoDetailScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: TodoViewModel
) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(
                todos = viewModel.todoList.value,
                onItemClick = { id -> navController.navigate("detail/$id") },
                onCheckboxChange = { id -> viewModel.toggleTodo(id) }
            )
        }
        composable(
            route = "detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId")
            val todo = viewModel.todoList.value.find { it.id == todoId }
            TodoDetailScreen(
                todo = todo,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}