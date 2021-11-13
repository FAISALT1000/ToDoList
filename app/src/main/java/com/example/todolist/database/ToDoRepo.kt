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
//======================================================================
    fun getAllTask():LiveData<List<ToDo>> {
        val tyr=toDoDao.getAllTasks()
       return tyr
    }
    fun homManyTaskDone():LiveData<List<ToDo>> =toDoDao.howManyTaskDone()

    fun getTask(id:UUID):LiveData<ToDo?>{
        return toDoDao.getTask(id)
    }
    fun updateTask(task:ToDo){
        executor.execute {
            toDoDao.updateTask(task)
        }
    }



    fun getAllTasksByExpiredDate():LiveData<List<ToDo>>{
        return toDoDao.getAllTasksByExpiredDate()
    }
    fun getAllTasksByAToZ():LiveData<List<ToDo>>{
        return toDoDao.getAllTasksByAToZ()
    }
    fun howManyTaskDone():LiveData<List<ToDo>>{
        return toDoDao.howManyTaskDone()
    }



    fun getAllTasksByDone():LiveData<List<ToDo>>{
        return toDoDao.getAllTasksByDone()
    }
    fun getAllTasksByUnDone():LiveData<List<ToDo>>{
        return toDoDao.getAllTasksByUnDone()
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