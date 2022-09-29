package com.example.mytodobeta.model.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

// テーブルを作成
@Entity
data class ToDo(
    // 主キー
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val title: String,
    val detail: String,
    val created: Long,
    val modified: Long
)
