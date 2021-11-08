package com.example.todolist.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.*
import java.util.concurrent.Executors


private const val DATABASE_NAME="ToDo-database"

class ToDoRepo private constructor(context: Context){

    private val database:ToDoDatabase = Room.databaseBuilder(
        context.applicationContext,
        ToDoDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val toDoDao= database.ToDoDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getAllTask():LiveData<List<ToDo>> =toDoDao.getAllTasks()

    fun getTask(id:UUID):LiveData<ToDo?>{
        return toDoDao.getTask(id)
    }
    fun updateTask(task:ToDo){
        executor.execute {
            toDoDao.updateTask(task)
        }
    }

    fun addTask(task: ToDo){
        executor.execute {
            toDoDao.addTask(task)
        }
    }
    fun deleteTask(task: ToDo){
        executor.execute {
            toDoDao.deleteTask(task)
        }
    }

    companion object{
        var INSTANCE:ToDoRepo?=null

        fun initialize(context: Context){
            if(INSTANCE==null){
                INSTANCE= ToDoRepo(context)
            }

        }
        fun get():ToDoRepo{
            return INSTANCE?:throw  IllegalStateException("To DO REPO")
        }

    }
}