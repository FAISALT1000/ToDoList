package com.example.todolist.todofragment

import androidx.lifecycle.*
import com.example.todolist.database.ToDo
import com.example.todolist.database.ToDoRepo
import java.util.*

class ToDoFragmentViewModel: ViewModel() {

    private val toDoRepo=ToDoRepo.get()
    private val todoIdLiveData=MutableLiveData<UUID>()

      var toDoLiveData:LiveData<ToDo?> =
          Transformations.switchMap(todoIdLiveData){
              toDoRepo.getTask(it)
          }
    public fun loadToDo(taskId:UUID){
        todoIdLiveData.value=taskId
    }
    public fun  saveUpdate(task:ToDo){
        toDoRepo.updateTask(task)
    }
    fun del(task:ToDo){
        toDoRepo.deleteTask(task)
    }

}