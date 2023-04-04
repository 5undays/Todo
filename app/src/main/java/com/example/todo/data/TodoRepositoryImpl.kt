package com.example.todo.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository {
    override suspend fun addTodo(todo: Todo) {
        dao.addTodo(todo)
    }

    override suspend fun dropTodo(todo: Todo) {
        dao.dropTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }
}