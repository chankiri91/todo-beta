package com.example.mytodobeta.page.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodobeta.R
import com.example.mytodobeta.databinding.TodoDetailFragmentBinding
import com.example.mytodobeta.model.todo.ToDo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoDetailFragment : Fragment(R.layout.todo_detail_fragment) {
    private val vm: ToDoDetailViewModel by viewModels()

    private var _binding: TodoDetailFragmentBinding? = null
    private val binding: TodoDetailFragmentBinding get() = _binding!!

    // 画面遷移で渡される引数をプロパティ二セット
    private val args: ToDoDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setFragmentResultListener("edit") { _, data ->
            val todo = data.getParcelable("todo") as? ToDo ?: return@setFragmentResultListener
            vm.todo.value = todo
        }
        if (savedInstanceState == null) {
            vm.todo.value = args.todo
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this._binding = TodoDetailFragmentBinding.bind(view)

        vm.todo.observe(viewLifecycleOwner) { todo ->
            binding.titleText.text = todo.title
            binding.detailText.text = todo.detail
        }

        val todo = args.todo
        binding.titleText.text = todo.title
        binding.detailText.text = todo.detail
    }

    override fun onDestroy() {
        super.onDestroy()
        this._binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                val todo = vm.todo.value ?: return true
                val action =
                    ToDoDetailFragmentDirections.actionToDoDetailFragmentToEditToDoFragment(
                        todo
                    )
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}