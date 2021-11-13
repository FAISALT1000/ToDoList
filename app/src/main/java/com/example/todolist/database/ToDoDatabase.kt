package com.example.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ToDo::class],version = 1)
@TypeConverters(ToDoTypeConverter::class)
abstract class ToDoDatabase :RoomDatabase(){
    abstract fun ToDoDao():ToDoDao
}