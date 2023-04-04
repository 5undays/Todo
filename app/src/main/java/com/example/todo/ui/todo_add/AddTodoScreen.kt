package com.example.todo.ui.todo_add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.util.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddTodoScreen(
    onPopBackStack: () -> Unit, viewModel: AddTodoViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.message, actionLabel = it.action
                    )
                }
                else -> Unit
            }
        }
    }
    Scaffold(scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(AddTodoEvent.OnSaveTodoClick) }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
            }
        }) {
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(value = viewModel.title, onValueChange = {
                viewModel.onEvent(AddTodoEvent.OnTitleChange(it))
            }, placeholder = {
                Text(text = "Title")
            }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = viewModel.description, onValueChange = {
                viewModel.onEvent(AddTodoEvent.OnDescriptionChange(it))
            }, placeholder = {
                Text(text = "description")
            }, modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}