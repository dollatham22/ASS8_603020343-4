package com.example.lab8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    var studentList = arrayListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycle_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycle_view.addItemDecoration((DividerItemDecoration(recycle_view.getContext(), DividerItemDecoration.VERTICAL)))

        recycle_view.addOnItemTouchListener(object : OnItemClickListener{
            override fun onItemClicked(position: Int, view: View){
                Toast.makeText(applicationContext, "You click on :" + studentList[position].emp_name,
                    Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        callStudentData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

     fun addStudent(view : View) {


                val intent = Intent(this, InsertActivity::class.java)
                startActivity(intent)


    }

    fun callStudentData() {

        studentList.clear();
        val serv : StudentAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudentAPI ::class.java)

        serv.retrieveStudent()
            .enqueue(object : Callback<List<Employee>>{

                override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
                    response.body()?.forEach{
                        studentList.add(Employee(it.emp_name,it.emp_gen,it.emp_email,it.emp_salary))
                    }
                    recycle_view.adapter = StudentsAdapter(studentList,applicationContext)
                }

                override fun onFailure(call: Call<List<Employee>>, t: Throwable) = t.printStackTrace()
            })
    }
}
interface OnItemClickListener{
    fun onItemClicked(position: Int, view: View)
}
fun RecyclerView.addOnItemTouchListener(onClickListener: OnItemClickListener){
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener{
        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener{
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }
    })
}
