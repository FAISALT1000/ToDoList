package com.example.todolist

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.todolist.todofragment.DO_DAY_KEY

import java.util.*

class DatePickerDialogFragment:DialogFragment() {

    interface DatePickerCallBack{

        fun OnDateSelected(date: Date)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val date=arguments?.getSerializable(DO_DAY_KEY)as Date



        val calendar= Calendar.getInstance()
        calendar.time=date
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)
//        val hour=calendar.get(Calendar.HOUR)
//        val minute=calendar.get(Calendar.MINUTE)

        val dateListener=DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val resultDate=GregorianCalendar(year,month,day).time
            targetFragment?.let {
                (it as DatePickerCallBack ).OnDateSelected(resultDate)
            }
        }

        return DatePickerDialog(
            requireContext(),
            dateListener,
            year,
            month,
            day
        )


    }
}