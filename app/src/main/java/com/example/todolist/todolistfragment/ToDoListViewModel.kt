package com.example.todolist.todolistfragment

import androidx.lifecycle.LiveData
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
    fun getAllTasksByAToZ(): LiveData<List<ToDo>> {
        return toDoRepo.getAllTasksByAToZ()

    }

    fun getAllTasksByExpiredDate(): LiveData<List<ToDo>> {
        return toDoRepo.getAllTasksByExpiredDate()

    }

    fun homManyTaskDone(): LiveData<List<ToDo>> {
        return toDoRepo.homManyTaskDone()

    }




    fun getAllTasksByDone(): LiveData<List<ToDo>> {
        return toDoRepo.getAllTasksByDone()

    }

    fun getAllTasksByUnDone(): LiveData<List<ToDo>> {
        return toDoRepo.getAllTasksByUnDone()

    }

    }
