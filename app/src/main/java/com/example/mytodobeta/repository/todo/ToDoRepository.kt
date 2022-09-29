package com.example.mytodobeta.repository.todo

import com.example.mytodobeta.model.todo.ToDo
import com.example.mytodobeta.model.todo.ToDoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// todoを永続化する機能を提供
interface ToDoRepository {
     suspend fun create(title: String, detail: String)
}

class ToDoRepositoryImpl(private val dao: ToDoDAO): ToDoRepository {
    override suspend fun create(title: String, detail: String) {
        val now = System.currentTimeMillis()
        val todo = ToDo(title = title, detail = detail, created = now, modified = now)
        withContext(Dispatchers.IO) {
            dao.create(todo)
        }
    }
}