package com.example.todolist

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class UpdateTaskDialogFragment: DialogFragment() {
    interface UpdateTaskCallBack{

        fun updateTaskSelected(date: Date)
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return
//    }

}