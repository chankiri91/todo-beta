package com.example.mytodobeta.page.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodobeta.model.todo.ToDo
import com.example.mytodobeta.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditToDoViewModel @Inject constructor(
    private val repo: ToDoRepository
) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val done = MutableLiveData<ToDo>()

    fun save(todo: ToDo, title: String, detail: String) {
        if (title.trim().isEmpty()) {
            errorMessage.value = "Please input title"
            return
        }

        viewModelScope.launch {
            try {
                val updateToDo = repo.update(todo, title, detail)
                done.value = updateToDo
            } catch (e: Exception) {
                errorMessage.value = e.toString()
            }
        }
    }
}