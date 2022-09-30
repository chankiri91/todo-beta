package com.example.mytodobeta.page.edit

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytodobeta.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditToDoFragment:Fragment(R.layout.edit_todo_fragment) {
    private val vm: EditToDoViewModel by viewModels()
}