package com.example.mytodobeta.page.create

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytodobeta.R
import com.example.mytodobeta.databinding.CreateTodoFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateToDoFragment : Fragment(R.layout.create_todo_fragment) {
    private val vm: CreateToDoViewModel by viewModels()
    private var _binding: CreateTodoFragmentBinding? = null
    private val binding: CreateTodoFragmentBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this._binding = CreateTodoFragmentBinding.bind(view)

        vm.errorMessage.observe(viewLifecycleOwner) { msg ->
            if (msg.isEmpty()) return@observe
            Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show()
            // 画面回転するとエラーが再度表示されるのを防止
            vm.errorMessage.value = ""
        }
        vm.done.observe(viewLifecycleOwner) { done ->
            if (!done) return@observe
            //1つ前の画面に戻る
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this._binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // MenuInflateを使ってXMLリソースからメニューを作る
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                save()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

        private fun save() {
            val title = binding.titleEdit.text.toString()
            val detail = binding.detailEdit.text.toString()
            // viewModelにわたす
            vm.save (title, detail)
        }
    }