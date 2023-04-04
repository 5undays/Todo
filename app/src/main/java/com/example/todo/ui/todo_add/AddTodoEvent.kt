package com.example.todo.ui.todo_add

sealed class AddTodoEvent {
    data class OnTitleChange(val title: String) : AddTodoEvent()
    data class OnDescriptionChange(val description: String) : AddTodoEvent()
    object OnSaveTodoClick : AddTodoEvent()
}
