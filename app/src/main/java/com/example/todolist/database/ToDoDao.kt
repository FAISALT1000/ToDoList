package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface ToDoDao {
@Query("SELECT * FROM todo")
fun getAllTasks():LiveData<List<ToDo>>


@Query("SELECT * FROM todo ORDER BY title desc")
fun getAllTasksByAToZ():LiveData<List<ToDo>>

@Query("SELECT * FROM todo ORDER BY expiredDate desc")
fun getAllTasksByExpiredDate():LiveData<List<ToDo>>


@Query("SELECT * FROM todo ORDER BY isdidit=0 ")
fun howManyTaskDone():LiveData<List<ToDo>>

@Query("SELECT * FROM todo  WHERE isdidit=0 ORDER BY expiredDate desc")
fun getAllTasksByUnDone():LiveData<List<ToDo>>
@Query("SELECT * FROM todo  WHERE isdidit=1 ORDER BY expiredDate desc")
fun getAllTasksByDone():LiveData<List<ToDo>>

@Query("SELECT * FROM todo WHERE id=(:id)")
fun getTask(id: UUID):LiveData<ToDo?>


@Update
fun updateTask(task: ToDo)
@Insert
fun addTask(task: ToDo)
@Delete
fun deleteTask(task:ToDo)

}