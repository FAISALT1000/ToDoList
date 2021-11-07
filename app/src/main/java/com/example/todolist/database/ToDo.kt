package com.example.todolist.database

import androidx.room.Entity
import java.util.*

@Entity
data class ToDo(
    var title:String="",
    var isdidit:Boolean=false,
    var details:String="",
    var startDate:Date=Date(),
    var expiredDate:Date=Date(),
)
