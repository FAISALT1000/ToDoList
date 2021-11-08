package com.example.todolist.todolistfragment

import androidx.lifecycle.ViewModel
import com.example.todolist.database.ToDo
import com.example.todolist.database.ToDoRepo

class ToDoListViewModel:ViewModel() {


   val toDoRepo=ToDoRepo.get()

    val liveDataTask =toDoRepo.getAllTask()

    fun addTask(task:ToDo){
        toDoRepo.addTask(task)
    }
    fun deleteTask(task:ToDo){
        toDoRepo.deleteTask(task)
    }
    }
