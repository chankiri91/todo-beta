package com.example.mytodobeta.repository.todo

// todoを永続化する機能を提供
interface ToDoRepository {
     suspend fun create(title: String, detail: String)
}