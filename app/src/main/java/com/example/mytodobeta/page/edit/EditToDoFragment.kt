package com.example.mytodobeta.page.edit

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mytodobeta.R
import com.example.mytodobeta.databinding.EditTodoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditToDoFragment : Fragment(R.layout.edit_todo_fragment) {
    private val args: EditToDoFragmentArgs by navArgs()

    private val vm: EditToDoViewModel by viewModels()

    private var _binding: EditTodoFragmentBinding? = null
    private val binding: EditTodoFragmentBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this._binding = EditTodoFragmentBinding.bind(view)

        val todo = args.todo
        binding.titleEdit.setText(todo.title)
        binding.detailEdit.setText(todo.detail)
    }

    override fun onDestroy() {
        super.onDestroy()
        this._binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_edit, menu)
    }
}