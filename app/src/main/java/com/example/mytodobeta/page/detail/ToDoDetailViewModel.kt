package com.example.mytodobeta.page.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mytodobeta.model.todo.ToDo

class ToDoDetailViewModel(
    savedStateHandle: SavedStateHandle
):ViewModel() {
    val todo = savedStateHandle.getLiveData<ToDo>("todo")
}