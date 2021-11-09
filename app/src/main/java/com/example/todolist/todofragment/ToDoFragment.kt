package com.example.todolist.todofragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.DatePickerDialogFragment
import com.example.todolist.R
import com.example.todolist.database.ToDo
import com.example.todolist.todolistfragment.KEY_ID
import java.time.DayOfWeek
import java.time.Month
import java.time.Year
import java.util.*

const val DO_DAY_KEY="DO date"
private const val DATE_FORMAT = "EEE, MMM, dd"
private const val REQUEST_CONTACT=1
private const val TAG="maine"
class ToDoFragment : Fragment(),DatePickerDialogFragment.DatePickerCallBack {

    private lateinit var task: ToDo

    private lateinit var titleEditText: EditText
    private lateinit var endDateBtn: Button
    private lateinit var isDone: RadioButton
    private lateinit var startDateBtn: Button
    private lateinit var detailsET:EditText



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG,"onActivityResult")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode !=Activity.RESULT_OK){
            return
     }
//-------------------------------double checking -------------------------------------------
        if (requestCode== REQUEST_CONTACT && data!=null){
            val contactsURI= data.data

            val queryFields= arrayOf(ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE)

            val cursor= contactsURI?.let {
                requireActivity().contentResolver.query(
                    it,queryFields,null,null,null
                )
            }
            cursor?.let { cursor ->
                cursor.use {
                    if(it.count==0){return}

                    it.moveToFirst()
                    val suspect = it.getString(0)

                    task.suspect=suspect

                    val taskId=arguments?.getSerializable(KEY_ID) as UUID
                    fragmentViewModel.loadToDo(taskId)
                    task.id=taskId
                    fragmentViewModel.saveUpdate(task)
                    //chooseSuspectBtn.text=suspect

                }
            }
        }
    }

    private  val fragmentViewModel
    by lazy{ ViewModelProvider(this)
        .get(ToDoFragmentViewModel::class.java)}


    override fun onCreateView(


    inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_to_do,container,false)
        titleEditText=view.findViewById(R.id.title_tv)
        detailsET=view.findViewById(R.id.details_mt)
        endDateBtn=view.findViewById(R.id.end_date_btn)
        isDone=view.findViewById(R.id.do_radio)
        startDateBtn=view.findViewById(R.id.start_date_btn)



        startDateBtn.apply {
            text=task.startDate.toString()
            isEnabled=false
        }
        return view
    }


    override fun onStart() {
        Log.d(TAG,"onStart")
        startDateBtn.setOnClickListener {

        }
        endDateBtn.setOnClickListener {
            val args=Bundle()
            args.putSerializable(DO_DAY_KEY,task.expiredDate)
            val datePicker= DatePickerDialogFragment()
            datePicker.show(this.parentFragmentManager,"date picker")
            datePicker.arguments=args
            datePicker.setTargetFragment(this,0)

        }

        super.onStart()
        val textWatcher=object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //I will not used it today
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               Log.d("AAA",p0.toString())
                task.title=p0.toString()
            }


            override fun afterTextChanged(p0: Editable?) {
                //I will not used it today
            }

        }
        val textWatcher1=object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //I will not used it today
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("AAA",p0.toString())
                task.details=p0.toString()
            }


            override fun afterTextChanged(p0: Editable?) {
                //I will not used it today
            }

        }

        titleEditText.addTextChangedListener(textWatcher)
        detailsET.addTextChangedListener(textWatcher1)
        isDone.setOnCheckedChangeListener{_,b->
            task.isdidit=b

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate")
        super.onCreate(savedInstanceState)
        task= ToDo()
        val taskId=arguments?.getSerializable(KEY_ID) as UUID
        fragmentViewModel.loadToDo(taskId)
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(KEY_ID,"onViewCreated")

        super.onViewCreated(view, savedInstanceState)
        fragmentViewModel.toDoLiveData.observe(
            viewLifecycleOwner,{
                it?.let {
                    task=it
                    titleEditText.setText(it.title)
                    detailsET.setText(it.details)
                    startDateBtn.setText(it.startDate.toString())
                    endDateBtn.setText(it.expiredDate.toString())
                    isDone.isChecked=it.isdidit ?:false

                }
            }
        )

    }

    override fun onStop() {
        Log.d(KEY_ID,"onStop")

        super.onStop()
        fragmentViewModel.saveUpdate(task)
    }
    override fun OnDateSelected(date: Date) {

        task.expiredDate=date
        task.startDate=date

        endDateBtn.text=date.toString()
        startDateBtn.text=date.toString()

    }



}



