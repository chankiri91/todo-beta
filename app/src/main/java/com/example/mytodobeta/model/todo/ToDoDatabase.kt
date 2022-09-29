package com.example.mytodobeta.model.todo

import androidx.room.Database
import androidx.room.RoomDatabase

// 1つのデータベースにはたくさんのテーブルが入ってるため、テーブルを指定する。
@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun todoDAO(): ToDoDAO
}