package com.example.mytodobeta

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodobeta.databinding.TodoItemBinding
import com.example.mytodobeta.model.todo.ToDo

// 1引数=リストで扱うデータを表す型、2引数＝ViewHolderの型
class ToDoAdapter : ListAdapter<ToDo, ToDoAdapter.ViewHolder>(
    // 湯嘘の比較をするためのコールバックオブジェクト
    callbacks
) {
    // リスト1行分のビューを作り、ViewHolder煮詰めて返す
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // ViewHolderとリスト中での場所が渡され、ビューに値をセット
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
val todo = getItem(position)
        holder.bindTO(todo)
    }

    // View一つ分を保持
    class ViewHolder(
        private val binding: TodoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTO(todo: ToDo) {
            binding.titleText.text = todo.title
            binding.createdText.text = android.text.format.DateFormat.format(
                "yyyy-MM-dd hh:mm:ss",
                todo.created
            )
        }

    }

    companion object {
        private val callbacks = object : DiffUtil.ItemCallback<ToDo>() {
            override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.created == newItem.created
            }

        }
    }
}