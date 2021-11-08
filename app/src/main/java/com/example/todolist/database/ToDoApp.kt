package com.example.todolist.database

import android.app.Application

class ToDoApp:Application() {
    override fun onCreate() {
        super.onCreate()
        ToDoRepo.initialize(this)
    }
}