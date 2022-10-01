package com.example.mytodobeta.repository.todo

import android.content.Context
import androidx.room.Room
import com.example.mytodobeta.model.todo.ToDo
import com.example.mytodobeta.model.todo.ToDoDAO
import com.example.mytodobeta.model.todo.ToDoDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

// Implementsでインターフェースを実装したクラスだよという意味
// 依存性注入される側の準備。ToDoDAOをどっかからもってきて。
// ToDoRepositoryImplでは、ToDoDAOを必要としています。
class ToDoRepositoryImpl @Inject constructor(
    private val dao: ToDoDAO
) : ToDoRepository {
    override fun getAl(): Flow<List<ToDo>> {
        return dao.getAll()
    }

    override suspend fun create(title: String, detail: String) {
        val now = System.currentTimeMillis()
        val todo = ToDo(title = title, detail = detail, created = now, modified = now)
        withContext(Dispatchers.IO) {
            dao.create(todo)
        }
    }

    override suspend fun update(todo: ToDo, title: String, detail: String): ToDo {
        val updateToDo = ToDo(
            _id = todo._id,
            title = title,
            detail = detail,
            created = todo.created,
            modified = System.currentTimeMillis()
        )
        withContext(Dispatchers.IO) {
            dao.update(updateToDo)
        }
        return updateToDo
    }
}