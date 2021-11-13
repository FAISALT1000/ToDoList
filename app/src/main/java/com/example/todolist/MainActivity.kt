package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.todofragment.ToDoFragment
import com.example.todolist.todofragment.ToDoFragmentViewModel
import com.example.todolist.todolistfragment.ToDoListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment=
            supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView)
        if(currentFragment==null){
            val fragment= ToDoListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView,fragment)
                .commit()
        }
   }

}