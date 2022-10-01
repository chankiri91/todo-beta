package com.example.mytodobeta

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytodobeta.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
// FragmentのプライマリーコンストラクタでレイアウトXMLを指定
class MainFragment:Fragment(R.layout.main_fragment) {
    // デリゲートを使ってviewmodelをプロパティとしてもたせる
    private val vm: MainViewModel by viewModels()

    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this._binding = MainFragmentBinding.bind(view)

        // recyclerViewにToDoAdapterそセット
        val adapter = ToDoAdapter()
        binding.recycler.adapter = adapter

        binding.fab.setOnClickListener {
            // findNavController()でNavController取得。
            findNavController().navigate(R.id.action_mainFragment_to_createToDoFragment)
        }

        // LiveDataを監視
        vm.todoList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}