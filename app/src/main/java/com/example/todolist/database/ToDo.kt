package com.example.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ToDo(
    @PrimaryKey var id:UUID=UUID.randomUUID(),
    var title:String="",
    var isdidit:Boolean=false,
    var details:String="",
    var startDate:Date=Date(),
    var expiredDate:Date=Date(),
    //--------------------------
    var suspect: String = ""
    //var isFavorite:Boolean=false
)
