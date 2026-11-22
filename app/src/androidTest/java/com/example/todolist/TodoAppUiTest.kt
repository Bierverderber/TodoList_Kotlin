package com.example.todolist

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class TodoAppUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun displayAllTasks_fromJson() {
        composeTestRule.onNodeWithText("Купить молоко").assertExists()
        composeTestRule.onNodeWithText("Позвонить маме").assertExists()
        composeTestRule.onNodeWithText("Сделать ДЗ по Android").assertExists()
    }

    @Test
    fun checkbox_togglesStatus() {
        val checkbox = composeTestRule.onNodeWithTag("todo_checkbox_1")
        checkbox.assertIsOff()
        checkbox.performClick()
        checkbox.assertIsOn()
    }

    @Test
    fun navigation_listToDetailAndBack() {
        composeTestRule.onNodeWithTag("todo_item_1").performClick()

        composeTestRule.onNodeWithText("Детали задачи").assertExists()
        composeTestRule.onNodeWithText("Описание: 2 литра, обезжиренное").assertExists()

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        composeTestRule.onNodeWithText("Todo List").assertExists()
    }
}