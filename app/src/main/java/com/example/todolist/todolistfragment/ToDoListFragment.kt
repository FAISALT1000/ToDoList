package com.example.todolist.todolistfragment

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.ToDo
import com.example.todolist.database.ToDoRepo
import com.example.todolist.todofragment.ToDoFragment
import java.text.SimpleDateFormat
import java.util.*

const val KEY_ID="to do fragment"
class ToDoListFragment : Fragment() {
  private lateinit var toDoRecyclerView: RecyclerView

  val toDoListViewModel by lazy{ViewModelProvider(this).get(ToDoListViewModel::class.java)}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.appbar,menu)

    }
//---------------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setHasOptionsMenu(true)
        }
//---------------------------------------------------------------------------------------------------

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.new_task->{
                val task=ToDo()
                toDoListViewModel.addTask(task)

                val args=Bundle()
                args.putSerializable(KEY_ID,task.id)
                val fragment=ToDoFragment()
                fragment.arguments=args
                activity?.let{
                    it.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,fragment)
                    .addToBackStack(null)
                    .commit()}

          true  }
            else-> super.onOptionsItemSelected(item)
        }

    }
//---------------------------------------------------------------------------------------------------


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_to_do_list, container, false)
        toDoRecyclerView=view.findViewById(R.id.to_do_recycler_view)
        val linearLayoutManager=LinearLayoutManager(context)
        toDoRecyclerView.layoutManager=linearLayoutManager

        return view
    }
//---------------------------------------------------------------------------------------------------

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toDoListViewModel.liveDataTask.observe(
            viewLifecycleOwner,{
                updateUI(it)
            }
        )
    }
private fun updateUI(tasks:List<ToDo>){
    val taskAdapter= ToDoAdapter(tasks)
    toDoRecyclerView.adapter=taskAdapter
}

    private inner class ToDoViewHolder (view:View) : RecyclerView.ViewHolder(view) ,View.OnClickListener {
        private lateinit var task: ToDo
        private val titleTextView: TextView = itemView.findViewById(R.id.task_item)
        private val endDate: TextView = itemView.findViewById(R.id.task_date_item)
        private val startDate: TextView = itemView.findViewById(R.id.task_date_item)
        private val day: TextView = itemView.findViewById(R.id.reday_tv)
        private val backGraond:ConstraintLayout=itemView.findViewById(R.id.con)
        private val isDoneImageView: ImageView = itemView.findViewById(R.id.did_it_imageView)
        private val detailsTextView: TextView = itemView.findViewById(R.id.task_details)


       init {
           itemView.setOnClickListener(this)
       }

        fun bind(task:ToDo){
            this.task=task

            titleTextView.text=task.title
            endDate.text=task.expiredDate.toString()
            detailsTextView.text=task.details
            startDate.text=task.startDate.toString()
            //isDoneImageView.

         //   day.text



//                val currentDate = ""
//                val finalDate = ""
                var todaysDate: Date = Date()
               // var date2: Date
               // if (date2.after(endDate))
                //   date2.text
             // val sdf = SimpleDateFormat("dd/MM/yyyy")

                if (todaysDate.after(task.expiredDate)&& task.isdidit==true){

                    endDate.text="Done"
                    //titleTextView.text=task.title
                    backGraond.setBackgroundColor( resources.getColor(R.color.done))
                }else if (todaysDate.after(task.expiredDate)&& task.isdidit==false){
                        endDate.text="try Again you lazy "
                     backGraond.setBackgroundColor( resources.getColor(R.color.not_done))

                 }


                //val dates = SimpleDateFormat("MM/dd/yyyy")
                //todaysDate = dates.parse(todaysDate.toString())
              //  date2 = dates.parse(date2.toString())
                //val difference: Long = kotlin.math.abs(todaysDate.time - date2.time)
                //val differenceDates = difference / (24 * 60 * 60 * 1000)
                //val dayDifference = differenceDates.toString()




            isDoneImageView.visibility = if(task.isdidit){
                View.VISIBLE
            }else{
                View.GONE
            }
        }

        override fun onClick(p0: View?) {
            if(p0==itemView) {
                val args = Bundle()
                args.putSerializable(KEY_ID, task.id)
                val fragment = ToDoFragment()
                fragment.arguments = args
                activity?.let {
                    it.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .addToBackStack("")
                        .commit()
                }}else if(p0==endDate){
                    Toast.makeText(context,"${task.expiredDate}",Toast.LENGTH_SHORT).show()

                }

                  else if(p0==isDoneImageView){if(task.isdidit){
                    Toast.makeText(context,"${task.isdidit}",Toast.LENGTH_SHORT).show()}
                }

                }




    }
    private inner class ToDoAdapter(var tasks:List<ToDo>) :RecyclerView.Adapter<ToDoViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
          val view=layoutInflater.inflate(R.layout.item_list_fragment,parent,false)
            return ToDoViewHolder(view)
        }

        override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
            val toDo=tasks[position]
            holder.bind(toDo)
        }

        override fun getItemCount(): Int=tasks.size

    }



}

