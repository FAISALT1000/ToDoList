package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface ToDoDao {
@Query("SELECT * FROM todo")
fun getAllTasks():LiveData<List<ToDo>>

@Query("SELECT * FROM todo ORDER BY startDate")
fun getAllTasksByDate():LiveData<List<ToDo>>

//@Query("SELECT * FROM todo WHERE  ORDER BY startDate ")
//fun getAllTasksByUnDone():LiveData<List<ToDo>>

@Query("SELECT * FROM todo WHERE id=(:id)")
fun getTask(id: UUID):LiveData<ToDo?>


@Update
fun updateTask(task: ToDo)
@Insert
fun addTask(task: ToDo)
@Delete
fun deleteTask(task:ToDo)

}